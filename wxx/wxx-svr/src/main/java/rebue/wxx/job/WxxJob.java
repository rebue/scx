package rebue.wxx.job;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xxl.job.core.handler.annotation.XxlJob;

import lombok.extern.slf4j.Slf4j;
import rebue.wxx.cco.WxxAppCco;
import rebue.wxx.fapi.WxxFapi;
import rebue.wxx.fro.WxxGetAccessTokenFro;
import rebue.wxx.svc.WxxAppSvc;

@Slf4j
@Component
public class WxxJob {

    @Resource
    private WxxAppSvc appSvc;
    @Resource
    private WxxFapi   wxxFapi;

    @XxlJob("refreshAccessToken")
    public void refreshAccessToken() {
        for (final WxxAppCco appCco : appSvc.listCcoAll()) {
            final LocalDateTime now = LocalDateTime.now();
            // 如果下次请求时间为空或小于当前时间，发出请求
            if (appCco.getNextRequestTime() == null //
                || appCco.getNextRequestTime().compareTo(now) < 0) {
                final WxxGetAccessTokenFro accessTokenFro = wxxFapi.getAccessToken(appCco.getAppId(), appCco.getAppSecret());
                log.info("accessTokenFro: {}", accessTokenFro);
                appCco.setAccessToken(accessTokenFro.getAccess_token());
                appCco.setNextRequestTime(now.plusSeconds(accessTokenFro.getExpires_in()).minusMinutes(15));    // 在返回的超时时间前15分钟再次刷新
                appSvc.putCco(appCco);
            }
            else {
                log.trace("还未到计划下次发出请求的时间: appCco-{}", appCco);
            }
        }

    }
}
