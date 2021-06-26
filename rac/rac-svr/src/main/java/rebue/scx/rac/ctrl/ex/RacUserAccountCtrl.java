package rebue.scx.rac.ctrl.ex;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.ex.RacUserAccountApi;
import rebue.scx.rac.mo.ex.RacUserAccountMo;
import rebue.scx.rac.to.ex.RacUserAccountPageTo;

/**
 * 带用户信息的账户
 *
 */
@RestController
public class RacUserAccountCtrl {

	/**
	 */
	@Resource
	private RacUserAccountApi api;

	/**
	 * 分页查询带有用户信息的账户
	 *
	 * @param qo 查询的具体条件
	 */
	@GetMapping("/rac/user-account/page")
	public Mono<Ro<PageRa<RacUserAccountMo>>> page(final RacUserAccountPageTo qo) {
		return Mono.create(callback -> callback.success(api.page(qo)));
	}

	/**
	 * 根据ID查询有用户信息的账户
	 *
	 * @param id
	 */
	@GetMapping("/rac/user-account/by-accountId")
	public Mono<Ro<PojoRa<RacUserAccountMo>>> page(@RequestParam("id") final java.lang.Long id) {
		return Mono.create(callback -> callback.success(api.getByAccountId(id)));
	}

}
