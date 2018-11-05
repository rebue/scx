package rebue.scx.zuul;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.util.AntPathMatcher;

import com.google.common.collect.Lists;

public class MatchTests {
    private final static String  _sUrls   = "POST:/pfm-svr/user/login/by/user/name,POST:/wxx-svr/wxx/response,GET:/wxx-svr/wxx/response/**,GET:/damai-wx-svr/damai-wx/login,GET:/damai-wxg/**,GET:/**.ico,GET:/**/*.js,GET:/**.css,GET:/**.png,GET:/**.jpg,GET:/**/js/**,GET:/**/css/**,GET:/**/img/**,GET:/**/images/**,GET:/**/fonts/**";

    private static List<String>  _urls;

    private final AntPathMatcher _matcher = new AntPathMatcher();

    @BeforeClass
    public static void init() {
        _urls = Lists.newArrayList(_sUrls.split(","));
    }

    @Test
    public void test01() {
        Assert.assertEquals(true, match("GET:/damai-wxg/app.js"));
        Assert.assertEquals(true, match("GET:/damai-wxg/"));
        Assert.assertEquals(true, match("GET:/damai-wxg"));
    }

    private boolean match(final String url) {
        final List<String> tempUrls = _urls;
        return _urls != null && !_urls.isEmpty()//
                && tempUrls.stream().anyMatch((final String pattern) -> _matcher.match(pattern, url));
    }
}
