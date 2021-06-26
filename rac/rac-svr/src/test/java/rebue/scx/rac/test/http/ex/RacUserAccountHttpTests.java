package rebue.scx.rac.test.http.ex;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.type.TypeReference;

import lombok.extern.slf4j.Slf4j;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ra.PageRa;
import rebue.robotech.ro.Ro;
import rebue.scx.rac.mo.ex.RacUserAccountMo;
import rebue.scx.rac.to.ex.RacUserAccountPageTo;
import rebue.wheel.net.httpclient.HttpClient;
import rebue.wheel.net.httpclient.impl.OkHttpClientImpl;
import rebue.wheel.serialization.jackson.JacksonUtils;

/**
 * HTTP测试
 */
@Slf4j
public class RacUserAccountHttpTests {

    private final String _hostUrl = "http://127.0.0.1:9605";
    // private final String _hostUrl = "http://127.0.0.1:10080/rac-svr";

    private final HttpClient _httpClient = new OkHttpClientImpl();

    /**
     * 测试分页查询
     */
    @Test
    public void testPage() throws IOException {
        final RacUserAccountPageTo to = new RacUserAccountPageTo();
        to.setPageNum(1);
        to.setPageSize(100);

        final String pageResult = _httpClient.get(_hostUrl + "/rac/user-account/page");
        log.info("查询账户的返回值为：" + pageResult);
        final Ro<PageRa<RacUserAccountMo>> pageRo = JacksonUtils.deserialize(pageResult, new TypeReference<Ro<PageRa<RacUserAccountMo>>>() {
        });
        Assertions.assertEquals(ResultDic.SUCCESS, pageRo.getResult());
    }
}
