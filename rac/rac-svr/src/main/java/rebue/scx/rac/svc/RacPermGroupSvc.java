package rebue.scx.rac.svc;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacPermGroupJo;
import rebue.scx.rac.mo.RacPermGroupMo;
import rebue.scx.rac.to.RacPermGroupAddTo;
import rebue.scx.rac.to.RacPermGroupDelTo;
import rebue.scx.rac.to.RacPermGroupListTo;
import rebue.scx.rac.to.RacPermGroupModifyTo;
import rebue.scx.rac.to.RacPermGroupOneTo;
import rebue.scx.rac.to.RacPermGroupPageTo;

/**
 * 权限分组服务接口
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
public interface RacPermGroupSvc extends
    BaseSvc<java.lang.Long, RacPermGroupAddTo, RacPermGroupModifyTo, RacPermGroupDelTo, RacPermGroupOneTo, RacPermGroupListTo, RacPermGroupPageTo, RacPermGroupMo, RacPermGroupJo> {

    /**
     * 上移动
     */
    void moveUp(RacPermGroupModifyTo qo);

    /**
     * 下移动
     */
    void moveDown(RacPermGroupModifyTo qo);

    /**
     * 启用权限分组
     */
    void enable(RacPermGroupModifyTo qo);

    /**
     * 禁用权限分组
     */
    void disable(RacPermGroupModifyTo qo);

    /**
     * 禁用权限分组联动子节点
     */
    void disableLinkage(RacPermGroupModifyTo to);

    /**
     * 启动权限分组联动子节点
     */
    void enableLinkage(RacPermGroupModifyTo to);
}
