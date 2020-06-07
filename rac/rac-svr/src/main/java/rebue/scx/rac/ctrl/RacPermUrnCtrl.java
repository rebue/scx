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
import rebue.scx.rac.mo.RacPermUrnMo;
import rebue.scx.rac.svc.RacPermUrnSvc;

/**
 * 权限URN
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
@Slf4j
public class RacPermUrnCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacPermUrnSvc svc;

    /**
     * 添加权限URN
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "添加权限URN")
    @Parameters({ @Parameter(name = "RacPermUrnMo", description = "权限URN") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "添加权限URN的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = IdRo.class))) })
    @PostMapping("/rac/perm-urn")
    Mono<Ro> add(@RequestBody final RacPermUrnMo mo) {
        log.info("received post:/rac/perm-urn");
        return Mono.create(callback -> callback.success(svc.add(mo)));
    }

    /**
     * 修改权限URN
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "修改权限URN")
    @Parameters({ @Parameter(name = "RacPermUrnMo", description = "权限URN") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "修改权限URN的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = Ro.class))) })
    @PutMapping("/rac/perm-urn")
    Mono<Ro> modify(@RequestBody final RacPermUrnMo mo) throws Exception {
        log.info("received put:/rac/perm-urn");
        return Mono.create(callback -> callback.success(svc.modify(mo)));
    }

    /**
     * 删除权限URN
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "删除权限URN")
    @Parameters({ @Parameter(name = "id", description = "权限URNID") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "删除权限URN的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = Ro.class))) })
    @DeleteMapping("/rac/perm-urn")
    Mono<Ro> del(@RequestParam("id") final java.lang.Long id) {
        log.info("received delete:/rac/perm-urn");
        return Mono.create(callback -> callback.success(svc.del(id)));
    }

    /**
     * 获取单个权限URN
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "获取单个权限URN")
    @Parameters({ @Parameter(name = "id", description = "权限URNID") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "获取单个权限URN的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = RacPermUrnMo.class))) })
    @GetMapping("/rac/perm-urn/get-by-id")
    Mono<RacPermUrnMo> getById(@RequestParam("id") final java.lang.Long id) {
        log.info("received get:/rac/perm-urn/get-by-id");
        return Mono.create(callback -> callback.success(svc.getById(id)));
    }

    /**
     * 查询权限URN
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "查询权限URN列表")
    @Parameters({ // 
    // 
    // 
    @Parameter(name = "qo", description = "查询权限URN的条件"), // 
    @Parameter(name = "pageNum", description = "查询第几页"), @Parameter(name = "pageSize", description = "查询分页的大小"), @Parameter(name = "orderBy", description = "查询权限URN的排序规则") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "查询权限URN列表的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = RacPermUrnMo.class))) })
    @GetMapping("/rac/perm-urn/list")
    Mono<PageInfo<RacPermUrnMo>> list(final RacPermUrnMo qo, @RequestParam(value = "pageNum", required = false) final Integer pageNum, @RequestParam(value = "pageSize", required = false) final Integer pageSize, @RequestParam(value = "orderBy", required = false) final String orderBy) {
        log.info("received get:/rac/perm-urn/list");
        return Mono.create(callback -> callback.success(svc.list(qo, pageNum, pageSize, orderBy)));
    }
}
