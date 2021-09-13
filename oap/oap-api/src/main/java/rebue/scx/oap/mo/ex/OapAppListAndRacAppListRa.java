package rebue.scx.oap.mo.ex;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import rebue.scx.oap.mo.OapAppMo;

import lombok.Data;
import rebue.scx.rac.mo.RacAppMo;

@Data
@JsonInclude(Include.NON_NULL)
public class OapAppListAndRacAppListRa implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;
    /**
     * 第三方应用认证
     */
    private List<OapAppMo>    oapAppList;

    /**
     * 所有应用
     */
    private List<RacAppMo>    racAppList;

}
