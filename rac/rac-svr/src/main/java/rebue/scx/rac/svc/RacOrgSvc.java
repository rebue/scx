package rebue.scx.rac.svc;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import com.github.pagehelper.PageInfo;

import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacOrgJo;
import rebue.scx.rac.mo.RacOrgMo;
import rebue.scx.rac.to.RacOrgAccountAddTo;
import rebue.scx.rac.to.RacOrgAccountDelTo;
import rebue.scx.rac.to.RacOrgAddTo;
import rebue.scx.rac.to.RacOrgDelTo;
import rebue.scx.rac.to.RacOrgListTo;
import rebue.scx.rac.to.RacOrgModifyTo;
import rebue.scx.rac.to.RacOrgOneTo;
import rebue.scx.rac.to.RacOrgPageTo;
import rebue.scx.rac.to.ex.RacModifyOrgAccountTo;
import rebue.scx.rac.to.ex.RacOrgListByAccountIdTo;
import rebue.scx.rac.to.ex.RacOrgModifyDefaultOrgTo;

/**
 * 组织服务接口
 *
 * <pre>
 * 1. 在接口上方必须写上 @Validated 注解
 * 2. 参数是POJO类时用 @Valid 注解在参数类型的前面进行修饰
 *    参数是普通参数时，直接在参数类型的前面加上具体约束的注解
 * 3. (待验证)有分组时，在方法上方必须写上 @Validated 注解及分组
 * 4. 踩坑留痕：
 *    如果方法的返回值为void，在方法上方加上 @Valid 注解会出现异常，报HV000132错误
 * </pre>
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Validated
public interface RacOrgSvc extends BaseSvc<java.lang.Long, RacOrgAddTo, RacOrgModifyTo, RacOrgDelTo, RacOrgOneTo, RacOrgListTo, RacOrgPageTo, RacOrgMo, RacOrgJo> {

    /**
     * 添加组织账户关系
     *
     * @param to 添加的具体信息
     */
    void addOrgAccount(RacOrgAccountAddTo to);

    /**
     * 删除组织账户关系
     *
     * @param to 添加的具体信息
     */
    void delOrgAccount(RacOrgAccountDelTo to);

    /**
     * 查询当前账户所在的组织的信息
     *
     * @param qo 查询的具体条件
     */
    List<RacOrgMo> listByAccountId(RacOrgListByAccountIdTo qo);

    /**
     * 修改账户默认组织的信息
     *
     * @param to 修改的具体数据
     */
    void modifyDefaultOrg(RacOrgModifyDefaultOrgTo to);

    /**
     * 查询可以添加的组织信息
     *
     * @param qo 查询的具体条件
     */
    PageInfo<RacOrgMo> listAddableOrg(RacOrgListByAccountIdTo qo);

    /**
     * 更改组织与账户的关系
     *
     * @param to 修改的具体数据
     */
    void modifyOrgAccount(RacModifyOrgAccountTo to);

    /**
     * 表格展示的分页查询
     *
     * @param qo
     *
     * @return
     */
    PageInfo<RacOrgMo> pageIsTable(RacOrgPageTo qo);

    /**
     * 查询组织的信息
     *
     * @param qo 查询的具体条件
     */
    List<RacOrgMo> selectInAndByKeywordsList(List<Long> ids, RacOrgMo qo);
}
