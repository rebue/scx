package rebue.scx.gateway.server.filter;

public class FilterUtils {

    /**
     * 后端放行静态资源 拦截html
     */
    public static boolean backendInterceptSkip(String path)
    {
        // 拦截html
        return path.matches(".*[.](jpg|css|svg|ttf|ddf|png|js|woff|txt|ico|json|map)$");
    }

    /**
     * 匹配就不记录日志
     */
    public static boolean logSkip(String path)
    {
        return path.matches(".*[.](jpg|css|svg|ttf|ddf|png|js|woff|txt|ico|json|map|html)$");
    }

}
