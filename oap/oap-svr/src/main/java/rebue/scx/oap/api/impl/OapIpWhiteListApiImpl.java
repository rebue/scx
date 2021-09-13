package rebue.scx.oap.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.scx.oap.api.OapIpWhiteListApi;
import rebue.scx.oap.jo.OapIpWhiteListJo;
import rebue.scx.oap.mo.OapIpWhiteListMo;
import rebue.scx.oap.svc.OapIpWhiteListSvc;
import rebue.scx.oap.to.OapIpWhiteListAddTo;
import rebue.scx.oap.to.OapIpWhiteListDelTo;
import rebue.scx.oap.to.OapIpWhiteListListTo;
import rebue.scx.oap.to.OapIpWhiteListModifyTo;
import rebue.scx.oap.to.OapIpWhiteListOneTo;
import rebue.scx.oap.to.OapIpWhiteListPageTo;

import rebue.robotech.api.impl.BaseApiImpl;

/**
 * 第三方应用IP白名单API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class OapIpWhiteListApiImpl extends
    BaseApiImpl<java.lang.Long, OapIpWhiteListAddTo, OapIpWhiteListModifyTo, OapIpWhiteListDelTo, OapIpWhiteListOneTo, OapIpWhiteListListTo, OapIpWhiteListPageTo, OapIpWhiteListMo, OapIpWhiteListJo, OapIpWhiteListSvc>
    implements OapIpWhiteListApi {
}
