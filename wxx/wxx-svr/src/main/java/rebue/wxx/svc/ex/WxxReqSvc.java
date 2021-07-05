package rebue.wxx.svc.ex;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.wxx.cco.WxxAppCco;
import rebue.wxx.fto.WxxMessageTemplateSendFto;

/**
 * 向微信发送请求的服务接口
 *
 * <pre>
 * 1. 在接口上方必须写上 @Validated 注解
 * 2. 参数是POJO类时用 @Valid 注解在参数类型的前面进行修饰
 *    参数是普通参数时，直接在参数类型的前面加上具体约束的注解
 * 3. (待验证)有分组时，在方法上方必须写上 @Validated 注解及分组
 * 4. 踩坑留痕：
 *    如果方法的返回值为void，在方法上方加上 @Valid 注解会出现异常，报HV000132错误
 * </pre>
 */
@Validated
public interface WxxReqSvc {

    /**
     * 刷新Access token
     *
     * @param appId   微信的AppId
     * @param cco     App的缓存对象，可从缓存中获取传进来；也可以为空，会自动获取
     * @param isForce 是否强制刷新(缓存未到期时也刷新，默认为否，缓存到期了才刷新)
     */
    void refreshAccessToken(@NotBlank(message = "微信的AppId不能为空") String appId, WxxAppCco cco, boolean isForce);

    /**
     * 发送模板类的消息
     */
    Ro<?> sendTemplateMessage(@NotBlank(message = "微信的AppId不能为空") String appId, @Valid WxxMessageTemplateSendFto to);

}
