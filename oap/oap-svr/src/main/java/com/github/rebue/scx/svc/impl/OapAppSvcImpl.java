package com.github.rebue.scx.svc.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.rebue.scx.dao.OapAppDao;
import com.github.rebue.scx.jo.OapAppJo;
import com.github.rebue.scx.mapper.OapAppMapper;
import com.github.rebue.scx.mo.OapAppMo;
import com.github.rebue.scx.mo.OapIpWhiteListMo;
import com.github.rebue.scx.mo.OapRedirectUriMo;
import com.github.rebue.scx.mo.ex.OapAppMoEx;
import com.github.rebue.scx.svc.OapAppSvc;
import com.github.rebue.scx.svc.OapIpWhiteListSvc;
import com.github.rebue.scx.svc.OapRedirectUriSvc;
import com.github.rebue.scx.to.OapAppAddTo;
import com.github.rebue.scx.to.OapAppDelTo;
import com.github.rebue.scx.to.OapAppListTo;
import com.github.rebue.scx.to.OapAppModifyTo;
import com.github.rebue.scx.to.OapAppOneTo;
import com.github.rebue.scx.to.OapAppPageTo;
import com.github.rebue.scx.to.OapIpWhiteListAddTo;
import com.github.rebue.scx.to.OapIpWhiteListOneTo;
import com.github.rebue.scx.to.OapRedirectUriAddTo;
import com.github.rebue.scx.to.OapRedirectUriOneTo;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.util.OrikaUtils;

