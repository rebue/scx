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
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.svc.RacOrgSvc;

/**
 * 组织信息
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
@Slf4j
public class RacOrgCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacOrgSvc svc;

    /**
     * 添加组织信息
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "添加组织信息")
    @Parameters({ @Parameter(name = "RacOrgMo", description = "组织信息") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "添加组织信息的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = IdRo.class))) })
    @PostMapping("/rac/org")
    Mono<Ro> add(@RequestBody final RacOrgMo mo) {
        log.info("received post:/rac/org");
        return Mono.create(callback -> callback.success(svc.add(mo)));
    }

    /**
     * 修改组织信息
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "修改组织信息")
    @Parameters({ @Parameter(name = "RacOrgMo", description = "组织信息") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "修改组织信息的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = Ro.class))) })
    @PutMapping("/rac/org")
    Mono<Ro> modify(@RequestBody final RacOrgMo mo) throws Exception {
        log.info("received put:/rac/org");
        return Mono.create(callback -> callback.success(svc.modify(mo)));
    }

    /**
     * 删除组织信息
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "删除组织信息")
    @Parameters({ @Parameter(name = "id", description = "组织信息ID") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "删除组织信息的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = Ro.class))) })
    @DeleteMapping("/rac/org")
    Mono<Ro> del(@RequestParam("id") final java.lang.Long id) {
        log.info("received delete:/rac/org");
        return Mono.create(callback -> callback.success(svc.del(id)));
    }

    /**
     * 获取单个组织信息
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "获取单个组织信息")
    @Parameters({ @Parameter(name = "id", description = "组织信息ID") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "获取单个组织信息的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = RacOrgMo.class))) })
    @GetMapping("/rac/org/get-by-id")
    Mono<RacOrgMo> getById(@RequestParam("id") final java.lang.Long id) {
        log.info("received get:/rac/org/get-by-id");
        return Mono.create(callback -> callback.success(svc.getById(id)));
    }

    /**
     * 查询组织信息
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "查询组织信息列表")
    @Parameters({ // 
    // 
    // 
    @Parameter(name = "qo", description = "查询组织信息的条件"), // 
    @Parameter(name = "pageNum", description = "查询第几页"), @Parameter(name = "pageSize", description = "查询分页的大小"), @Parameter(name = "orderBy", description = "查询组织信息的排序规则") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "查询组织信息列表的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = RacOrgMo.class))) })
    @GetMapping("/rac/org/list")
    Mono<PageInfo<RacOrgMo>> list(final RacOrgMo qo, @RequestParam(value = "pageNum", required = false) final Integer pageNum, @RequestParam(value = "pageSize", required = false) final Integer pageSize, @RequestParam(value = "orderBy", required = false) final String orderBy) {
        log.info("received get:/rac/org/list");
        return Mono.create(callback -> callback.success(svc.list(qo, pageNum, pageSize, orderBy)));
    }
}
