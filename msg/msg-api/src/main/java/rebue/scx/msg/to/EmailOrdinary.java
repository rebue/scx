package rebue.scx.msg.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class EmailOrdinary implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 邮箱标题
     */
    private String            title;

    /**
     * 邮箱文本内容
     */
    private String            text;
    /**
     * 接受邮箱人
     */
    private String[]          datas;

}
