package rebue.scx.orp.svc;

import org.springframework.validation.annotation.Validated;

import rebue.scx.orp.jo.OapAppJo;
import rebue.scx.orp.mo.OapAppMo;
import rebue.scx.orp.mo.ex.OapAppListAndRacAppListRa;
import rebue.scx.orp.to.OapAppAddTo;
import rebue.scx.orp.to.OapAppDelTo;
import rebue.scx.orp.to.OapAppListTo;
import rebue.scx.orp.to.OapAppModifyTo;
import rebue.scx.orp.to.OapAppOneTo;
import rebue.scx.orp.to.OapAppPageTo;

import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;

/**
 * 第三方应用服务接口
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
public interface OapAppSvc extends BaseSvc<java.lang.Long, OapAppAddTo, OapAppModifyTo, OapAppDelTo, OapAppOneTo, OapAppListTo, OapAppPageTo, OapAppMo, OapAppJo> {
    /**
     * 获取单个第三方应用的信息
     *
     * @param id 通过rac_app的ID关联查询
     */
    Ro<?> getByAppId(String id);

    /**
     * 查询应用的信息并附带第三方应用的信息
     *
     * @param qo 查询的具体条件(查询所有，及条件为空)
     * 
     */
    Ro<OapAppListAndRacAppListRa> listAndTripartite(OapAppListTo qo);
}
