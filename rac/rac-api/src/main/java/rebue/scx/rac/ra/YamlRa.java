package rebue.scx.rac.ra;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 配置信息
 *
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class YamlRa implements Serializable {

    private static final long         serialVersionUID = 1L;
    /**
     * 钉钉配置
     */
    private List<Map<String, String>> dingTalkMapList;

    /**
     * 微信(公众号)配置
     */
    private List<Map<String, String>> wechatOpenMapList;

}
