package rebue.scx.oss.api;

import java.io.InputStream;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;
import rebue.scx.oss.mo.OssObjMo;
import rebue.scx.oss.to.OssObjAddTo;
import rebue.scx.oss.to.OssObjModifyTo;
import rebue.scx.oss.to.OssObjPageTo;

/**
 * 对象API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface OssObjApi extends BaseApi<java.lang.Long, OssObjAddTo, OssObjModifyTo, OssObjPageTo, OssObjMo> {

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
}
