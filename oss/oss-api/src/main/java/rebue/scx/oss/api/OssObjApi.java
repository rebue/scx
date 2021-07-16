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

	/**
	 * 上传字符串大文本内容
	 *
	 * @param curAccountId       当前账户ID
	 * @param fileName           文件名称 ("avatar.txt")
	 * @param contentDisposition 请求头中的 Content-Disposition ("form-data; name=\"avatar\"; filename=\"avatar.png\"")
	 * @param contentType        请求头中的 Content-Type ("text/html")
	 * @param writeValueAsString 写入字符串
	 */
	Ro<?> upload(Long curAccountId, String fileName, String contentDisposition, String contentType, String writeValueAsString);

	/**
	 * 获取文本对象
	 *
	 * @param fileName 文件名称
	 * @param text     文本
	 */
	String getText(String fileName);

	/**
	 * 删除文本对象
	 *
	 * @param id   文件对象ID
	 * @param text 文本
	 */
	Ro<?> delText(Long id);
}
