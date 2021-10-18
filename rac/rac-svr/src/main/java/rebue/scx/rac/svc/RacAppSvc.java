package rebue.scx.rac.svc;

import java.util.List;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.svc.BaseSvc;
import rebue.scx.rac.jo.RacAppJo;
import rebue.scx.rac.mo.RacAppMo;
import rebue.scx.rac.mo.RacAppTagMo;
import rebue.scx.rac.to.RacAppAddTo;
import rebue.scx.rac.to.RacAppDelTo;
import rebue.scx.rac.to.RacAppEnabledTo;
import rebue.scx.rac.to.RacAppListTo;
import rebue.scx.rac.to.RacAppModifyTo;
import rebue.scx.rac.to.RacAppOneTo;
import rebue.scx.rac.to.RacAppPageTo;

/**
 * 应用服务接口
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
public interface RacAppSvc extends BaseSvc<java.lang.String, RacAppAddTo, RacAppModifyTo, RacAppDelTo, RacAppOneTo, RacAppListTo, RacAppPageTo, RacAppMo, RacAppJo> {

    /**
     * 是否启用应用
     *
     * @param to 修改的具体数据
     */
    void enable(RacAppEnabledTo to);

    /**
     * 上移动
     */
    void moveUp(RacAppModifyTo to);

    /**
     * 下移动
     */
    void moveDown(RacAppModifyTo to);

    /**
     * 查询应用
     */
    List<RacAppMo> listOrderBySeqNo(RacAppListTo qo);

    /**
     * 根据帐号ID查询他可以看到的应用
     *
     * @param accountIds 账户ID集合
     *
     * @return
     */
    List<RacAppMo> selectAppByAccountIds(List<Long> accountIds);

    /**
     * 根据应用ID查询对应的认证信息
     */
    List<RacAppTagMo> listInAppIdList(List<String> appIds);

    /**
     * 通过ID修改记录内容(oap服务调用修改)
     *
     * @param to 修改的参数，必须包含ID
     *
     * @return 如果成功，且仅修改一条记录，正常返回，否则会抛出运行时异常
     */
    RacAppMo oapModifyById(RacAppModifyTo to);
}
