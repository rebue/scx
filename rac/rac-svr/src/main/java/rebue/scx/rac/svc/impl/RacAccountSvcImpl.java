package rebue.scx.rac.svc.impl;

import static org.mybatis.dynamic.sql.SqlBuilder.and;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;
import static rebue.scx.rac.mapper.RacAccountDynamicSqlSupport.racAccount;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageInfo;
import com.google.common.io.Files;

import io.minio.BucketExistsArgs;
import io.minio.GetBucketPolicyArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.SetBucketPolicyArgs;
import lombok.SneakyThrows;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.cap.api.CapMessageSendingApi;
import rebue.scx.cap.to.CapEmailVerificationTo;
import rebue.scx.cap.to.CapSMSVerificationTo;
import rebue.scx.rac.co.RacMinioCo;
import rebue.scx.rac.config.LevelProtectProperties;
import rebue.scx.rac.dao.RacAccountDao;
import rebue.scx.rac.jo.RacAccountJo;
import rebue.scx.rac.mapper.RacAccountMapper;
import rebue.scx.rac.mapper.RacOrgAccountMapper;
import rebue.scx.rac.mapper.RacPermCommandMapper;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.mo.RacPermCommandMo;
import rebue.scx.rac.mo.RacRealmMo;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.mo.ex.RacAccountExMo;
import rebue.scx.rac.mo.ex.RacAccountNonDesensitizedMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.ra.ListTransferOfOrgRa;
import rebue.scx.rac.svc.RacAccountSvc;
import rebue.scx.rac.svc.RacAppSvc;
import rebue.scx.rac.svc.RacDisableLogSvc;
import rebue.scx.rac.svc.RacOrgSvc;
import rebue.scx.rac.svc.RacPermMenuSvc;
import rebue.scx.rac.svc.RacRealmSvc;
import rebue.scx.rac.svc.RacUserSvc;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountDelTo;
import rebue.scx.rac.to.RacAccountListTo;
import rebue.scx.rac.to.RacAccountModifySignInByOldPswdTo;
import rebue.scx.rac.to.RacAccountModifySignInPswdTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountOneTo;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.scx.rac.to.RacDisableLogAddTo;
import rebue.scx.rac.to.RacDisableLogModifyTo;
import rebue.scx.rac.to.RacOrgAccountAddTo;
import rebue.scx.rac.to.ex.RacAccountByUserTo;
import rebue.scx.rac.to.ex.RacAccountEmailTo;
import rebue.scx.rac.to.ex.RacAccountMobileTo;
import rebue.scx.rac.to.ex.RacAccountResetPasswordTo;
import rebue.scx.rac.to.ex.RacAccountUnionIdTo;
import rebue.scx.rac.to.ex.RacListTransferOfOrgTo;
import rebue.scx.rac.util.PswdUtils;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.util.OrikaUtils;

