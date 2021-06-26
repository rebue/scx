package rebue.scx.rac.mapper.ex;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import rebue.robotech.mybatis.MapperRootInterface;
import rebue.scx.rac.mo.RacAccountMo;
import rebue.scx.rac.mo.ex.RacUserAccountMo;
import rebue.scx.rac.to.ex.RacUserAccountPageTo;

@Mapper
public interface RacUserAccountMapper extends MapperRootInterface<RacAccountMo, Long> {

	/**
	 * 查询带有用户的账户列表
	 *
	 * @param qo 查询条件
	 *
	 * @return 查询到的分页信息
	 */
	@Select({ "<script>"
			+ "SELECT \n"
			+ "    ac.ID accountId,ac.*,us.*\n"
			+ "FROM\n"
			+ "    rac.RAC_ACCOUNT ac\n"
			+ "        JOIN\n"
			+ "    RAC_USER us ON ac.USER_ID = us.ID "
			+ "<where> "
			+ "<if test='record.orgId!=null'>"
			+ " and ac.ORG_ID = #{record.orgId}"
			+ "</if>"
			+ "<if test='record.keywords!=null'>"
			+ "  and( us.ID_CARD like '%${record.keywords}%' or us.MOBILE like '%${record.keywords}%' or us.REAL_NAME like '%${record.keywords}%')"
			+ "</if>"
			+ "</where>"
			+ "</script>"
	})
	List<RacUserAccountMo> page(@Param(value = "record") RacUserAccountPageTo record);

	/**
	 * 根据ID查询有用户信息的账户
	 *
	 * @param id
	 */
	@Select({ "<script>"
			+ "SELECT \n"
			+ "    ac.ID accountId,ac.*,us.*\n"
			+ "FROM\n"
			+ "    rac.RAC_ACCOUNT ac\n"
			+ "        JOIN\n"
			+ "    RAC_USER us ON ac.USER_ID = us.ID "
			+ " where us.ID=#{id}"
			+ "</script>"
	})
	RacUserAccountMo getByAccountId(@Param(value = "id") Long id);
}
