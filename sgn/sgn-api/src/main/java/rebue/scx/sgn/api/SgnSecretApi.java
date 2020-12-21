package rebue.scx.sgn.api;

import rebue.robotech.ra.StringRa;
import rebue.robotech.ro.Ro;
import rebue.scx.sgn.to.SgnSecretAddTo;
import rebue.scx.sgn.to.SgnSecretModifyTo;

/**
 * 签名密钥API
 */
public interface SgnSecretApi {
    /**
     * 添加
     */
    Ro<?> add(SgnSecretAddTo to);

    /**
     * 修改
     */
    Ro<?> modify(SgnSecretModifyTo to);

    /**
     * 删除
     */
    Ro<?> del(String id);

    Ro<StringRa> getSecretById(String id);
}
