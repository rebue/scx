package rebue.scx.oap.api.impl;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.IdRa;
import rebue.robotech.ro.Ro;
import rebue.scx.oap.api.OapAuthLogApi;
import rebue.scx.oap.jo.OapAuthLogJo;
import rebue.scx.oap.mo.OapAuthLogMo;
import rebue.scx.oap.svc.OapAuthLogSvc;
import rebue.scx.oap.to.OapAuthLogAddTo;
import rebue.scx.oap.to.OapAuthLogDelTo;
import rebue.scx.oap.to.OapAuthLogListTo;
import rebue.scx.oap.to.OapAuthLogModifyTo;
import rebue.scx.oap.to.OapAuthLogOneTo;
import rebue.scx.oap.to.OapAuthLogPageTo;

/**
 * 认证记录API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class OapAuthLogApiImpl extends
    BaseApiImpl<java.lang.Long, OapAuthLogAddTo, OapAuthLogModifyTo, OapAuthLogDelTo, OapAuthLogOneTo, OapAuthLogListTo, OapAuthLogPageTo, OapAuthLogMo, OapAuthLogJo, OapAuthLogSvc>
    implements OapAuthLogApi {

    @Override
    public Ro<?> countSurvey(OapAuthLogPageTo qo) {
        return new Ro<>(ResultDic.SUCCESS, "查询成功", new IdRa<>(_svc.countSurvey(qo)));
    }
}
