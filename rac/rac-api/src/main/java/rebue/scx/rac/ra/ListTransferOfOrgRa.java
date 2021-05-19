package rebue.scx.rac.ra;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.pagehelper.PageInfo;

import lombok.Data;
import lombok.NoArgsConstructor;
import rebue.scx.rac.mo.RacAccountMo;

/**
 * 返回结果
 *
 * @param <T> 返回附加内容的类型
 */
@Data
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ListTransferOfOrgRa implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 权限分组列表
	 */
	private PageInfo<RacAccountMo> addableList;

	/**
	 * 权限列表
	 */
	private List<RacAccountMo> existList;

}