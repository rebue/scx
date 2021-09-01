package rebue.scx.oss.svc;

import java.io.InputStream;

import org.springframework.validation.annotation.Validated;

import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.scx.oss.jo.OssObjJo;
import rebue.scx.oss.mo.OssObjMo;
import rebue.scx.oss.to.OssObjAddTo;
import rebue.scx.oss.to.OssObjDelTo;
import rebue.scx.oss.to.OssObjListTo;
import rebue.scx.oss.to.OssObjModifyTo;
import rebue.scx.oss.to.OssObjOneTo;
import rebue.scx.oss.to.OssObjPageTo;

/**
 * 对象服务接口
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
public interface OssObjSvc extends BaseSvc<java.lang.Long, OssObjAddTo, OssObjModifyTo, OssObjDelTo, OssObjOneTo, OssObjListTo, OssObjPageTo, OssObjMo, OssObjJo> {

    /**
     * 上传文件
     *
     * @param curAccountId       当前账户ID
     * @param fileName           文件名称
     * @param contentDisposition 请求头中的 Content-Disposition
     * @param contentType        请求头中的 Content-Type
     * @param inputStream        文件输入流
     */
    Ro<?> upload(Long curAccountId, String fileName, String contentDisposition, String contentType, InputStream inputStream);

    /**
     * 上传字符串大文本内容
     *
     * @param curAccountId       当前账户ID
     * @param fileName           文件名称 ("avatar.txt")
     * @param contentDisposition 请求头中的 Content-Disposition ("form-data; name=\"avatar\"; filename=\"avatar.png\"")
     * @param contentType        请求头中的 Content-Type ("text/html")
     * @param writeValueAsString 写入字符串
     */
    Ro<?> addText(Long curAccountId, String fileName, String contentDisposition, String contentType, String writeValueAsString);

    /**
     * 获取文本对象
     *
     * @param fileName 文件名称
     * @param text     文本
     */
    String getTextObject(String fileName);

    /**
     * 删除文本对象
     *
     * @param id   文件对象ID
     * @param text 文本
     */
    Ro<?> delText(Long id);
}
