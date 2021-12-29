package rebue.scx.rac.api.impl;

import java.util.Map;

import org.apache.dubbo.config.annotation.DubboService;

import rebue.robotech.api.impl.BaseApiImpl;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.api.RacOpLogApi;
import rebue.scx.rac.jo.RacOpLogJo;
import rebue.scx.rac.mo.RacOpLogMo;
import rebue.scx.rac.svc.RacOpLogSvc;
import rebue.scx.rac.to.RacOpLogAddTo;
import rebue.scx.rac.to.RacOpLogDelTo;
import rebue.scx.rac.to.RacOpLogListTo;
import rebue.scx.rac.to.RacOpLogModifyTo;
import rebue.scx.rac.to.RacOpLogOneTo;
import rebue.scx.rac.to.RacOpLogPageTo;

/**
 * 操作日志API实现
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@DubboService
public class RacOpLogApiImpl
        extends BaseApiImpl<java.lang.Long, RacOpLogAddTo, RacOpLogModifyTo, RacOpLogDelTo, RacOpLogOneTo, RacOpLogListTo, RacOpLogPageTo, RacOpLogMo, RacOpLogJo, RacOpLogSvc>
        implements RacOpLogApi {

    /**
     * 账户概况
     * 传参时间和关键字keywords 取值为：账户添加/账户修改/账户删除/账户密码修改/启用账户/禁用账户
     *
     * @param qo
     */
    @Override
    public Ro<Map<String, Long>> countSurvey(RacOpLogPageTo qo) {
        return new Ro<>(ResultDic.SUCCESS, "查询成功", _svc.countSurvey(qo));
    }
}
