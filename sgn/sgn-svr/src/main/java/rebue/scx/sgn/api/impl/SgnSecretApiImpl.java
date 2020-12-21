package rebue.scx.sgn.api.impl;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.StringRa;
import rebue.robotech.ro.Ro;
import rebue.scx.sgn.api.SgnSecretApi;
import rebue.scx.sgn.svc.SgnSecretSvc;
import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.scx.sgn.to.SgnSecretModifyTo;

/**
 * 签名密钥API实现
 */
@DubboService
public class SgnSecretApiImpl implements SgnSecretApi {
    @Resource
    private SgnSecretSvc _svc;

    @Override
    public Ro<?> add(final SgnSecretAddTo to) {
        _svc.add(to);
        return new Ro<>(ResultDic.SUCCESS, "添加成功");
    }

    @Override
    public Ro<?> modify(final SgnSecretModifyTo to) {
        _svc.modifyById(to);
        return new Ro<>(ResultDic.SUCCESS, "修改成功");
    }

    @Override
    public Ro<?> del(final String id) {
        _svc.delById(id);
        return new Ro<>(ResultDic.SUCCESS, "删除成功");
    }

    @Override
    public Ro<StringRa> getSecretById(final String id) {
        return new Ro<>(ResultDic.SUCCESS, "查询成功", new StringRa(_svc.getSecretById(id)));
    }
}
