package rebue.scx.rac.svc;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacPermMenuJo;
import rebue.scx.rac.mo.RacPermMenuMo;
import rebue.scx.rac.to.RacPermMenuAddTo;
import rebue.scx.rac.to.RacPermMenuDelTo;
import rebue.scx.rac.to.RacPermMenuListTo;
import rebue.scx.rac.to.RacPermMenuModifyTo;
import rebue.scx.rac.to.RacPermMenuOneTo;
import rebue.scx.rac.to.RacPermMenuPageTo;
import rebue.scx.rac.to.ex.RacPermMenusAddTo;

/**
 * 权限菜单服务接口
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
public interface RacPermMenuSvc
    extends BaseSvc<java.lang.Long, RacPermMenuAddTo, RacPermMenuModifyTo, RacPermMenuDelTo, RacPermMenuOneTo, RacPermMenuListTo, RacPermMenuPageTo, RacPermMenuMo, RacPermMenuJo> {

    List<String> getMenusOfAccount(Long accountId, String appId);

    /**
     * 查询权限菜单的信息
     *
     * @param qo 查询的具体条件
     */
    List<RacPermMenuMo> listPermMenu(RacPermMenuListTo qo);

    /**
     * 添加/修改权限菜单
     *
     * @param to 添加的具体信息
     */
    void addPermMenuUrn(RacPermMenusAddTo to);
}
