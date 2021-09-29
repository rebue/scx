package rebue.scx.orp.core.dic;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * ORP类型的字典
 * 
 * @author zbz
 *
 */
@Getter
@AllArgsConstructor
public enum OrpTypeDic {
    /**
     * 钉钉
     */
    DingTalk,
    /**
     * 微信开放平台
     */
    WechatOpen,
    /**
     * 平台本身oidc认证
     */
    Oidc

}
