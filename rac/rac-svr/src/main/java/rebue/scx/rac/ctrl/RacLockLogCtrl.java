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
import rebue.scx.rac.api.RacLockLogApi;
import rebue.scx.rac.mo.RacLockLogMo;
import rebue.scx.rac.to.RacLockLogAddTo;
import rebue.scx.rac.to.RacLockLogModifyTo;
import rebue.scx.rac.to.RacLockLogPageTo;
import rebue.wheel.turing.JwtUtils;

/**
 * 锁定日志控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacLockLogCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacLockLogApi api;

    /**
     * 添加锁定日志
     *
     * @param to 添加的具体信息
     */
    @PostMapping("/rac/lock-log")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacLockLogAddTo to,@CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken) {
    	if (StringUtils.isBlank(jwtToken)) {
            throw new IllegalArgumentException("在Cookie中找不到JWT签名");
        }
        to.setLockOpId(JwtUtils.getJwtAccountIdInSign(jwtToken));
    	to.setLockDatetime(LocalDateTime.now());
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改锁定日志的信息
     *
     * @param to 修改的具体数据
     */
    @PutMapping("/rac/lock-log")
    public Mono<Ro<?>> modify(@RequestBody final RacLockLogModifyTo to,@CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken) {
    	if (StringUtils.isBlank(jwtToken)) {
            throw new IllegalArgumentException("在Cookie中找不到JWT签名");
        }
        to.setLockOpId(JwtUtils.getJwtAccountIdInSign(jwtToken));
    	to.setUnlockDatetime(LocalDateTime.now());
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除锁定日志
     *
     * @param id 锁定日志ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/lock-log")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个锁定日志的信息
     *
     * @param id 锁定日志ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/lock-log/get-by-id")
    public Mono<Ro<PojoRa<RacLockLogMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断锁定日志是否存在
     *
     * @param id 锁定日志ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/lock-log/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询锁定日志的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/lock-log/page")
    public Mono<Ro<PageRa<RacLockLogMo>>> page(final RacLockLogPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }
}
