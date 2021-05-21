package rebue.scx.rac.ctrl;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacAccountApi;
import rebue.scx.rac.co.RacCo;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.ra.ListTransferOfOrgRa;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountDisableTo;
import rebue.scx.rac.to.RacAccountEnableTo;
import rebue.scx.rac.to.RacAccountModifySignInPswdTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.scx.rac.to.ex.RacListTransferOfOrgTo;
import rebue.wheel.turing.JwtUtils;

/**
 * 账户控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacAccountCtrl {

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Resource
	private RacAccountApi api;

	/**
	 * 添加账户
	 *
	 * @param to 添加的具体信息
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@PostMapping("/rac/account")
	public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacAccountAddTo to) {
		return Mono.create(callback -> callback.success(api.add(to)));
	}

	/**
	 * 修改账户的信息
	 *
	 * @param to 修改的具体数据
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@PutMapping("/rac/account")
	public Mono<Ro<?>> modify(@RequestBody final RacAccountModifyTo to) {
		return Mono.create(callback -> callback.success(api.modify(to)));
	}

	/**
	 * 删除账户
	 *
	 * @param id 账户ID
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@DeleteMapping("/rac/account")
	public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
		return Mono.create(callback -> callback.success(api.del(id)));
	}

	/**
	 * 获取单个账户的信息
	 *
	 * @param id 账户ID
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/rac/account/get-by-id")
	public Mono<Ro<PojoRa<RacAccountMo>>> getById(@RequestParam("id") final java.lang.Long id) {
		return Mono.create(callback -> callback.success(api.getById(id)));
	}

	/**
	 * 判断账户是否存在
	 *
	 * @param id 账户ID
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/rac/account/exist-by-id")
	public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
		return Mono.create(callback -> callback.success(api.existById(id)));
	}

	/**
	 * 查询账户的信息
	 *
	 * @param qo 查询的具体条件
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/rac/account/page")
	public Mono<Ro<PageRa<RacAccountMo>>> page(final RacAccountPageTo qo) {
		return Mono.create(callback -> callback.success(api.page(qo)));
	}

	/**
	 * 查询账户的信息
	 *
	 * @param qo 查询的具体条件
	 *
	 */
	@GetMapping("/rac/account/listTransferOfOrg")
	public Mono<Ro<ListTransferOfOrgRa>> listTransferOfOrg(final RacListTransferOfOrgTo qo) {
		return Mono.create(callback -> callback.success(api.listTransferOfOrg(qo)));
	}

	/**
	 * 修改账户登录密码
	 *
	 * @param to 修改账户登录密码的具体数据
	 */
	@PutMapping("/rac/account/modify-sign-in-pswd")
	public Mono<Ro<?>> modifySignInPswd(@RequestBody final RacAccountModifySignInPswdTo to) {
		return Mono.create(callback -> callback.success(api.modifySignInPswd(to)));
	}

	/**
	 * 启用账户
	 *
	 * @param to 启用的具体数据
	 */
	@PutMapping("/rac/account/enable")
	public Mono<Ro<?>> enable(@RequestBody final RacAccountEnableTo qo,
			@CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken) {
		if (StringUtils.isBlank(jwtToken)) {
			throw new IllegalArgumentException("在Cookie中找不到JWT签名");
		}
		qo.setUnlockOpId(JwtUtils.getJwtAccountIdInSign(jwtToken));
		qo.setUnlockDatetime(LocalDateTime.now());
		return Mono.create(callback -> callback.success(api.enable(qo)));
	}

	/**
	 * 禁用账户
	 *
	 * @param to 禁用的具体数据
	 */
	@PutMapping("/rac/account/disable")
	public Mono<Ro<?>> disable(@RequestBody final RacAccountDisableTo qo,
			@CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken) {
		if (StringUtils.isBlank(jwtToken)) {
			throw new IllegalArgumentException("在Cookie中找不到JWT签名");
		}
		qo.setLockOpId(JwtUtils.getJwtAccountIdInSign(jwtToken));
		qo.setLockDatetime(LocalDateTime.now());
		return Mono.create(callback -> callback.success(api.disable(qo)));
	}

	/**
	 * 获取当前账户信息
	 */
	@GetMapping("/rac/account/get-cur-account-info")
	public Mono<Ro<GetCurAccountInfoRa>> getCurAccountInfo(@CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken,
			@CookieValue(RacCo.SYS_ID_KEY) final String sysId) {
		if (StringUtils.isBlank(jwtToken)) {
			throw new IllegalArgumentException("在Cookie中找不到JWT签名");
		}
		final Long curAccountId = JwtUtils.getJwtAccountIdInSign(jwtToken);
		if (curAccountId == null) {
			throw new IllegalArgumentException("在JWT签名中找不到账户ID");
		}
		if (StringUtils.isBlank(sysId)) {
			throw new IllegalArgumentException("在Cookie中找不到系统ID");
		}
		return Mono.create(callback -> callback.success(api.getCurAccountInfo(curAccountId, sysId)));
	}
}
