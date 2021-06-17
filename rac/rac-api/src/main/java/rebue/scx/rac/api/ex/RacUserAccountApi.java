package rebue.scx.rac.api.ex;

import rebue.robotech.ra.PageRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.ex.RacUserAccountMo;
import rebue.scx.rac.to.ex.RacUserAccountPageTo;

/**
 * 带用户信息的账户API
 *
 */
public interface RacUserAccountApi {

    /**
     * 分页查询带有用户信息的账户
     *
     * @param qo 查询的具体条件
     */
    Ro<PageRa<RacUserAccountMo>> page(RacUserAccountPageTo qo);
}
