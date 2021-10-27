package rebue.scx.rac.ctrl;

import java.io.IOException;
import java.io.SequenceInputStream;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.BooleanRa;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ra.ListRa;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ra.PojoRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.ann.RacOpLog;
import rebue.scx.rac.api.RacAccountApi;
import rebue.scx.rac.co.RacCookieCo;
import rebue.scx.rac.co.RacJwtSignCo;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.ra.GetCurAccountInfoRa;
import rebue.scx.rac.ra.ListTransferOfOrgRa;
import rebue.scx.rac.to.RacAccountAddTo;
import rebue.scx.rac.to.RacAccountModifySignInByOldPswdTo;
import rebue.scx.rac.to.RacAccountModifySignInPswdTo;
import rebue.scx.rac.to.RacAccountModifyTo;
import rebue.scx.rac.to.RacAccountPageTo;
import rebue.scx.rac.to.RacDisableLogAddTo;
import rebue.scx.rac.to.RacDisableLogModifyTo;
import rebue.scx.rac.to.ex.PostParameterTo;
import rebue.scx.rac.to.ex.RacAccountByUserTo;
import rebue.scx.rac.to.ex.RacAccountMobileTo;
import rebue.scx.rac.to.ex.RacAccountResetPasswordTo;
import rebue.scx.rac.to.ex.RacAccountUnionIdTo;
import rebue.scx.rac.to.ex.RacListTransferOfOrgTo;
import rebue.wheel.api.exception.RuntimeExceptionX;
import rebue.wheel.turing.JwtUtils;

