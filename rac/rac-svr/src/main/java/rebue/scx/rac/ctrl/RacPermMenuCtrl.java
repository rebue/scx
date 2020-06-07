package rebue.scx.rac.ctrl;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacPermMenuMo;
import rebue.scx.rac.svc.RacPermMenuSvc;

/**
 * 权限菜单
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
@Slf4j
public class RacPermMenuCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacPermMenuSvc svc;

    /**
     * 添加权限菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "添加权限菜单")
    @Parameters({ @Parameter(name = "RacPermMenuMo", description = "权限菜单") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "添加权限菜单的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = IdRo.class))) })
    @PostMapping("/rac/perm-menu")
    Mono<Ro> add(@RequestBody final RacPermMenuMo mo) {
        log.info("received post:/rac/perm-menu");
        return Mono.create(callback -> callback.success(svc.add(mo)));
    }

    /**
     * 修改权限菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "修改权限菜单")
    @Parameters({ @Parameter(name = "RacPermMenuMo", description = "权限菜单") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "修改权限菜单的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = Ro.class))) })
    @PutMapping("/rac/perm-menu")
    Mono<Ro> modify(@RequestBody final RacPermMenuMo mo) throws Exception {
        log.info("received put:/rac/perm-menu");
        return Mono.create(callback -> callback.success(svc.modify(mo)));
    }

    /**
     * 删除权限菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "删除权限菜单")
    @Parameters({ @Parameter(name = "id", description = "权限菜单ID") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "删除权限菜单的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = Ro.class))) })
    @DeleteMapping("/rac/perm-menu")
    Mono<Ro> del(@RequestParam("id") final java.lang.Long id) {
        log.info("received delete:/rac/perm-menu");
        return Mono.create(callback -> callback.success(svc.del(id)));
    }

    /**
     * 获取单个权限菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "获取单个权限菜单")
    @Parameters({ @Parameter(name = "id", description = "权限菜单ID") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "获取单个权限菜单的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = RacPermMenuMo.class))) })
    @GetMapping("/rac/perm-menu/get-by-id")
    Mono<RacPermMenuMo> getById(@RequestParam("id") final java.lang.Long id) {
        log.info("received get:/rac/perm-menu/get-by-id");
        return Mono.create(callback -> callback.success(svc.getById(id)));
    }

    /**
     * 查询权限菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "查询权限菜单列表")
    @Parameters({ // 
    // 
    // 
    @Parameter(name = "qo", description = "查询权限菜单的条件"), // 
    @Parameter(name = "pageNum", description = "查询第几页"), @Parameter(name = "pageSize", description = "查询分页的大小"), @Parameter(name = "orderBy", description = "查询权限菜单的排序规则") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "查询权限菜单列表的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = RacPermMenuMo.class))) })
    @GetMapping("/rac/perm-menu/list")
    Mono<PageInfo<RacPermMenuMo>> list(final RacPermMenuMo qo, @RequestParam(value = "pageNum", required = false) final Integer pageNum, @RequestParam(value = "pageSize", required = false) final Integer pageSize, @RequestParam(value = "orderBy", required = false) final String orderBy) {
        log.info("received get:/rac/perm-menu/list");
        return Mono.create(callback -> callback.success(svc.list(qo, pageNum, pageSize, orderBy)));
    }
}
