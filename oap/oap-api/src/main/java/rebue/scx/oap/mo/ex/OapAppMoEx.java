package rebue.scx.oap.mo.ex;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import rebue.scx.oap.mo.OapAppMo;

@AllArgsConstructor
@ToString
public class OapAppMoEx extends OapAppMo {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * 白名单IP
     */
    @Getter
    @Setter
    private List<String>      ipAddrs;
    /**
     * 重定向地址
     */
    @Getter
    @Setter
    private List<String>      redirectUris;

    public OapAppMoEx() {
        super();
    }

}
