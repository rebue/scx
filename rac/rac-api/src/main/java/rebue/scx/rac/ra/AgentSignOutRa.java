package rebue.scx.rac.ra;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 退出代理登录返回的结果
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class AgentSignOutRa extends SignUpOrInRa {
    private static final long serialVersionUID = 1L;

    /**
     * 代理之前的URL
     */
    private String            urlBeforeAgent;

    public AgentSignOutRa(final Long accountId, final String urlBeforeAgent, final String sign, final LocalDateTime expirationTime) {
        super(accountId, sign, expirationTime);
        this.urlBeforeAgent = urlBeforeAgent;
    }

}
