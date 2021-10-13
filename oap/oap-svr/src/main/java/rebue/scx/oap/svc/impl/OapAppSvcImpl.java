package rebue.scx.oap.svc.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.scx.oap.dao.OapAppDao;
import rebue.scx.oap.jo.OapAppJo;
import rebue.scx.oap.mapper.OapAppMapper;
import rebue.scx.oap.mo.OapAppMo;
import rebue.scx.oap.mo.OapIpWhiteListMo;
import rebue.scx.oap.mo.OapRedirectUriMo;
import rebue.scx.oap.mo.ex.OapAppListAndRacAppListRa;
import rebue.scx.oap.mo.ex.OapAppMoEx;
import rebue.scx.oap.svc.OapAppSvc;
import rebue.scx.oap.svc.OapIpWhiteListSvc;
import rebue.scx.oap.svc.OapRedirectUriSvc;
import rebue.scx.oap.to.OapAppAddTo;
import rebue.scx.oap.to.OapAppDelTo;
import rebue.scx.oap.to.OapAppListTo;
import rebue.scx.oap.to.OapAppModifyTo;
import rebue.scx.oap.to.OapAppOneTo;
import rebue.scx.oap.to.OapAppPageTo;
import rebue.scx.oap.to.OapIpWhiteListAddTo;
import rebue.scx.oap.to.OapIpWhiteListDelTo;
import rebue.scx.oap.to.OapIpWhiteListListTo;
import rebue.scx.oap.to.OapRedirectUriAddTo;
import rebue.scx.oap.to.OapRedirectUriDelTo;
import rebue.scx.oap.to.OapRedirectUriListTo;
import rebue.scx.rac.api.RacAppApi;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.to.RacAppListTo;
import rebue.scx.rac.to.RacAppModifyTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.core.RandomEx;
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

    @DubboReference
    private RacAppApi         racAppApi;

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
        OapAppMo            amo   = thisSvc.addMo(mo);
        final OapAppMoEx    addMo = OrikaUtils.map(amo, OapAppMoEx.class);
        // 添加关联白名单IP
        OapIpWhiteListAddTo ipTo  = new OapIpWhiteListAddTo();
        ipTo.setAppId(addMo.getId());
        ipTo.setCreateTimestamp(System.currentTimeMillis());
        ipTo.setUpdateTimestamp(System.currentTimeMillis());
        List<String> ipList = new ArrayList<String>();
        for (String ip : to.getIpAddrs()) {
            ipTo.setIpAddr(ip);
            OapIpWhiteListMo ipMo = oapIpWhiteListSvc.add(ipTo);
            ipList.add(ipMo.getIpAddr());
        }
        // 添加关联重定向地址
        OapRedirectUriAddTo uriTo = new OapRedirectUriAddTo();
        uriTo.setAppId(addMo.getId());
        uriTo.setCreateTimestamp(System.currentTimeMillis());
        uriTo.setUpdateTimestamp(System.currentTimeMillis());
        List<String> uriList = new ArrayList<String>();
        for (String uri : to.getRedirectUris()) {
            uriTo.setRedirectUri(uri);
            OapRedirectUriMo uriMo = oapRedirectUriSvc.add(uriTo);
            uriList.add(uriMo.getRedirectUri());
        }
        addMo.setIpAddrs(ipList);
        addMo.setRedirectUris(uriList);
        RacAppModifyTo modfiy = new RacAppModifyTo();
        modfiy.setId(to.getAppId());
        modfiy.setIsCertified(true);
        modfiy.setAuthnType(to.getAuthnType());
        Ro<?> mod = racAppApi.modify(modfiy);
        if (mod.getResult().getCode() != 1) {
            throw new RuntimeExceptionX("添加记录异常");
        }
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
        // 先删除
        OapIpWhiteListDelTo ipDelTo      = new OapIpWhiteListDelTo();
        ipDelTo.setAppId(modifyMoById.getId());
        oapIpWhiteListSvc.delSelective(ipDelTo);
        OapRedirectUriDelTo uriDelTo = new OapRedirectUriDelTo();
        uriDelTo.setAppId(modifyMoById.getId());
        oapRedirectUriSvc.delSelective(uriDelTo);
        // 后添加
        // 添加关联白名单IP
        OapIpWhiteListAddTo ipTo = new OapIpWhiteListAddTo();
        ipTo.setAppId(modifyMoById.getId());
        ipTo.setCreateTimestamp(System.currentTimeMillis());
        ipTo.setUpdateTimestamp(System.currentTimeMillis());
        List<String> ipList = new ArrayList<String>();
        for (String ip : to.getIpAddrs()) {
            ipTo.setIpAddr(ip);
            OapIpWhiteListMo ipMo = oapIpWhiteListSvc.add(ipTo);
            ipList.add(ipMo.getIpAddr());
        }
        // 添加关联重定向地址
        OapRedirectUriAddTo uriTo = new OapRedirectUriAddTo();
        uriTo.setAppId(modifyMoById.getId());
        uriTo.setCreateTimestamp(System.currentTimeMillis());
        uriTo.setUpdateTimestamp(System.currentTimeMillis());
        List<String> uriList = new ArrayList<String>();
        for (String uri : to.getRedirectUris()) {
            uriTo.setRedirectUri(uri);
            OapRedirectUriMo uriMo = oapRedirectUriSvc.add(uriTo);
            uriList.add(uriMo.getRedirectUri());
        }
        final OapAppMoEx moEx = OrikaUtils.map(to, OapAppMoEx.class);
        moEx.setIpAddrs(ipList);
        moEx.setRedirectUris(uriList);
        if (to.getAuthnType() != null) {
            RacAppModifyTo modfiy = new RacAppModifyTo();
            modfiy.setId(to.getAppId());
            modfiy.setIsCertified(to.getAuthnType().equals(0) ? false : true);
            modfiy.setAuthnType(to.getAuthnType());
            Ro<?> mod = racAppApi.modify(modfiy);
            if (mod.getResult().getCode() != 1) {
                throw new RuntimeExceptionX("修改记录异常");
            }
        }
        return moEx;
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
     * 通过ID删除记录
     *
     * @param id 要删除记录的ID
     *
     * @return 如果成功，且删除一条记录，正常返回，否则会抛出运行时异常
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delById(Long id) {
        // 先删除依赖
        OapAppMo            appMo   = thisSvc.getById(id);
        OapIpWhiteListDelTo ipDelTo = new OapIpWhiteListDelTo();
        ipDelTo.setAppId(id);
        oapIpWhiteListSvc.delSelective(ipDelTo);
        OapRedirectUriDelTo uriDelTo = new OapRedirectUriDelTo();
        uriDelTo.setAppId(id);
        oapRedirectUriSvc.delSelective(uriDelTo);
        // 后删除主表
        final int rowCount = _mapper.deleteByPrimaryKey(id);
        if (rowCount == 0) {
            throw new RuntimeExceptionX("删除记录异常，记录已不存在或有变动");
        }
        if (rowCount != 1) {
            throw new RuntimeExceptionX("删除记录异常，影响行数为" + rowCount);
        }
        RacAppModifyTo modfiy = new RacAppModifyTo();
        modfiy.setId(appMo.getAppId());
        modfiy.setIsCertified(false);
        modfiy.setAuthnType((byte) 0);
        Ro<?> mod = racAppApi.modify(modfiy);
        if (mod.getResult().getCode() != 1) {
            throw new RuntimeExceptionX("删除记录异常");
        }

    }

    /**
     * 获取单个第三方应用的信息
     *
     * @param id 通过rac_app的ID关联查询
     */
    @Override
    public Ro<?> getByAppId(String id) {
        OapAppOneTo oneTo = new OapAppOneTo();
        oneTo.setAppId(id);
        OapAppMo             one = thisSvc.getOne(oneTo);
        Ro<PojoRa<RacAppMo>> ro  = racAppApi.getById(id);
        if (one == null) {
            OapAppMo mo = new OapAppMo();
            // 相关连的rac应用信息
            mo.setRacAppMo(ro.getExtra().getOne());
            mo.setClientId("uiap" + _idWorker.getIdStr());
            mo.setSecret(RandomEx.randomUUID());
            return new Ro<OapAppMo>(ResultDic.SUCCESS, "查询成功", mo);
        }
        final OapAppMoEx oneMo = OrikaUtils.map(one, OapAppMoEx.class);
        // 相关连的rac应用信息
        oneMo.setRacAppMo(ro.getExtra().getOne());
        // 查询白名单IP
        oneMo.setIpAddrs(this.getIpApprs(oneMo.getId()));
        // 查询重定向地址
        oneMo.setRedirectUris(this.getUris(oneMo.getId()));
        oneMo.setSecret(null);
        return new Ro<OapAppMo>(ResultDic.SUCCESS, "查询成功", oneMo);
    }

    /**
     * 查询应用的信息并附带第三方应用的信息
     *
     * @param qo 查询的具体条件(查询所有，及条件为空)
     * 
     */
    @Override
    public Ro<OapAppListAndRacAppListRa> listAndTripartite(OapAppListTo qo) {
        // 查询所有应用
        RacAppListTo              racAppListQo = new RacAppListTo();
        List<RacAppMo>            racAppList   = racAppApi.listOrderBySeqNo(racAppListQo).getExtra().getList().stream().map(item -> {
                                                   item.setMenu(null);
                                                   return item;
                                               }).collect(Collectors.toList());
        // 查询所有认证应用
        List<OapAppMo>            oapAppList   = thisSvc.listAll().stream().map(item -> {
                                                   OapAppMoEx moEx = OrikaUtils.map(item, OapAppMoEx.class);
                                                   // moEx.setIpAddrs(this.getIpApprs(item.getId()));
                                                   // moEx.setRedirectUris(this.getUris(item.getId()));
                                                   // 不显示在前端
                                                   // moEx.setSecret(null);
                                                   return moEx;
                                               }).collect(Collectors.toList());
        OapAppListAndRacAppListRa ra           = new OapAppListAndRacAppListRa();
        ra.setRacAppList(racAppList);
        ra.setOapAppList(oapAppList);
        return new Ro<OapAppListAndRacAppListRa>(ResultDic.SUCCESS, "查询成功", ra);
    }

    /**
     * 通过appId查询app的ip
     * 
     * @param appId
     * 
     * @return
     */
    private List<String> getIpApprs(Long appId) {
        final OapIpWhiteListListTo ipOne = new OapIpWhiteListListTo();
        ipOne.setAppId(appId);
        final List<String> ipApprs = oapIpWhiteListSvc.list(ipOne).stream().map(ip -> {
            return ip.getIpAddr();
        }).collect(Collectors.toList());
        return ipApprs;
    }

    /**
     * 通过appId查询app的uri
     * 
     * @param appId
     * 
     * @return
     */
    private List<String> getUris(Long appId) {
        final OapRedirectUriListTo uriOne = new OapRedirectUriListTo();
        uriOne.setAppId(appId);
        final List<String> uris = oapRedirectUriSvc.list(uriOne).stream().map(uri -> {
            return uri.getRedirectUri();
        }).collect(Collectors.toList());
        return uris;
    }

}
