package rebue.scx.rac.svc;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacPermUrnJo;
import rebue.scx.rac.mo.RacPermUrnMo;
import rebue.scx.rac.to.RacPermUrnAddTo;
import rebue.scx.rac.to.RacPermUrnDelTo;
import rebue.scx.rac.to.RacPermUrnListTo;
import rebue.scx.rac.to.RacPermUrnModifyTo;
import rebue.scx.rac.to.RacPermUrnOneTo;
import rebue.scx.rac.to.RacPermUrnPageTo;

/**
 * 权限URN服务接口
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
public interface RacPermUrnSvc
    extends BaseSvc<java.lang.Long, RacPermUrnAddTo, RacPermUrnModifyTo, RacPermUrnDelTo, RacPermUrnOneTo, RacPermUrnListTo, RacPermUrnPageTo, RacPermUrnMo, RacPermUrnJo> {

    /**
     * 获取账户的链接列表
     *
     * @param accountId 账户ID
     *
     * @return 指定账户的链接列表
     */
    List<String> getUrnsOfAccount(Long accountId);

    /**
     * 添加修改URN
     */
    void modifyByPermId(RacPermUrnAddTo to);
}
