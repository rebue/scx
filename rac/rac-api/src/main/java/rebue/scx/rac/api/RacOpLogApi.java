package rebue.scx.rac.api;

import java.util.Map;

import rebue.robotech.api.BaseApi;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.RacOpLogMo;
import rebue.scx.rac.to.RacOpLogAddTo;
import rebue.scx.rac.to.RacOpLogModifyTo;
import rebue.scx.rac.to.RacOpLogPageTo;

/**
 * 操作日志的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface RacOpLogApi extends BaseApi<java.lang.Long, RacOpLogAddTo, RacOpLogModifyTo, RacOpLogPageTo, RacOpLogMo> {

    /**
     * 账户概况
     * 传参时间和关键字keywords 取值为：账户添加/账户修改/账户删除/账户密码修改/启用账户/禁用账户
     *
     * @param qo
     */
    Ro<Map<String, Long>> countSurvey(RacOpLogPageTo qo);
}