/**
 * 第三方应用服务实现
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
@Slf4j
public class OapAppSvcImpl
        extends BaseSvcImpl<java.lang.Long, OapAppAddTo, OapAppModifyTo, OapAppDelTo, OapAppOneTo, OapAppListTo, OapAppPageTo, OapAppMo, OapAppJo, OapAppMapper, OapAppDao>
        implements OapAppSvc {

    /**
     * 本服务的单例
     * 注意：内部调用自己的方法，如果涉及到回滚事务的，请不要直接调用，而是通过本实例调用
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Lazy
    @Resource
    private OapAppSvc         thisSvc;

    @Resource
    private OapIpWhiteListSvc oapIpWhiteListSvc;

    @Resource
    private OapRedirectUriSvc oapRedirectUriSvc;

    /**
     * 从接口获取本服务的单例(提供给基类调用)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected BaseSvc<java.lang.Long, OapAppAddTo, OapAppModifyTo, OapAppDelTo, OapAppOneTo, OapAppListTo, OapAppPageTo, OapAppMo, OapAppJo> getThisSvc() {
        return thisSvc;
    }

    /**
     * 泛型MO的class(提供给基类调用-因为java中泛型擦除，JVM无法智能获取泛型的class)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    protected Class<OapAppMo> getMoClass() {
        return OapAppMo.class;
    }

    /**
     * 添加记录
     *
     * @param to 添加的参数
     *
     * @return 如果成功，且仅添加一条记录，返回添加时自动生成的ID，否则会抛出运行时异常
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public OapAppMo add(OapAppAddTo to) {
        final OapAppMo mo = OrikaUtils.map(to, OapAppMo.class);
        mo.setCreateTimestamp(System.currentTimeMillis());
        mo.setUpdateTimestamp(System.currentTimeMillis());
        OapAppMoEx          addMo = (OapAppMoEx) thisSvc.addMo(mo);
        // 添加关联白名单IP
        OapIpWhiteListAddTo ipTo  = new OapIpWhiteListAddTo();
        ipTo.setAppId(addMo.getId());
        ipTo.setIpAddr(to.getIpAddr());
        ipTo.setCreateTimestamp(System.currentTimeMillis());
        ipTo.setUpdateTimestamp(System.currentTimeMillis());
        OapIpWhiteListMo    ipMo  = oapIpWhiteListSvc.add(ipTo);
        // 添加关联重定向地址
        OapRedirectUriAddTo uriTo = new OapRedirectUriAddTo();
        uriTo.setAppId(addMo.getId());
        uriTo.setRedirectUri(to.getRedirectUri());
        uriTo.setUpdateTimestamp(System.currentTimeMillis());
        OapRedirectUriMo uriMo = oapRedirectUriSvc.add(uriTo);
        addMo.setIpWhiteList(ipMo);
        addMo.setRedirectUri(uriMo);
        return addMo;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public OapAppMo addMo(OapAppMo mo) {
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }

        final int rowCount = _mapper.insertSelective(mo);
        if (rowCount != 1) {
            throw new RuntimeExceptionX("添加记录异常，影响行数为" + rowCount);
        }
        if (_mapper.getColumns().length > 1) {
            // XXX 新添加的记录肯定不在缓存中，调用接口的getById方法不可能查到缓存，不用担心
            return thisSvc.getById(mo.getId());
        }
        else {
            log.info("该表只有一个字段，Mapper没有生成selectByPrimaryKey的方法，直接返回id的Mo");
            return mo;
        }
    }

    /**
     * 通过ID修改记录内容
     *
     * @param to 修改的参数，必须包含ID
     *
     * @return 如果成功，且仅修改一条记录，正常返回，否则会抛出运行时异常
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public OapAppMo modifyById(OapAppModifyTo to) {
        final OapAppMo mo = OrikaUtils.map(to, OapAppMo.class);
        // 固定关联rac_app主键，不可修改
        mo.setAppId(null);
        OapAppMo            modifyMoById = thisSvc.modifyMoById(mo);
        OapIpWhiteListOneTo ipOne        = new OapIpWhiteListOneTo();
        ipOne.setAppId(modifyMoById.getId());
        OapIpWhiteListMo ipMo = oapIpWhiteListSvc.getOne(ipOne);
        // 修改白名单IP
        ipMo.setIpAddr(to.getIpAddr());
        ipMo.setUpdateTimestamp(System.currentTimeMillis());
        oapIpWhiteListSvc.modifyMoById(ipMo);
        // 修改重定向uri
        OapRedirectUriOneTo uriOne = new OapRedirectUriOneTo();
        uriOne.setAppId(modifyMoById.getId());
        OapRedirectUriMo uriMo = oapRedirectUriSvc.getOne(uriOne);
        uriMo.setRedirectUri(to.getRedirectUri());
        uriMo.setUpdateTimestamp(System.currentTimeMillis());
        oapRedirectUriSvc.modifyMoById(uriMo);
        return modifyMoById;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public OapAppMo modifyMoById(OapAppMo mo) {
        final int rowCount = _mapper.updateByPrimaryKeySelective(mo);
        if (rowCount == 0) {
            throw new RuntimeExceptionX("修改记录异常，记录已不存在或有变动");
        }
        if (rowCount != 1) {
            throw new RuntimeExceptionX("修改记录异常，影响行数为" + rowCount);
        }
        // XXX 注意这里是this，而不是getThisSvc()，这是避免使用到了缓存
        return this.getById(mo.getId());
    }

    /**
     * 获取单个第三方应用的信息
     *
     * @param id 通过rac_app的ID关联查询
     */
    @Override
    public OapAppMo getByAppId(String id) {
        OapAppOneTo oneTo = new OapAppOneTo();
        oneTo.setAppId(id);
        OapAppMoEx                oneMo = (OapAppMoEx) thisSvc.getOne(oneTo);
        final OapIpWhiteListOneTo ipOne = new OapIpWhiteListOneTo();
        ipOne.setAppId(oneMo.getId());
        final OapIpWhiteListMo    ipMo   = oapIpWhiteListSvc.getOne(ipOne);
        final OapRedirectUriOneTo uriOne = new OapRedirectUriOneTo();
        uriOne.setAppId(oneMo.getId());
        final OapRedirectUriMo uriMo = oapRedirectUriSvc.getOne(uriOne);
        oneMo.setIpWhiteList(ipMo);
        oneMo.setRedirectUri(uriMo);
        return oneMo;
    }

}
