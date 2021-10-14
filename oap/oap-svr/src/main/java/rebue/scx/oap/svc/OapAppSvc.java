package rebue.scx.oap.svc;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.oap.jo.OapAppJo;
import rebue.scx.oap.mo.OapAppMo;
import rebue.scx.oap.mo.ex.OapAppListAndRacAppListRa;
import rebue.scx.oap.to.OapAppAddTo;
import rebue.scx.oap.to.OapAppDelTo;
import rebue.scx.oap.to.OapAppListTo;
import rebue.scx.oap.to.OapAppModifyTo;
import rebue.scx.oap.to.OapAppOneTo;
import rebue.scx.oap.to.OapAppPageTo;

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

    List<OapAppMo> listInAppIdList(List<String> appIds);

}
