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
    DingTalk("dingtalk", "钉钉"),
    /**
     * 微信开放平台
     */
    WeChatOpen("wechat-open", "微信开放平台");

    private final String code;
    private final String desc;
}