/**
 * 账户
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
     * @mbg.dontOverWriteAnnotation
     * @param to 添加的具体信息
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "账户添加", opTitle = "账户添加: #{#p0.signInName}")
    @PostMapping("/rac/account")
    public Mono<Ro<IdRa<java.lang.Long>>> add(@RequestBody final RacAccountAddTo to) {
        return Mono.create(callback -> callback.success(api.add(to)));
    }

    /**
     * 修改账户的信息
     *
     * @mbg.dontOverWriteAnnotation
     * @param to 修改的具体数据
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "账户修改", opTitle = "账户修改: #{#p0.signInName}")
    @PutMapping("/rac/account")
    public Mono<Ro<?>> modify(@RequestBody final RacAccountModifyTo to) {
        return Mono.create(callback -> callback.success(api.modify(to)));
    }

    /**
     * 添加账户unionId映射
     *
     * @param to 添加的具体信息
     */
    @RacOpLog(opType = "添加账户unionId", opTitle = "添加账户unionId: #{#p0.srcId}")
    @PostMapping("/rac/account/add-union-mapper")
    public Mono<Ro<?>> addUnionIdMapper(@RequestBody final RacAccountUnionIdTo to) {
        return Mono.create(callback -> callback.success(api.addUnionIdMapper(to)));
    }

    // /**
    // * 修改账户unionId映射
    // *
    // * @param to 修改的具体信息
    // *
    // */
    // @RacOpLog(opType = "修改账户unionId", opTitle = "修改账户unionId: #{#p0.srcId}")
    // @PostMapping("/rac/account/modify-union-mapper")
    // public Mono<Ro<?>> modifyUnionIdMapper(@RequestBody final RacAccountUnionIdTo to) {
    // return Mono.create(callback -> callback.success(api.modifyUnionIdMapper(to)));
    // }
    /**
     * 删除账户unionId映射
     *
     * @param to 删除的具体信息
     */
    @RacOpLog(opType = "删除账户unionId", opTitle = "删除账户unionId: #{#p0.srcId}")
    @PostMapping("/rac/account/del-union-mapper")
    public Mono<Ro<?>> delUnionIdMapper(@RequestBody final RacAccountUnionIdTo to) {
        return Mono.create(callback -> callback.success(api.delUnionIdMapper(to)));
    }

    /**
     * 账户解除关联用户
     *
     * @param id 需要解除的账户ID
     */
    @RacOpLog(opType = "解除关联用户", opTitle = "解除关联用户: #{#p0.id}")
    @PostMapping("/rac/account/disassociate-user")
    public Mono<Ro<?>> disassociateUser(@RequestBody final PostParameterTo to) {
        return Mono.create(callback -> callback.success(api.disassociateUser(to.getId())));
    }

    /**
     * 管理员解除账户绑定钉钉
     *
     * @param id 被解绑的账户ID
     */
    @RacOpLog(opType = "解绑账户钉钉", opTitle = "解绑账户钉钉: #{#p0.id}")
    @PostMapping("/rac/account/unbind-ding-talk")
    public Mono<Ro<?>> unbindDdModify(@RequestBody final PostParameterTo to) {
        return Mono.create(callback -> callback.success(api.unbindDdModify(to.getId())));
    }

    /**
     * 管理员解除账户绑定微信
     *
     * @param id 被解绑的账户ID
     */
    @RacOpLog(opType = "解绑账户微信", opTitle = "解绑账户微信: #{#p0.id}")
    @PostMapping("/rac/account/unbind-wechat-open")
    public Mono<Ro<?>> unbindWxModify(@RequestBody final PostParameterTo to) {
        return Mono.create(callback -> callback.success(api.unbindWxModify(to.getId())));
    }

    /**
     * 管理员解除账户绑定手机号
     *
     * @param id 被解绑的账户ID
     */
    @RacOpLog(opType = "解绑账户手机号", opTitle = "解绑账户手机号: #{#p0.id}")
    @PostMapping("/rac/account/unbind-mobile")
    public Mono<Ro<?>> unbindMobile(@RequestBody final PostParameterTo to) {
        return Mono.create(callback -> callback.success(api.unbindMobile(to.getId())));
    }

    /**
     * 账户绑定/解绑手机号
     *
     * @param to 账户ID/手机号/校验码/绑定类型
     */
    @RacOpLog(opType = "账户绑定手机号", opTitle = "账户绑定手机号: #{#p0.id}")
    @PostMapping("/rac/account/bind-mobile")
    public Mono<Ro<?>> bindMobile(@RequestBody final RacAccountMobileTo to) {
        return Mono.create(callback -> callback.success(api.bindMobile(to)));
    }

    /**
     * 判断手机号是否已被绑定注册
     *
     * @param id     账户ID
     * @param mobile 手机号
     */
    @GetMapping("/rac/account/exist-mobile-by-id")
    public Mono<Ro<BooleanRa>> existMobileById(@RequestParam("id") final java.lang.Long id, @RequestParam("mobile") final int mobile) {
        return Mono.create(callback -> callback.success(api.existMobileById(id, mobile)));
    }

    /**
     * 删除账户
     *
     * @mbg.dontOverWriteAnnotation
     * @param id 账户ID
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @RacOpLog(opType = "账户删除", opTitle = "账户删除: #{#p0}")
    @DeleteMapping("/rac/account")
    public Mono<Ro<?>> del(@RequestParam("id") final java.lang.Long id) {
        return Mono.create(callback -> callback.success(api.del(id)));
    }

    /**
     * 获取单个账户的信息
     *
     * @param id 账户ID
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
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/rac/account/page")
    public Mono<Ro<PageRa<RacAccountMo>>> page(final RacAccountPageTo qo) {
        return Mono.create(callback -> callback.success(api.page(qo)));
    }

    /**
     * 修改账户登录密码
     *
     * @param to 修改账户登录密码的具体数据
     */
    @RacOpLog(opType = "账户密码修改", opTitle = "账户密码修改: #{#p0.id}")
    @PutMapping("/rac/account/modify-sign-in-pswd")
    public Mono<Ro<?>> modifySignInPswd(@RequestBody final RacAccountModifySignInPswdTo to) {
        return Mono.create(callback -> callback.success(api.modifySignInPswd(to)));
    }

    /**
     * 重置账户登录密码(12345678)
     *
     * @param to 修改账户登录密码的具体数据
     */
    @RacOpLog(opType = "重置账户密码", opTitle = "重置账户密码: #{#p0.id}")
    @PostMapping("/rac/account/sign-in-reset-password")
    public Mono<Ro<?>> resetPassword(@RequestBody final RacAccountResetPasswordTo to) {
        return Mono.create(callback -> callback.success(api.resetPassword(to)));
    }

    /**
     * 根据旧登录密码更新新登录密码
     *
     * @param to 修改账户登录密码的具体数据
     */
    @RacOpLog(opType = "账户密码修改", opTitle = "账户密码修改: #{#p0.id}")
    @PutMapping("/rac/account/modify-sign-in-by-old-pswd")
    public Mono<Ro<?>> modifySignInByOldPswd(@RequestBody final RacAccountModifySignInByOldPswdTo to) {
        return Mono.create(callback -> callback.success(api.modifySignInByOldPswd(to)));
    }

    /**
     * 启用账户
     *
     * @param to 启用的具体数据
     */
    @RacOpLog(opType = "启用账户", opTitle = "启用账户: #{#p0.accountId}")
    @PutMapping("/rac/account/enable")
    public Mono<Ro<?>> enable(@RequestBody final RacDisableLogModifyTo qo, @CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken) {
        if (StringUtils.isBlank(jwtToken)) {
            throw new IllegalArgumentException("在Cookie中找不到JWT签名");
        }
        qo.setEnableOpId(JwtUtils.getJwtAccountIdFromSign(jwtToken));
        qo.setEnableDatetime(LocalDateTime.now());
        return Mono.create(callback -> callback.success(api.enable(qo)));
    }

    /**
     * 禁用账户
     *
     * @param to 禁用的具体数据
     */
    @RacOpLog(opType = "禁用账户", opTitle = "禁用账户: #{#p0.accountId}")
    @PutMapping("/rac/account/disable")
    public Mono<Ro<?>> disable(@RequestBody final RacDisableLogAddTo qo, @CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken) {
        if (StringUtils.isBlank(jwtToken)) {
        }
        qo.setDisableOpId(JwtUtils.getJwtAccountIdFromSign(jwtToken));
        qo.setDisableDatetime(LocalDateTime.now());
        return Mono.create(callback -> callback.success(api.disable(qo)));
    }

    /**
     * 上传头像
     *
     * @throws IOException
     */
    @PostMapping(value = "/rac/account/upload-avatar")
    public Mono<?> uploadAvatar(@CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken, @RequestPart("avatar") final Flux<FilePart> filePartFlux,
        final ServerHttpResponse response) {
        if (StringUtils.isBlank(jwtToken)) {
            throw new IllegalArgumentException("在Cookie中找不到JWT签名");
        }
        final Long curAccountId = JwtUtils.getJwtAccountIdFromSign(jwtToken);
        if (curAccountId == null) {
            throw new IllegalArgumentException("在JWT签名中找不到账户ID");
        }
        return filePartFlux.flatMap(filePart -> {
            final String fileName = filePart.filename();
            final ContentDisposition contentDisposition = filePart.headers().getContentDisposition();
            final MediaType contentType = filePart.headers().getContentType();
            return filePart.content().map(dataBuffer -> dataBuffer.asInputStream(true)).reduce(SequenceInputStream::new).map(inputStream -> {
                final Ro<?> ro = api.uploadAvatar(curAccountId, fileName, contentDisposition.toString(), contentType.toString(), inputStream);
                if (!ResultDic.SUCCESS.equals(ro.getResult())) {
                    response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                }
                return ro;
            });
        }).next();
    }

    /**
     * 获取当前账户信息
     *
     * @ignoreParams request
     */
    @GetMapping("/rac/account/get-cur-account-info")
    @SneakyThrows
    public Mono<Ro<GetCurAccountInfoRa>> getCurAccountInfo(@CookieValue(JwtUtils.JWT_TOKEN_NAME) final String jwtToken, final ServerHttpRequest request) {
        if (StringUtils.isBlank(jwtToken)) {
            throw new IllegalArgumentException("在Cookie中找不到JWT签名");
        }
        // 从JWT签名中获取当前账户ID
        final Long curAccountId = JwtUtils.getJwtAccountIdFromSign(jwtToken);
        if (curAccountId == null) {
            throw new IllegalArgumentException("在JWT签名中找不到账户ID");
        }
        // 从JWT签名中获取代理账户ID
        Long agentAccountId = null;
        final Object agentAccountIdItem = JwtUtils.getJwtAdditionItemFromSign(jwtToken, RacJwtSignCo.AGENT_ACCOUNT_ID);
        if (agentAccountIdItem != null) {
            final String agentAccountIdString = agentAccountIdItem.toString();
            if (StringUtils.isNotBlank(agentAccountIdString)) {
                agentAccountId = Long.valueOf(agentAccountIdString);
            }
        }
        final Long agentAccountIdFinal = agentAccountId;
        // 从Headers中获取应用ID
        final List<String> list = request.getHeaders().get(RacCookieCo.HEADERS_APP_ID_KEY);
        final String appId = list != null && list.size() > 0 ? list.get(0) : null;
        if (StringUtils.isBlank(appId)) {
            throw new RuntimeExceptionX("在Headers中找不到应用ID");
        }
        return Mono.create(callback -> callback.success(api.getCurAccountInfo(curAccountId, agentAccountIdFinal, appId)));
    }

    /**
     * 查询账户的信息
     *
     * @param qo 查询的具体条件
     *
     * @return
     */
    @GetMapping("/rac/account/listTransferOfOrg")
    public Mono<Ro<ListTransferOfOrgRa>> listTransferOfOrg(final RacListTransferOfOrgTo qo) {
        return Mono.create(callback -> callback.success(api.listTransferOfOrg(qo)));
    }

    /**
     * 根据账户ID领域ID关键字查询该领域下账户(用户的下帐号)的信息
     *
     * @param to 查询的具体条件
     *
     * @return
     */
    @GetMapping("/rac/account/get-account-by-user")
    public Mono<Ro<PageRa<RacAccountMo>>> getAccountByUser(final RacAccountByUserTo to) {
        return Mono.create(callback -> callback.success(api.getAccountByUser(to)));
    }

    /**
     * 通过unionId查询账户
     *
     * @param unionId
     *
     * @return
     */
    @GetMapping("/rac/account/get-account-by-union-id")
    public Mono<Ro<ListRa<RacAccountMo>>> getAccountByUnionId(@RequestParam("unionId") final java.lang.Long unionId) {
        return Mono.create(callback -> callback.success(api.getAccountByUnionId(unionId)));
    }

    /**
     * 根据用户ID查询用户下的账户的信息
     *
     * @param userId
     *
     * @return
     */
    @GetMapping("/rac/account/get-by-user-id")
    public Mono<Ro<ListRa<RacAccountMo>>> getByUserId(@RequestParam("userId") final java.lang.Long userId) {
        return Mono.create(callback -> callback.success(api.getByUserId(userId)));
    }
}
