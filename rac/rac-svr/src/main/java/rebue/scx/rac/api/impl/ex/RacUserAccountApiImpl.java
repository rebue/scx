package rebue.scx.rac.api.impl.ex;

import javax.annotation.Resource;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacUserAccountApi;
import rebue.scx.rac.mo.ex.RacUserAccountMo;
import rebue.scx.rac.svc.ex.RacUserAccountSvc;
import rebue.scx.rac.to.ex.RacUserAccountPageTo;

/**
 * 带用户信息的账户API实现
 */
@DubboService
public class RacUserAccountApiImpl implements RacUserAccountApi {

	@Resource
	private RacUserAccountSvc svc;

	/**
	 * 分页查询带有用户信息的账户
	 *
	 * @param qo 查询的具体条件
	 */
	@Override
	public Ro<PageRa<RacUserAccountMo>> page(final RacUserAccountPageTo qo) {
		return new Ro<>(ResultDic.SUCCESS, "分页查询成功", new PageRa<>(svc.page(qo)));
	}

	/**
	 * 根据ID查询有用户信息的账户
	 *
	 * @param id
	 */
	@Override
	public Ro<PojoRa<RacUserAccountMo>> getByAccountId(final Long id) {
		return new Ro<>(ResultDic.SUCCESS, "查询成功", new PojoRa<>(svc.getByAccountId(id)));
	}
}
