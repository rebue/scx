package rebue.scx.sgn.test.http.ex;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import rebue.scx.sgn.to.SgnSecretModifyTo;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
public class SgnSecretModifyExTo extends SgnSecretModifyTo {

    private static final long serialVersionUID = 1L;

    private String            signId;

    private String            signResult;

    private Long              signTimestamp;
}
