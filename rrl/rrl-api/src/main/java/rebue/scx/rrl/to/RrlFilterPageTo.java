package rebue.scx.rrl.to;

import java.io.Serializable;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.EqualsAndHashCode;
import rebue.robotech.to.PageTo;

/**
 * 过滤器
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(Include.NON_NULL)
public class RrlFilterPageTo extends PageTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 请求方法
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 10, message = "请求方法的长度不能大于10")
    private String            method;

    /**
     * 请求地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 512, message = "请求地址的长度不能大于512")
    private String            uri;
}
