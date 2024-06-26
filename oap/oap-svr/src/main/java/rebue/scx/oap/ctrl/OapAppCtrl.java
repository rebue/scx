package rebue.scx.oap.ctrl;

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
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.oap.api.OapAppApi;
import rebue.scx.oap.mo.OapAppMo;
import rebue.scx.oap.mo.ex.OapAppListAndRacAppListRa;
import rebue.scx.oap.to.OapAppAddTo;
import rebue.scx.oap.to.OapAppListTo;
import rebue.scx.oap.to.OapAppModifyTo;
import rebue.scx.oap.to.OapAppPageTo;
import rebue.scx.rac.ann.RacOpLog;
import rebue.wheel.core.RandomEx;
import rebue.wheel.turing.JwtUtils;

/**
 * 第三方应用
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class OapAppCtrl {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Resource
    private OapAppApi api;

    /**
     * 添加第三方应用
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "添加应用认证信息", opTitle = "添加应用认证信息: #{#p0.appId}")
    @PostMapping("/oap/app")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final OapAppAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改第三方应用的信息
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "修改应用认证信息", opTitle = "修改应用认证信息: #{#p0.appId}")
    @PutMapping("/oap/app")
    public Mono<Ro<?>> modify(@RequestBody final OapAppModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 删除第三方应用
     *
     * @mbg.dontOverWriteAnnotation
     * @param id 第三方应用ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "删除应用认证信息", opTitle = "删除应用认证信息: #{#p0}")
    @DeleteMapping("/oap/app")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个第三方应用的信息
     *
     * @param id 第三方应用ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/app/get-by-id")
    public Mono<Ro<PojoRa<OapAppMo>>> getById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.getById(id)));
    }

    /**
     * 获取单个第三方应用的信息
     *
     * @param id 通过rac_app的ID关联查询
     */
    @GetMapping("/oap/app/get-by-app-id")
    public Mono<Ro<?>> getByAppId(@RequestParam("id") final String id) {
        return Mono.create(callback -> callback.success(api.getByAppId(id)));
    }

    /**
     * 生成应用secret
     */
    @GetMapping("/oap/app/get-app-secret")
    public Mono<Ro<?>> getAppSecret() {
        OapAppMo oapAppMo = new OapAppMo();
        oapAppMo.setSecret(RandomEx.randomUUID());
        return Mono.create(callback -> callback.success(new Ro<>(ResultDic.SUCCESS, "生成成功", oapAppMo)));
    }

    /**
     * 判断第三方应用是否存在
     *
     * @param id 第三方应用ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/app/exist-by-id")
    public Mono<Ro<BooleanRa>> existById(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.existById(id)));
    }

    /**
     * 查询第三方应用的信息
     *
     * @param qo 查询的具体条件
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/oap/app/page")
    public Mono<Ro<PageRa<OapAppMo>>> page(final OapAppPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 查询应用的信息并附带第三方应用的信息
     *
     * @param qo 查询的具体条件(查询所有，及条件为空)
     */
    @GetMapping("/oap/app/list-and-tripartite")
    public Mono<Ro<OapAppListAndRacAppListRa>> listAndTripartite(@CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken, final OapAppListTo qo) {
        if (StringUtils.isBlank(jwtToken)) {
            throw new IllegalArgumentException("在Cookie中找不到JWT签名");
        }
        // 从JWT签名中获取当前账户ID
        final Long curAccountId = JwtUtils.getJwtAccountIdFromSign(jwtToken);
        if (curAccountId == null) {
            throw new IllegalArgumentException("在JWT签名中找不到账户ID");
        }
        qo.setAccountId(curAccountId);
        return Mono.create(callback -> callback.success(api.listAndTripartite(qo)));
    }
}
