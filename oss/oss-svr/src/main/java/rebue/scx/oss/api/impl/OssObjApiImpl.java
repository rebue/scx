package rebue.scx.oss.api.impl;

import java.io.InputStream;
import org.apache.dubbo.config.annotation.DubboService;
import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.ro.Ro;
import rebue.scx.oss.api.OssObjApi;
import rebue.scx.oss.jo.OssObjJo;
import rebue.scx.oss.mo.OssObjMo;
import rebue.scx.oss.svc.OssObjSvc;
import rebue.scx.oss.to.OssObjAddTo;
import rebue.scx.oss.to.OssObjDelTo;
import rebue.scx.oss.to.OssObjListTo;
import rebue.scx.oss.to.OssObjModifyTo;
import rebue.scx.oss.to.OssObjOneTo;
import rebue.scx.oss.to.OssObjPageTo;

/**
 * 对象API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class OssObjApiImpl extends BaseApiImpl<java.lang.Long, OssObjAddTo, OssObjModifyTo, OssObjDelTo, OssObjOneTo, OssObjListTo, OssObjPageTo, OssObjMo, OssObjJo, OssObjSvc>
    implements OssObjApi {

    /**
     * 上传文件
     *
     * @param curAccountId       当前账户ID
     * @param fileName           文件名称
     * @param contentDisposition 请求头中的 Content-Disposition
     * @param contentType        请求头中的 Content-Type
     * @param inputStream        文件输入流
     */
    @Override
    public Ro<?> upload(final Long curAccountId, final String fileName, final String contentDisposition, final String contentType, final InputStream inputStream) {
        return _svc.upload(curAccountId, fileName, contentDisposition, contentType, inputStream);
    }

    /**
     * 上传字符串大文本内容
     *
     * @param curAccountId       当前账户ID
     * @param fileName           文件名称
     * @param contentDisposition 请求头中的 Content-Disposition
     * @param contentType        请求头中的 Content-Type
     * @param writeValueAsString 写入字符串
     */
    @Override
    public Ro<?> upload(final Long curAccountId, final String fileName, final String contentDisposition, final String contentType, final String writeValueAsString) {
        return _svc.addText(curAccountId, fileName, contentDisposition, contentType, writeValueAsString);
    }

    /**
     * 获取文本对象
     *
     * @param fileName 文件名称
     * @param text     文本
     */
    @Override
    public String getTextObject(final String fileName) {
        return _svc.getTextObject(fileName);
    }

    /**
     * 删除文本对象
     *
     * @param id   文件对象ID
     * @param text 文本
     */
    @Override
    public Ro<?> delText(final Long id) {
        return _svc.delText(id);
    }
}
