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
import rebue.scx.rac.mo.RacOpLogMo;
import rebue.scx.rac.svc.RacOpLogSvc;

/**
 * 用户操作日志
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
@Slf4j
public class RacOpLogCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacOpLogSvc svc;

    /**
     * 添加用户操作日志
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "添加用户操作日志")
    @Parameters({ @Parameter(name = "RacOpLogMo", description = "用户操作日志") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "添加用户操作日志的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = IdRo.class))) })
    @PostMapping("/rac/op-log")
    Mono<Ro> add(@RequestBody final RacOpLogMo mo) {
        log.info("received post:/rac/op-log");
        return Mono.create(callback -> callback.success(svc.add(mo)));
    }

    /**
     * 修改用户操作日志
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "修改用户操作日志")
    @Parameters({ @Parameter(name = "RacOpLogMo", description = "用户操作日志") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "修改用户操作日志的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = Ro.class))) })
    @PutMapping("/rac/op-log")
    Mono<Ro> modify(@RequestBody final RacOpLogMo mo) throws Exception {
        log.info("received put:/rac/op-log");
        return Mono.create(callback -> callback.success(svc.modify(mo)));
    }

    /**
     * 删除用户操作日志
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "删除用户操作日志")
    @Parameters({ @Parameter(name = "id", description = "用户操作日志ID") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "删除用户操作日志的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = Ro.class))) })
    @DeleteMapping("/rac/op-log")
    Mono<Ro> del(@RequestParam("id") final java.lang.Long id) {
        log.info("received delete:/rac/op-log");
        return Mono.create(callback -> callback.success(svc.del(id)));
    }

    /**
     * 获取单个用户操作日志
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "获取单个用户操作日志")
    @Parameters({ @Parameter(name = "id", description = "用户操作日志ID") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "获取单个用户操作日志的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = RacOpLogMo.class))) })
    @GetMapping("/rac/op-log/get-by-id")
    Mono<RacOpLogMo> getById(@RequestParam("id") final java.lang.Long id) {
        log.info("received get:/rac/op-log/get-by-id");
        return Mono.create(callback -> callback.success(svc.getById(id)));
    }

    /**
     * 查询用户操作日志
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Operation(summary = "查询用户操作日志列表")
    @Parameters({ // 
    // 
    // 
    @Parameter(name = "qo", description = "查询用户操作日志的条件"), // 
    @Parameter(name = "pageNum", description = "查询第几页"), @Parameter(name = "pageSize", description = "查询分页的大小"), @Parameter(name = "orderBy", description = "查询用户操作日志的排序规则") })
    @ApiResponses({ @// 
    // 
    ApiResponse(description = "查询用户操作日志列表的结果", content = @Content(mediaType = "application/json; charset=utf-8", schema = @Schema(implementation = RacOpLogMo.class))) })
    @GetMapping("/rac/op-log/list")
    Mono<PageInfo<RacOpLogMo>> list(final RacOpLogMo qo, @RequestParam(value = "pageNum", required = false) final Integer pageNum, @RequestParam(value = "pageSize", required = false) final Integer pageSize, @RequestParam(value = "orderBy", required = false) final String orderBy) {
        log.info("received get:/rac/op-log/list");
        return Mono.create(callback -> callback.success(svc.list(qo, pageNum, pageSize, orderBy)));
    }
}
