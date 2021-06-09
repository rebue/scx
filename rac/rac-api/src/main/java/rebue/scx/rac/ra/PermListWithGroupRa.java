package rebue.scx.rac.ra;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import rebue.scx.rac.mo.RacPermGroupMo;
import rebue.scx.rac.mo.RacPermMo;

/**
 * 当前权限信息
 *
 */
@Data
@NoArgsConstructor
// @RequiredArgsConstructor // 不知道@Data中默认包含的@RequiredArgsConstructor为何没起效
@JsonInclude(Include.NON_NULL)
public class PermListWithGroupRa implements Serializable {

    private static final long    serialVersionUID = 1L;

    /**
     * 权限分组列表
     */
    private List<RacPermGroupMo> groupList;

    /**
     * 权限列表
     */
    private List<RacPermMo>      permList;

}
