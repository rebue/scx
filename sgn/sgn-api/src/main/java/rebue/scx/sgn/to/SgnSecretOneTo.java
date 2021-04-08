package rebue.scx.sgn.to;

import java.io.Serializable;

import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import rebue.robotech.valid.DicValid;
import rebue.scx.sgn.dic.SignAlgorithmDic;

/**
 * 签名密钥
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class SgnSecretOneTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 密钥
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 512, message = "密钥的长度不能大于512")
    private String            secret;

    /**
     * 算法
     *
     * @mbg.dontOverWriteAnnotation
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "算法不能为负数")
    @DicValid(target = SignAlgorithmDic.class, message = "不能识别的算法类型")
    private Byte              algorithm;
}
