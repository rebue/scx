package rebue.scx.gateway.server.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;

public class FilterUtils {

    /**
     * 后端放行静态资源 拦截html
     */
    public static boolean backendInterceptSkip(ServerHttpRequest request) {
        String path = request.getPath().toString();
        return path.matches(".*[.](jpg|css|svg|ttf|ddf|png|js|woff|txt|ico|json|map)$")
                || isLoginPage(path, request.getURI().getQuery());
    }

    /**
     * 匹配就不记录日志
     */
    public static boolean logSkip(ServerHttpRequest request) {
        String path = request.getPath().toString();
        return path.matches(".*[.](jpg|css|svg|ttf|ddf|png|js|woff|txt|ico|json|map|html)$")
                || isLoginPage(path, request.getURI().getQuery());
    }

    /**
     * 是否是登录页面
     */
    private static boolean isLoginPage(String path, String query) {
        return path.startsWith("/admin-web") && query != null && query.contains("u=1");
    }

}
