package rebue.wxx.job;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xxl.job.core.handler.annotation.XxlJob;

import lombok.extern.slf4j.Slf4j;
import rebue.wxx.cco.WxxAppCco;
import rebue.wxx.fapi.WxxFapi;
import rebue.wxx.fro.WxxGetAccessTokenFro;
import rebue.wxx.svc.ex.WxxAppCacheSvc;

@Slf4j
@Component
public class WxxJob {

    @Resource
    private WxxAppCacheSvc appCacheSvc;
    @Resource
    private WxxFapi        wxxFapi;

    @XxlJob("refreshAccessToken")
    public void refreshAccessToken() {
        for (final WxxAppCco cco : appCacheSvc.listAll()) {
            final LocalDateTime now = LocalDateTime.now();
            // 如果下次请求时间为空或小于当前时间，发出请求
            if (cco.getNextRequestTime() == null //
                    || cco.getNextRequestTime().compareTo(now) < 0) {
                final WxxGetAccessTokenFro accessTokenFro = wxxFapi.getAccessToken(cco.getAppId(), cco.getAppSecret());
                log.info("accessTokenFro: {}", accessTokenFro);
                if (accessTokenFro.getErrcode() == null || accessTokenFro.getErrcode() == 0) {
                    cco.setAccessToken(accessTokenFro.getAccess_token());
                    cco.setNextRequestTime(now.plusSeconds(accessTokenFro.getExpires_in()).minusMinutes(15));    // 在返回的超时时间前15分钟再次刷新
                    appCacheSvc.putToCache(cco);
                }
            }
            else {
                log.trace("还未到计划下次发出请求的时间: appCco-{}", cco);
            }
        }

    }
}
