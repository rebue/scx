package rebue.scx.rac.ctrl;

import javax.annotation.Resource;

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
import rebue.robotech.ra.ListRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacOrgApi;
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.to.RacOrgAccountAddTo;
import rebue.scx.rac.to.RacOrgAccountDelTo;
import rebue.scx.rac.to.RacOrgAddTo;
import rebue.scx.rac.to.RacOrgListTo;
import rebue.scx.rac.to.RacOrgModifyTo;
import rebue.scx.rac.to.RacOrgPageTo;

/**
 * 组织控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacOrgCtrl {

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Resource
	private RacOrgApi api;

	/**
	 * 添加组织
	 *
	 * @param to 添加的具体信息
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@PostMapping("/rac/org")
	public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacOrgAddTo to) {
		return Mono.create(callback -> callback.success(api.add(to)));
	}

	/**
	 * 添加组织和账户的关系
	 *
	 * @param to 添加的具体信息
	 */
	@PostMapping("/rac/org/addOrgAccount")
	public Mono<Ro<?>> addOrgAccount(@RequestBody final RacOrgAccountAddTo to) {
		return Mono.create(callback -> callback.success(api.addOrgAccount(to)));
	}

	/**
	 * 删除组织和账户的关系
	 *
	 * @param to 删除的具体信息
	 */
	@DeleteMapping("/rac/org/delOrgAccount")
	public Mono<Ro<?>> delOrgAccount(@RequestBody final RacOrgAccountDelTo to) {
		return Mono.create(callback -> callback.success(api.delOrgAccount(to)));
	}

	/**
	 * 修改组织的信息
	 *
	 * @param to 修改的具体数据
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@PutMapping("/rac/org")
	public Mono<Ro<?>> modify(@RequestBody final RacOrgModifyTo to) {
		return Mono.create(callback -> callback.success(api.modify(to)));
	}

	/**
	 * 删除组织
	 *
	 * @param id 组织ID
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@DeleteMapping("/rac/org")
	public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
		return Mono.create(callback -> callback.success(api.del(id)));
	}

	/**
	 * 获取单个组织的信息
	 *
	 * @param id 组织ID
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/rac/org/get-by-id")
	public Mono<Ro<PojoRa<RacOrgMo>>> getById(@RequestParam("id") final java.lang.Long id) {
		return Mono.create(callback -> callback.success(api.getById(id)));
	}

	/**
	 * 判断组织是否存在
	 *
	 * @param id 组织ID
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/rac/org/exist-by-id")
	public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
		return Mono.create(callback -> callback.success(api.existById(id)));
	}

	/**
	 * 查询组织的信息
	 *
	 * @param qo 查询的具体条件
	 */
	@GetMapping("/rac/org/list")
	public Mono<Ro<ListRa<RacOrgMo>>> list(final RacOrgListTo qo) {
		return Mono.create(callback -> callback.success(api.list(qo)));
	}

	/**
	 * 查询组织的信息
	 *
	 * @param qo 查询的具体条件
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/rac/org/page")
	public Mono<Ro<PageRa<RacOrgMo>>> page(final RacOrgPageTo qo) {
		return Mono.create(callback -> callback.success(api.page(qo)));
	}
}
