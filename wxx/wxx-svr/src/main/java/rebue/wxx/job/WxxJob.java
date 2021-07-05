package rebue.wxx.job;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xxl.job.core.handler.annotation.XxlJob;

import rebue.wxx.cco.WxxAppCco;
import rebue.wxx.svc.ex.WxxAppCacheSvc;
import rebue.wxx.svc.ex.WxxReqSvc;

@Component
public class WxxJob {

    @Resource
    private WxxAppCacheSvc appCacheSvc;
    @Resource
    private WxxReqSvc      appReqSvc;

    @XxlJob("refreshAccessToken")
    public void refreshAccessToken() {
        for (final WxxAppCco cco : appCacheSvc.listAll()) {
            appReqSvc.refreshAccessToken(cco.getAppId(), cco, false);
        }

    }
}
