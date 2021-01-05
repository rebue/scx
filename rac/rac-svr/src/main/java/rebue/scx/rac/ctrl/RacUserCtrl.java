package rebue.scx.rac.ctrl;

import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacUserApi;
import rebue.scx.rac.co.RacCo;
import rebue.scx.rac.mo.RacUserMo;
import rebue.scx.rac.ra.GetCurUserInfoRa;
import rebue.scx.rac.to.RacUserAddTo;
import rebue.scx.rac.to.RacUserModifyTo;
import rebue.scx.rac.to.RacUserPageTo;
import rebue.wheel.CookieUtils;
import rebue.wheel.turing.JwtUtils;

/**
 * 用户控制器
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class RacUserCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private RacUserApi api;

    /**
     * 添加用户
     *
     * @param to 添加的具体信息
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PostMapping("/rac/user")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacUserAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改用户的信息
     *
     * @param to 修改的具体数据
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PutMapping("/rac/user")
    public Mono<Ro<?>> modify(@RequestBody final RacUserModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除用户
     *
     * @param id 用户ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DeleteMapping("/rac/user")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个用户的信息
     *
     * @param id 用户ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/user/get-by-id")
    public Mono<Ro<PojoRa<RacUserMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 判断用户是否存在
     *
     * @param id 用户ID
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/user/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询用户的信息
     *
     * @param qo 查询的具体条件
     * 
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/user/page")
    public Mono<Ro<PageRa<RacUserMo>>> page(final RacUserPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 获取当前用户信息
     */
    @GetMapping("/rac/user/get-cur-user-info")
    public Mono<Ro<GetCurUserInfoRa>> getCurUserInfo(final HttpServletRequest req) {
        try {
            final Long curUserId = JwtUtils.getJwtUserIdInCookie(req);
            if (curUserId == null) {
                throw new IllegalArgumentException("在Cookie中找不到用户ID");
            }
            final String curSysId = CookieUtils.getValue(req, RacCo.SYS_ID_KEY);
            if (StringUtils.isBlank(curSysId)) {
                throw new IllegalArgumentException("在Cookie中找不到系统ID");
            }
            return Mono.create(callback -> callback.success(api.getCurUserInfo(curUserId, curSysId)));
        } catch (final ParseException e) {
            e.printStackTrace();
            return Mono.create(callback -> callback.success(new Ro<>(ResultDic.WARN, "JWT解析Cookie出错")));
        }
    }
}