/**
 * 账户服务实现
 *
 * <pre>
 * 注意：
 * 1. 查询数据库操作的方法，不用设置默认 @Transactional
 *    在类上方已经设置默认为 readOnly=true, propagation=Propagation.SUPPORTS
 *    而涉及到 增删改 数据库操作的方法时，要设置 readOnly=false, propagation=Propagation.REQUIRED
 * 2. 事务不会针对受控异常（checked exception）回滚
 *    要想回滚事务，须抛出运行时异常(RuntimeException)
 * 3. 如果类上方不带任何参数的 @Transactional 注解时，如同下面的设置
 *    propagation(传播模式)=REQUIRED，readOnly=false，isolation(事务隔离级别)=READ_COMMITTED
 * </pre>
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
public class RacAccountSvcImpl extends
        BaseSvcImpl<java.lang.Long, RacAccountAddTo, RacAccountModifyTo, RacAccountDelTo, RacAccountOneTo, RacAccountListTo, RacAccountPageTo, RacAccountMo, RacAccountJo, RacAccountMapper, RacAccountDao>
        implements RacAccountSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private RacAccountSvc        thisSvc;

    @DubboReference
    private CapMessageSendingApi capSMSSendingApi;

    @Resource
    private RacUserSvc           userSvc;

    @Resource
    private RacAppSvc            appSvc;

    @Resource
    private RacRealmSvc          realmSvc;

    @Resource
    private RacPermMenuSvc       permMenuSvc;

    @Resource
    private RacDisableLogSvc     disableLogSvc;

    @Resource
    private RacOrgAccountMapper  orgAccountMapper;

    @Resource
    private RacPermCommandMapper permCommandMapper;

    @Resource
    private RacOrgSvc            racOrgSvc;
    @Resource
    LevelProtectProperties       levelProtectProperties;
    @Resource
    private MinioClient          minioClient;

    @Value("${minio.endpoint:http://172.20.14.125:9000}")
    private String               minioEndpoint;

    /**
     * 文件路径是否需要全路径还是只需要去除Ip的相对路径地址
     * true 全路径http://127.0.0.1:9000/oss-svr/***
     * false 相对路径 /oss-svr/**
     */
    @Value("${minio.urlPrefixBool:true}")
    private Boolean              url_prefix_bool;

    /**
     * 密码过期时长/天
     */
    @Override
    public Long getPasswordDoverdue() {
        String value = levelProtectProperties.getPasswordDoverdue();
        return Long.parseLong(value);
    }

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<RacAccountMo> getMoClass() {
        return RacAccountMo.class;
    }

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, RacAccountAddTo, RacAccountModifyTo, RacAccountDelTo, RacAccountOneTo, RacAccountListTo, RacAccountPageTo, RacAccountMo, RacAccountJo> getThisSvc() {
        return thisSvc;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RacAccountMo addMo(final RacAccountMo mo) {
        RacAccountOneTo oneTo = new RacAccountOneTo();
        oneTo.setRealmId(mo.getRealmId());
        oneTo.setSignInName(mo.getSignInName());
        Long count = thisSvc.countSelective(oneTo);
        if (count > 0) {
            throw new RuntimeExceptionX("该领域下已存在" + mo.getSignInName());
        }
        oneTo.setSignInName(null);
        oneTo.setSignInNickname(mo.getSignInNickname());
        count = thisSvc.countSelective(oneTo);
        // if (count > 0) {
        // throw new RuntimeExceptionX("该领域下已存在" + mo.getSignInNickname());
        // }
        if (StringUtils.isNotBlank(mo.getSignInPswd())) {
            // 随机生成盐值
            if (!StringUtils.isNotBlank(mo.getSignInPswdSalt())) {
                mo.setSignInPswdSalt(PswdUtils.randomSalt());
            }
            // 根据生成的盐值进行摘要
            mo.setSignInPswd(PswdUtils.saltPswd(mo.getSignInPswd(), mo.getSignInPswdSalt()));
        }
        final long now = System.currentTimeMillis();
        mo.setCreateTimestamp(now);
        mo.setUpdateTimestamp(now);
        if (mo.getCode() != null && mo.getCode().equals("")) {
            mo.setCode(null);
        }
        final RacAccountMo result = super.addMo(mo);
        // 判断是否设置了默认组织，有则添加组织关系
        if (result.getOrgId() != null) {
            final List<Long> accountId = new ArrayList<>();
            accountId.add(result.getId());
            final RacOrgAccountAddTo to = new RacOrgAccountAddTo();
            to.setOrgId(result.getOrgId());
            to.setAccountIds(accountId);
            racOrgSvc.addOrgAccount(to);
        }
        return result;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RacAccountMo modifyPswdById(final RacAccountMo mo) {
        if (StringUtils.isNotBlank(mo.getSignInPswd())) {
            // 随机生成盐值
            mo.setSignInPswdSalt(PswdUtils.randomSalt());
            // 根据生成的盐值进行摘要
            mo.setSignInPswd(PswdUtils.saltPswd(mo.getSignInPswd(), mo.getSignInPswdSalt()));
        }
        mo.setUpdateTimestamp(System.currentTimeMillis());
        return super.modifyMoById(mo);
    }

    /**
     * 修改账户登录密码
     *
     * @param to 修改账户登录密码的具体数据
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void modifySignInPswd(final RacAccountModifySignInPswdTo to) {
        final RacAccountMo mo = new RacAccountMo();
        mo.setId(to.getId());
        mo.setSignInPswd(to.getSignInPswd());
        thisSvc.modifyPswdById(mo);
    }

    /**
     * 重置账户登录密码
     *
     * @param to 修改账户登录密码的具体数据
     */
    @Override
    public void resetPassword(RacAccountResetPasswordTo to) {
        RacAccountMo mo             = OrikaUtils.map(to, RacAccountMo.class);
        // 默认登录密码为：12345678
        // 设置固定盐值
        String       signInPswdSalt = "zGxxxC";
        mo.setSignInPswdSalt(signInPswdSalt);
        // 根据生成的盐值进行摘要
        String signInPswd = "25d55ad283aa400af464c76d713c07ad";
        mo.setSignInPswd(PswdUtils.saltPswd(signInPswd, mo.getSignInPswdSalt()));
        thisSvc.modifyMoById(mo);
    }

    /**
     * 根据旧登录密码更新新登录密码
     *
     * @param to 修改账户登录密码的具体数据
     *
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<?> modifySignInByOldPswd(RacAccountModifySignInByOldPswdTo to) {
        RacAccountMo accountMo = thisSvc.getAccountMoById(to.getId());
        if (accountMo.getSignInPswd().equals(PswdUtils.saltPswd(to.getSignInPswd(), accountMo.getSignInPswdSalt()))) {
            final RacAccountMo mo = new RacAccountMo();
            // 随机生成盐值
            mo.setSignInPswdSalt(PswdUtils.randomSalt());
            // 根据生成的盐值进行摘要
            mo.setSignInPswd(PswdUtils.saltPswd(to.getNewSignInPswd(), mo.getSignInPswdSalt()));
            mo.setUpdateTimestamp(System.currentTimeMillis());
            // 默认6个月密码过期
            mo.setExpirationDatetime(LocalDateTime.now().plusDays(getPasswordDoverdue()));
            mo.setId(to.getId());
            super.modifyMoById(mo);
            return new Ro<>(ResultDic.SUCCESS, "修改成功");
        }
        else {
            return new Ro<>(ResultDic.WARN, "旧密码不正确");
        }
    }

    /**
     * 添加账户unionId映射
     *
     * @param to 添加的具体信息
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RacAccountMo addUnionIdMapper(RacAccountUnionIdTo to) {
        RacAccountMo srcMo = thisSvc.getAccountMoById(to.getSrcId());
        RacAccountMo dstMo = thisSvc.getAccountMoById(to.getDstId());
        if (srcMo.getUnionId() != null) {
            dstMo.setUnionId(srcMo.getUnionId());
            thisSvc.modifyMoById(dstMo);
        }
        else {
            Long unionId = _idWorker.getId();
            dstMo.setUnionId(unionId);
            srcMo.setUnionId(unionId);
            thisSvc.modifyMoById(dstMo);
            thisSvc.modifyMoById(srcMo);
        }
        return thisSvc.getById(to.getSrcId());
    }

    /**
     * 删除账户unionId映射
     *
     * @param to 删除的具体信息
     *
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public RacAccountMo delUnionIdMapper(RacAccountUnionIdTo to) {
        int count = 0;
        count = _mapper.setUnionIdIsNull(to.getDstId());
        // }
        if (count == 0) {
            throw new RuntimeExceptionX("操作记录异常，记录已不存在或有变动");
        }
        if (count != 1) {
            throw new RuntimeExceptionX("操作记录异常，影响行数为" + count);
        }
        return thisSvc.getById(to.getSrcId());
    }

    /**
     * 通过unionId查询账户
     *
     * @param unionId
     *
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public List<RacAccountMo> getAccountByUnionId(Long unionId) {
        RacAccountListTo to = new RacAccountListTo();
        to.setUnionId(unionId);
        List<RacAccountMo> list = thisSvc.list(to).stream().map(item -> {
            RacRealmMo realmMo = realmSvc.getById(item.getRealmId());
            item.setRealm(realmMo);
            return item;
        }).collect(Collectors.toList());
        return list;
    }

    /**
     * 根据账户ID绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void bindModify(RacAccountModifyTo to) {
        thisSvc.modifyById(to);
    }

    /**
     * 用户解除绑定微信钉钉的信息
     *
     * @param to 只需要上传微信/钉钉的信息
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void unbindModify(RacAccountModifyTo to) {
        // RacAccountOneTo oneTo = OrikaUtils.map(to, RacAccountOneTo.class);
        // 解除绑定钉钉
        if (to.getDdUnionId() != null) {
            RacAccountMo one = thisSvc.getAccountMoById(to.getId());
            if (one == null) {
                throw new RuntimeExceptionX("查找不到该账户的绑定信息，请确认后再试！");
            }
            int unbindDingTalk = _mapper.unbindDingTalk(one.getId());
            if (unbindDingTalk != 1) {
                throw new RuntimeExceptionX("解除绑定异常信息，请确认后再试");
            }
        }
        else // 解除绑定微信
            if (to.getWxUnionId() != null) {
                RacAccountMo one = thisSvc.getAccountMoById(to.getId());
                if (one == null) {
                    throw new RuntimeExceptionX("查找不到该账户的绑定信息，请确认后再试！");
                }
                int unbindWechatOpen = _mapper.unbindWechatOpen(one.getId());
                if (unbindWechatOpen != 1) {
                    throw new RuntimeExceptionX("解除绑定异常信息，请确认后再试");
                }
            }
            else {
                throw new RuntimeExceptionX("查找不到该账户的绑定信息，请确认后再试！");
            }
    }

    /**
     * 管理员解除账户绑定钉钉
     *
     * @param id 被解绑的账户ID
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void unbindDdModify(Long id) {
        RacAccountMo one = thisSvc.getAccountMoById(id);
        if (one == null) {
            throw new RuntimeExceptionX("查找不到该账户的绑定信息，请确认后再试！");
        }
        int unbindDingTalk = _mapper.unbindDingTalk(one.getId());
        if (unbindDingTalk != 1) {
            throw new RuntimeExceptionX("解除绑定异常信息，请确认后再试");
        }
    }

    /**
     * 管理员解除账户绑定微信
     *
     * @param id 被解绑的账户ID
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void unbindWxModify(Long id) {
        RacAccountMo one = thisSvc.getAccountMoById(id);
        if (one == null) {
            throw new RuntimeExceptionX("查找不到该账户的绑定信息，请确认后再试！");
        }
        int unbindWechatOpen = _mapper.unbindWechatOpen(one.getId());
        if (unbindWechatOpen != 1) {
            throw new RuntimeExceptionX("解除绑定异常信息，请确认后再试");
        }
    }

    /**
     * 管理员解除账户绑定手机号
     *
     * @param id 被解绑的账户ID
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void unbindMobile(Long id) {
        RacAccountMo one = thisSvc.getAccountMoById(id);
        if (one == null) {
            throw new RuntimeExceptionX("查找不到该账户的绑定信息，请确认后再试！");
        }
        int unbindWechatOpen = _mapper.unbindMobile(one.getId());
        if (unbindWechatOpen != 1) {
            throw new RuntimeExceptionX("解除绑定异常信息，请确认后再试");
        }
    }

    /**
     * 账户绑定/解绑手机号
     *
     * @param to 账户ID/手机号/校验码
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<?> bindMobile(RacAccountMobileTo to) {
        RacAccountMo mo = thisSvc.getAccountMoById(to.getId());
        if (mo == null) {
            throw new RuntimeExceptionX("查找不到该账户的信息，请确认后再试！");
        }
        // 判断解帮还是绑定
        if (to.getBindType().equals("1")) {
            // 解绑
            CapSMSVerificationTo verifiy = new CapSMSVerificationTo();
            verifiy.setPhoneNumber(mo.getSignInMobile());
            verifiy.setCode(to.getCode());
            Ro<?> verification = capSMSSendingApi.msgSMSVerification(verifiy);
            if (verification.getResult().getCode() == 1) {
                int unbindWechatOpen = _mapper.unbindMobile(mo.getId());
                if (unbindWechatOpen != 1) {
                    throw new RuntimeExceptionX("解除绑定异常信息，请稍后再试");
                }
                capSMSSendingApi.deleteVerifiyMobilCode(verifiy);
                return new Ro<>(ResultDic.SUCCESS, "解绑手机成功");
            }
            return verification;
        }
        else {
            // 绑定
            CapSMSVerificationTo verifiy = new CapSMSVerificationTo();
            verifiy.setPhoneNumber(to.getMobile());
            verifiy.setCode(to.getCode());
            Ro<?> verification = capSMSSendingApi.msgSMSVerification(verifiy);
            if (verification.getResult().getCode() == 1) {
                // 绑定手机号
                if (mo.getSignInMobile() == null) {
                    RacAccountOneTo one = new RacAccountOneTo();
                    one.setSignInMobile(to.getMobile());
                    one.setRealmId(mo.getRealmId());
                    RacAccountMo oneMo = thisSvc.getAccountMoOne(one);
                    if (oneMo != null) {
                        return new Ro<>(ResultDic.FAIL, "手机号绑定失败，该手机号已被绑定！", mo);
                    }
                    mo.setSignInMobile(to.getMobile());
                    thisSvc.modifyMoById(mo);
                    capSMSSendingApi.deleteVerifiyMobilCode(verifiy);
                    return new Ro<>(ResultDic.SUCCESS, "手机号绑定成功", mo);
                }
                return new Ro<>(ResultDic.FAIL, "绑定失败，该账户已绑定手机号：", mo.getSignInMobile());
            }
            return verification;
        }
    }

    /**
     * 账户绑定/解绑邮箱
     *
     * @param to 账户ID/邮箱/校验码
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    @Override
    public Ro<?> bindMobile(RacAccountEmailTo to) {
        RacAccountMo mo = thisSvc.getAccountMoById(to.getId());
        if (mo == null) {
            throw new RuntimeExceptionX("查找不到该账户的信息，请确认后再试！");
        }
        // 判断解帮还是绑定
        if (to.getBindType().equals("1")) {
            // 解绑
            CapEmailVerificationTo verifiy = new CapEmailVerificationTo();
            verifiy.setEmail(mo.getSignInEmail());
            verifiy.setCode(to.getCode());
            Ro<?> verification = capSMSSendingApi.msgEmailVerification(verifiy);
            if (verification.getResult().getCode() == 1) {
                int unbindWechatOpen = _mapper.unbindEmail(mo.getId());
                if (unbindWechatOpen != 1) {
                    throw new RuntimeExceptionX("解除绑定异常信息，请稍后再试");
                }
                capSMSSendingApi.deleteVerifiyEmailCode(verifiy);
                return new Ro<>(ResultDic.SUCCESS, "解绑邮箱成功");
            }
            return verification;
        }
        else {
            // 绑定
            CapEmailVerificationTo verifiy = new CapEmailVerificationTo();
            verifiy.setEmail(to.getEmail());
            verifiy.setCode(to.getCode());
            Ro<?> verification = capSMSSendingApi.msgEmailVerification(verifiy);
            if (verification.getResult().getCode() == 1) {
                // 绑定邮箱
                if (mo.getSignInEmail() == null) {
                    RacAccountOneTo one = new RacAccountOneTo();
                    one.setSignInEmail(to.getEmail());
                    one.setRealmId(mo.getRealmId());
                    RacAccountMo oneMo = thisSvc.getAccountMoOne(one);
                    if (oneMo != null) {
                        return new Ro<>(ResultDic.FAIL, "邮箱绑定失败，该邮箱已被绑定！", mo);
                    }
                    mo.setSignInEmail(to.getEmail());
                    thisSvc.modifyMoById(mo);
                    capSMSSendingApi.deleteVerifiyEmailCode(verifiy);
                    return new Ro<>(ResultDic.SUCCESS, "邮箱绑定成功", mo);
                }
                return new Ro<>(ResultDic.FAIL, "绑定失败，该账户已绑定邮箱：", mo.getSignInEmail());
            }
            return verification;
        }
    }

    /**
     * 判断手机号是否已被绑定注册
     *
     * @param id     账户ID
     * @param mobile 手机号
     */
    @Override
    public Boolean existMobileById(Long id, int mobile) {
        RacAccountMo byId = thisSvc.getById(id);
        return _mapper.existByRealmIdAndMobile(byId.getRealmId(), mobile);
    }

    /**
     * 账户解除关联用户
     *
     * @param id 需要解除的账户ID
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void disassociateUser(Long id) {
        RacAccountMo one = thisSvc.getById(id);
        if (one == null) {
            throw new RuntimeExceptionX("查找不到该账户的信息，请确认后再试！");
        }
        int disassociateUser = _mapper.disassociateUser(one.getId());
        if (disassociateUser != 1) {
            throw new RuntimeExceptionX("解除关联异常信息，请确认后再试");
        }
    }

    /**
     * 启用账户
     *
     * @param to 启用的具体数据
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void enable(final RacDisableLogModifyTo to) {
        final RacAccountMo eableAccount = thisSvc.getAccountMoById(to.getAccountId());
        if (eableAccount != null) {
            if (!eableAccount.getIsEnabled()) {
                final RacAccountMo mo = new RacAccountMo();
                mo.setId(to.getAccountId());
                mo.setIsEnabled(!eableAccount.getIsEnabled());
                to.setRealmId(to.getRealmId());
                to.setAccountId(to.getAccountId());
                // 启用时添加锁定日志
                disableLogSvc.updateDisableLog(to);
                _mapper.updateByPrimaryKeySelective(mo);
            }
            else {
                throw new RuntimeExceptionX("该账户已经处于启用状态，请确认后再试！");
            }
        }
        else {
            throw new RuntimeExceptionX("该账户不存在，请确认后再试！");
        }
    }

    /**
     * 禁用账户
     *
     * @param to 禁用的具体数据
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void disable(final RacDisableLogAddTo to) {
        final RacAccountMo disableAccount = thisSvc.getAccountMoById(to.getAccountId());
        if (disableAccount != null) {
            if (disableAccount.getIsEnabled()) {
                final RacAccountMo mo = new RacAccountMo();
                mo.setId(to.getAccountId());
                mo.setIsEnabled(!disableAccount.getIsEnabled());
                to.setRealmId(to.getRealmId());
                // 禁用时添加禁用日志
                disableLogSvc.add(to);
                _mapper.updateByPrimaryKeySelective(mo);
            }
            else {
                throw new RuntimeExceptionX("该账户已经处于禁用状态，请确认后再试！");
            }
        }
        else {
            throw new RuntimeExceptionX("该账户不存在，请确认后再试！");
        }
    }

    /**
     * 上传头像
     */
    @Override
    @SneakyThrows
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro<?> uploadAvatar(final Long curAccountId, final String appId, final String fileName, final String contentDisposition, final String contentType,
            final InputStream inputStream) {
        RacAccountMo accountMo = thisSvc.getAccountMoById(curAccountId);
        RacAppMo     appMo     = appSvc.getById(appId);
        boolean      flag      = accountMo.getRealmId().equals(appMo.getRealmId());
        if (!flag) {
            RacAccountOneTo oneTo = new RacAccountOneTo();
            oneTo.setRealmId(appMo.getRealmId());
            if (accountMo.getUnionId() != null) {
                oneTo.setUnionId(accountMo.getUnionId());
                RacAccountMo oneMo = thisSvc.getAccountMoOne(oneTo);
                accountMo = oneMo;
            }
        }
        final Long    accountId = accountMo.getId();
        final String  fileExt   = Files.getFileExtension(fileName);
        final boolean found     = minioClient.bucketExists(BucketExistsArgs.builder().bucket(RacMinioCo.AVATAR_BUCKET).build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(RacMinioCo.AVATAR_BUCKET).build());
            final String policyJson = String.format(
                    "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:ListBucket\",\"s3:GetBucketLocation\"],\"Resource\":[\"arn:aws:s3:::%1$s\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::%1$s/*\"]}]}\n",
                    RacMinioCo.AVATAR_BUCKET);
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(RacMinioCo.AVATAR_BUCKET).config(policyJson).build());
        }
        final String bucketPolicy = minioClient.getBucketPolicy(GetBucketPolicyArgs.builder().bucket(RacMinioCo.AVATAR_BUCKET).build());
        System.out.println(bucketPolicy);
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Disposition", contentDisposition);
        headers.put("Content-Type", contentType);
        final String objectName = accountId.toString() + "." + fileExt;
        minioClient.putObject(
                PutObjectArgs.builder().bucket(RacMinioCo.AVATAR_BUCKET).contentType(contentType).headers(headers).object(objectName).stream(inputStream, -1, 10485760).build());
        final RacAccountMo mo = new RacAccountMo();
        mo.setId(accountId);
        // XXX 添加a参数并设置时间戳，以防前端接收到地址未改变，图片不刷新
        mo.setSignInAvatar(String.format("%s/%s/%s?a=%s", url_prefix_bool ? minioEndpoint : "", RacMinioCo.AVATAR_BUCKET, objectName, System.currentTimeMillis()));
        thisSvc.modifyMoById(mo);
        return new Ro<>(ResultDic.SUCCESS, "上传头像成功");
    }

    /**
     * 通过email获取账户信息
     *
     * @param realmId 领域ID
     * @param email   电子邮箱
     *
     * @return 账户信息
     */
    @Override
    public RacAccountMo getOneByEmail(final String realmId, final String email) {
        return _mapper.selectOne(c -> c.where(racAccount.realmId, isEqualTo(realmId), and(racAccount.signInEmail, isEqualTo(email)))).orElse(null);
    }

    /**
     * 通过手机号获取账户信息
     *
     * @param realmId 领域ID
     * @param mobile  手机号
     *
     * @return 账户信息
     */
    @Override
    public RacAccountMo getOneByMobile(final String realmId, final String mobile) {
        return _mapper.selectOne(c -> c.where(racAccount.realmId, isEqualTo(realmId), and(racAccount.signInMobile, isEqualTo(mobile)))).orElse(null);
    }

    /**
     * 通过登录名称获取账户信息
     *
     * @param realmId    领域ID
     * @param signInName 登录名称
     *
     * @return 账户信息
     */
    @Override
    public RacAccountMo getOneBySignInName(final String realmId, final String signInName) {
        return _mapper.selectOne(c -> c.where(racAccount.realmId, isEqualTo(realmId), and(racAccount.signInName, isEqualTo(signInName)))).orElse(null);
    }

    /**
     * 获取当前账户信息
     *
     * @param curAccountId   当前账户ID
     * @param agentAccountId 代理账户ID
     * @param appId          应用ID
     *
     * @return 当前账户信息
     */
    @Override
    public Ro<GetCurAccountInfoRa> getCurAccountInfo(final Long curAccountId, final Long agentAccountId, final String appId) {
        final GetCurAccountInfoRa ra        = new GetCurAccountInfoRa();
        RacAccountMo              accountMo = thisSvc.getById(curAccountId);
        RacAppMo                  appMo     = appSvc.getById(appId);
        boolean                   flag      = accountMo.getRealmId().equals(appMo.getRealmId());
        if (!flag) {
            RacAccountOneTo oneTo = new RacAccountOneTo();
            oneTo.setRealmId(appMo.getRealmId());
            if (accountMo.getUnionId() != null) {
                oneTo.setUnionId(accountMo.getUnionId());
                RacAccountMo oneMo = thisSvc.getOne(oneTo);
                accountMo = oneMo;
            }
            else {
                return new Ro<>(ResultDic.WARN, "该应用下,查找不到账户,请确认是否存在关联映射帐号");
            }
        }
        if (accountMo == null) {
            return new Ro<>(ResultDic.WARN, "该应用下,查找不到账户,请确认是否存在关联映射帐号");
            // return new Ro<>(ResultDic.WARN, "查找不到当前账户: " + curAccountId);
        }
        if (accountMo.getOrgId() != null) {
            final RacOrgMo racOrgMo = racOrgSvc.getById(accountMo.getOrgId());
            ra.setOrgId(accountMo.getOrgId());
            ra.setOrgFullName(racOrgMo.getFullName());
            ra.setOrgName(racOrgMo.getName());
        }
        RacAccountMo agentAccountMo = null;
        if (agentAccountId != null) {
            agentAccountMo = thisSvc.getById(agentAccountId);
            if (agentAccountMo == null) {
                return new Ro<>(ResultDic.WARN, "查找不到代理账户: " + agentAccountId);
            }
        }
        OrikaUtils.map(accountMo, ra);
        ra.setNickname(accountMo.getSignInNickname());
        ra.setAvatar(accountMo.getSignInAvatar());
        ra.setMenus(permMenuSvc.getMenusOfAccount(accountMo.getId(), appId));
        if (agentAccountId != null) {
            ra.setAgentAccountId(agentAccountId);
            ra.setAgentNickname(agentAccountMo.getSignInNickname());
            ra.setAgentAvatar(agentAccountMo.getSignInAvatar());
        }
        // 权限命令
        final List<RacPermCommandMo> racPermCommandMo = permCommandMapper.selectByAccountId(accountMo.getId());
        ra.setPermCommands(racPermCommandMo);
        if (accountMo.getUserId() != null) {
            RacUserMo userMo = userSvc.getById(accountMo.getUserId());
            ra.setUser(userMo);
        }
        return new Ro<>(ResultDic.SUCCESS, "获取当前账户信息成功", ra);
    }

    /**
     * 分页查询列表
     *
     * @param qo 查询条件
     *
     * @return 查询到的分页信息
     */
    @Override
    public PageInfo<RacAccountMo> page(final RacAccountPageTo qo) {
        final RacAccountListTo listTo = OrikaUtils.map(qo, RacAccountListTo.class);
        if (listTo.getOrgId() != null) {
            final RacOrgMo orgMo = racOrgSvc.getById(qo.getOrgId());
            listTo.setOrgTreeCode(orgMo.getTreeCode());
        }
        final ISelect select = () -> _mapper.list(listTo);
        return super.page(select, qo.getPageNum(), qo.getPageSize(), qo.getOrderBy());
    }

    /**
     * 查询账户的信息
     *
     * @param to 查询的具体条件
     */
    @Override
    public Ro<ListTransferOfOrgRa> listTransferOfOrg(final RacListTransferOfOrgTo to) {
        // 查找存在当前组织下的账户
        final RacAccountListTo existQo = new RacAccountListTo();
        existQo.setOrgId(to.getOrgId());
        existQo.setKeywords(to.getExistKeywords());
        final List<RacAccountMo> existAccountList = _mapper.list(existQo);
        // 查询可添加的所有用户
        final RacAccountExMo     addableQo        = new RacAccountExMo();
        addableQo.setRealmId(to.getRealmId());
        addableQo.setOrgId(to.getOrgId());
        addableQo.setKeywords(to.getAddableKeywords());
        final ISelect                select      = () -> _mapper.getAddablAccountList(addableQo);
        final PageInfo<RacAccountMo> addableList = thisSvc.page(select, to.getPageNum(), to.getPageSize(), null);
        // 将所有记录添加到返回ListTransferOfOrgRa的对象中
        final ListTransferOfOrgRa    ro          = new ListTransferOfOrgRa();
        ro.setAddableList(addableList);
        ro.setExistList(existAccountList);
        return new Ro<>(ResultDic.SUCCESS, "查询账户列表成功", ro);
    }

    /**
     * 根据Id查询账户携带user信息
     */
    @Override
    public RacAccountMo getById(Long id) {
        RacAccountMo accountMo = super.getById(id);
        if (accountMo.getUserId() != null) {
            RacUserMo userMo = userSvc.getById(accountMo.getUserId());
            accountMo.setUser(userMo);
        }
        return accountMo;
    }

    /**
     * 根据账户ID领域ID关键字查询该领域下账户(用户的下帐号)的信息
     *
     * @param to 查询的具体条件
     */
    @Override
    public PageInfo<RacAccountMo> getAccountByUser(RacAccountByUserTo to) {
        final RacAccountPageTo qo        = OrikaUtils.map(to, RacAccountPageTo.class);
        RacAccountMo           accountMo = thisSvc.getById(to.getAccountId());
        if (accountMo.getUserId() != null) {
            qo.setUserId(accountMo.getUserId());
        }
        final ISelect select = () -> _mapper.getAccountByUser(qo);
        return thisSvc.page(select, qo.getPageNum(), qo.getPageSize(), qo.getOrderBy());
    }

    /**
     * 根据用户ID查询用户下的账户的信息
     *
     * @param id
     *
     * @return
     */
    @Override
    public Ro<ListRa<RacAccountMo>> getByUserId(Long id) {
        List<RacAccountMo> List = _mapper.getByUserId(id);
        return new Ro<>(ResultDic.SUCCESS, "查询成功", new ListRa<>(List));
    }

    /**
     * 查询未脱敏帐号数据
     */
    @Override
    public RacAccountMo getAccountMoById(Long id) {
        RacAccountNonDesensitizedMo mo = _mapper.selectByKey(id).orElse(null);
        if (mo != null) {
            final RacAccountMo accountMo = OrikaUtils.map(mo, RacAccountMo.class);
            return accountMo;
        }
        return null;
    }

    /**
     * 查询未脱敏帐号数据
     */
    @Override
    public RacAccountMo getAccountMoOne(RacAccountOneTo to) {
        final RacAccountMo          qo = OrikaUtils.map(to, RacAccountMo.class);
        RacAccountNonDesensitizedMo mo = _mapper.selectByOne(qo).orElse(null);
        if (mo != null) {
            final RacAccountMo accountMo = OrikaUtils.map(mo, RacAccountMo.class);
            return accountMo;
        }
        return null;
    }
}
