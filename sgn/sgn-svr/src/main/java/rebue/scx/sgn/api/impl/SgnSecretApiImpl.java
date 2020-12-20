package rebue.scx.sgn.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.scx.sgn.api.SgnSecretApi;
import rebue.scx.sgn.jo.SgnSecretJo;
import rebue.scx.sgn.mo.SgnSecretMo;
import rebue.scx.sgn.svc.SgnSecretSvc;
import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.scx.sgn.to.SgnSecretDelTo;
import rebue.scx.sgn.to.SgnSecretModifyTo;
import rebue.scx.sgn.to.SgnSecretOneTo;
import rebue.scx.sgn.to.SgnSecretPageTo;

/**
 * 签名密钥API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class SgnSecretApiImpl
    extends BaseApiImpl<java.lang.Long, SgnSecretAddTo, SgnSecretModifyTo, SgnSecretDelTo, SgnSecretOneTo, SgnSecretPageTo, SgnSecretMo, SgnSecretJo, SgnSecretSvc>
    implements SgnSecretApi {
}
