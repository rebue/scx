package rebue.scx.rac.to;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 应用
 *
 * @mbg.generated 自动生成，如需修改，请删除本行
 */
@Data
@JsonInclude(Include.NON_NULL)
public class RacAppModifyTo implements Serializable {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     * 应用ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @NotBlank(message = "应用ID不能为空")
    @Length(max = 32, message = "应用ID的长度不能大于32")
    private String            id;

    /**
     * 应用名称
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 20, message = "应用名称的长度不能大于20")
    private String            name;

    /**
     * 应用备注
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 50, message = "应用备注的长度不能大于50")
    private String            remark;

    /**
     * 应用URL
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 100, message = "应用URL的长度不能大于100")
    private String            url;

    /**
     * 菜单
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 3000, message = "菜单的长度不能大于3000")
    private String            menu;

    /**
     * 领域ID
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 32, message = "领域ID的长度不能大于32")
    private String            realmId;

    /**
     * 是否启用(如果应用没有启用，则不显示在第三方认证页面）
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean           isEnabled;

    /**
     * 应用图片地址
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Length(max = 512, message = "应用图片地址的长度不能大于512")
    private String            imgUrl;

    /**
     * 顺序号
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Byte              seqNo;

    /**
     * 是否认证(@deprecated 请使用认证方式判断)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean           isCertified;

    /**
     * 字典项ID(list集合)
     *
     */
    private List<Long>        dicItemIds;
    /**
     * 认证方式(0:未认证;1:共用Cookie;2:OIDC/OAuth2;3:CAS)
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @PositiveOrZero(message = "认证方式不能为负数")
    private Byte              authnType;
}
