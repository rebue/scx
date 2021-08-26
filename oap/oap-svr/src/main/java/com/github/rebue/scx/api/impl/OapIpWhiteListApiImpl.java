package com.github.rebue.scx.api.impl;

import org.apache.dubbo.config.annotation.DubboService;
import com.github.rebue.scx.mo.OapIpWhiteListMo;
import com.github.rebue.scx.to.OapIpWhiteListAddTo;
import com.github.rebue.scx.to.OapIpWhiteListModifyTo;
import com.github.rebue.scx.to.OapIpWhiteListDelTo;
import com.github.rebue.scx.to.OapIpWhiteListOneTo;
import com.github.rebue.scx.to.OapIpWhiteListListTo;
import com.github.rebue.scx.to.OapIpWhiteListPageTo;
import com.github.rebue.scx.api.OapIpWhiteListApi;
import com.github.rebue.scx.jo.OapIpWhiteListJo;
import com.github.rebue.scx.svc.OapIpWhiteListSvc;
import rebue.robotech.api.impl.BaseApiImpl;

/**
 * API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class OapIpWhiteListApiImpl extends
    BaseApiImpl<java.lang.Long, OapIpWhiteListAddTo, OapIpWhiteListModifyTo, OapIpWhiteListDelTo, OapIpWhiteListOneTo, OapIpWhiteListListTo, OapIpWhiteListPageTo, OapIpWhiteListMo, OapIpWhiteListJo, OapIpWhiteListSvc>
    implements OapIpWhiteListApi {
}
