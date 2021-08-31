package com.github.rebue.scx.api;

import com.github.rebue.scx.mo.OapAppMo;
import com.github.rebue.scx.to.OapAppAddTo;
import com.github.rebue.scx.to.OapAppModifyTo;
import com.github.rebue.scx.to.OapAppPageTo;
import rebue.robotech.api.BaseApi;

import java.util.Optional;

/**
 * 第三方应用的API
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface OapAppApi extends BaseApi<java.lang.Long, OapAppAddTo, OapAppModifyTo, OapAppPageTo, OapAppMo> {

    Optional<OapAppMo> selectOneByClientId(String clientId);

}
