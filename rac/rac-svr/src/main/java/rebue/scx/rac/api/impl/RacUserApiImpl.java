package rebue.scx.rac.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacUserApi;
import rebue.scx.rac.jo.RacUserJo;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.svc.RacUserSvc;
import rebue.scx.rac.to.RacUserAddTo;
import rebue.scx.rac.to.RacUserDelTo;
import rebue.scx.rac.to.RacUserListTo;
import rebue.scx.rac.to.RacUserModifyTo;
import rebue.scx.rac.to.RacUserOneTo;
import rebue.scx.rac.to.RacUserPageTo;

/**
 * 用户API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacUserApiImpl extends
    BaseApiImpl<java.lang.Long, RacUserAddTo, RacUserModifyTo, RacUserDelTo, RacUserOneTo, RacUserListTo, RacUserPageTo, RacUserMo, RacUserJo, RacUserSvc> implements RacUserApi {

    /**
     * 根据姓名和身份张号查询用户信息
     *
     * @param id
     *
     * @return
     */
    @Override
    public Ro<RacUserMo> getOneByRealNameIdCard(final RacUserOneTo to) {
        RacUserMo userMo = _svc.getOneByRealNameIdCard(to);
        return new Ro<>(ResultDic.SUCCESS, "查询用户信息成功", userMo);
    }
}
