package rebue.scx.rac.ra;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import rebue.scx.rac.mo.RacAccountRoleMo;
import rebue.scx.rac.mo.RacRoleMo;

/**
 * 返回结果
 *
 * @param <T> 返回附加内容的类型
 */
@Data
@JsonInclude(Include.NON_NULL)
public class ListTransferOfRoleRa implements Serializable {
    private static final long      serialVersionUID = 1L;

    /**
     * 已存在的角色Id列表
     */
    private List<RacAccountRoleMo> existList;

    /**
     * 角色列表
     */
    private List<RacRoleMo>        addableList;

}