package rebue.scx.oss.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
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

}
