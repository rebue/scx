package rebue.scx.orp.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<CookieFilter> regCookieFilter(CookieFilter cookieFilter)
    {
        FilterRegistrationBean<CookieFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(cookieFilter);
        registrationBean.setUrlPatterns(new ArrayList<String>() {{
            add("/*");
        }});
        return registrationBean;
    }

}
