package rebue.wxx.job;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xxl.job.core.handler.annotation.XxlJob;

import lombok.extern.slf4j.Slf4j;
import rebue.wxx.mo.WxxAppMo;
import rebue.wxx.svc.WxxAppSvc;

@Component
@Slf4j
public class WxxJob {

    @Resource
    private WxxAppSvc appSvc;

    @XxlJob("refreshAccessToken")
    public void refreshAccessToken() {
        for (final WxxAppMo appMo : appSvc.listAll()) {
            // WxxAccessTokenEo accessTokenEo = accessTokenRedisSvc.get(appMo);
            // if (accessTokenEo == null) {
            // accessTokenEo = new WxxAccessTokenEo();
            // accessTokenEo.setId(appMo);
            // }
            // // 如果下次请求时间为空或小于当前时间，发出请求
            // if (accessTokenEo.getNextRequestTime() == null //
            // || accessTokenEo.getNextRequestTime().compareTo(new Date()) < 0) {
            // requestAccessToken(accessTokenEo);
            // }
            // else {
            // log.trace("还未到计划下次发出请求的时间: appEo-{}", accessTokenEo);
            // }
        }

    }
}
