package rebue.scx.orp.core.mo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientMo {
    private String id;
    private String secret;
}
