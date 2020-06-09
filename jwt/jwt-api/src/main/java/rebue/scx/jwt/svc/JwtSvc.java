package rebue.scx.jwt.svc;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import rebue.scx.jwt.ro.JwtSignRo;
import rebue.scx.jwt.ro.JwtVerifyRo;
import rebue.scx.jwt.to.JwtSignTo;

@Validated
public interface JwtSvc {
    /**
     * JWT签名
     * 
     * @param to
     *           签名中储存的用户信息
     */
    JwtSignRo sign(@Valid JwtSignTo to);

    /**
     * 验证JWT签名
     * 
     * @param signToVerify
     *                     要验证的签名
     */
    @Valid
    JwtVerifyRo verify(@NotBlank(message = "要验证的签名不能为空") String signToVerify);
}
