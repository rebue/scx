package rebue.wxx.orika;

import rebue.wheel.core.util.OrikaUtils;
import rebue.wxx.cco.WxxAppCco;
import rebue.wxx.mo.WxxAppMo;

public class OrikaRegister {

    static {
        OrikaUtils.mapperFactory.classMap(WxxAppMo.class, WxxAppCco.class).field("id", "appId").field("token", "appToken").byDefault().register();
    }

}
