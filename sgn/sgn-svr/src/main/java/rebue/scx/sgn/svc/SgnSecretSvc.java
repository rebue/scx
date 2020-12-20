package rebue.scx.sgn.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.svc.BaseSvc;
import rebue.scx.sgn.jo.SgnSecretJo;
import rebue.scx.sgn.mo.SgnSecretMo;
import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.scx.sgn.to.SgnSecretDelTo;
import rebue.scx.sgn.to.SgnSecretModifyTo;
import rebue.scx.sgn.to.SgnSecretOneTo;
import rebue.scx.sgn.to.SgnSecretPageTo;

/**
 * 签名密钥服务接口
 *
 * Validated注解按规范应该写在接口上
 *
 * Valid注解在参数是POJO类时，写在参数类型的前面，具体约束的注解写在参数类型的属性的上方
 * Valid注解在参数是普通参数时，写在方法的上方，具体约束的注解直接写在参数类型的前面
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Validated
public interface SgnSecretSvc extends BaseSvc<java.lang.String, SgnSecretAddTo, SgnSecretModifyTo, SgnSecretDelTo, SgnSecretOneTo, SgnSecretPageTo, SgnSecretMo, SgnSecretJo> {
}
