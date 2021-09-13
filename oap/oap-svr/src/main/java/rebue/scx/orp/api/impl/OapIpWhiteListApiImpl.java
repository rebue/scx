package rebue.scx.orp.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.scx.orp.api.OapIpWhiteListApi;
import rebue.scx.orp.jo.OapIpWhiteListJo;
import rebue.scx.orp.mo.OapIpWhiteListMo;
import rebue.scx.orp.svc.OapIpWhiteListSvc;
import rebue.scx.orp.to.OapIpWhiteListAddTo;
import rebue.scx.orp.to.OapIpWhiteListDelTo;
import rebue.scx.orp.to.OapIpWhiteListListTo;
import rebue.scx.orp.to.OapIpWhiteListModifyTo;
import rebue.scx.orp.to.OapIpWhiteListOneTo;
import rebue.scx.orp.to.OapIpWhiteListPageTo;

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
