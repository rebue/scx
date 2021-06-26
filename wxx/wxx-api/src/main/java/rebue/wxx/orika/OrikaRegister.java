package rebue.wxx.orika;

import rebue.wheel.api.OrikaUtils;
import rebue.wxx.mo.WxxAppMo;
import rebue.wxx.rdo.WxxAppRdo;

public class OrikaRegister {

    static {
        OrikaUtils.mapperFactory.classMap(WxxAppMo.class, WxxAppRdo.class).field("id", "appId").byDefault().register();
    }

}
