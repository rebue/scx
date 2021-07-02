package rebue.wxx.orika;

import rebue.wheel.api.OrikaUtils;
import rebue.wxx.cco.WxxAppCco;
import rebue.wxx.mo.WxxAppMo;

public class OrikaRegister {

    static {
        OrikaUtils.mapperFactory.classMap(WxxAppMo.class, WxxAppCco.class).field("id", "appId").field("token", "appToken").byDefault().register();
    }

}
