package rebue.scx.rac.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.scx.rac.mo.RacSignInLogMo;

@Mapper
public interface RacSignInLogMapper extends MybatisBaseMapper<RacSignInLogMo, Long> {

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(RacSignInLogMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(RacSignInLogMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    RacSignInLogMo selectByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(RacSignInLogMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(RacSignInLogMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RacSignInLogMo> selectAll();

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<RacSignInLogMo> selectSelective(RacSignInLogMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(RacSignInLogMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(RacSignInLogMo record);
}
