package rebue.scx.oap.oidc;

/**
 * https://tools.ietf.org/html/rfc6749#section-5.2
 */
public class OidcTokenError {

    public static final String INVALID_REQUEST = "invalid_request";

    public static final String INVALID_CLIENT = "invalid_client";

    public static final String INVALID_GRANT = "invalid_grant";

    public static final String UNAUTHORIZED_CLIENT = "unauthorized_client";

    public static final String UNSUPPORTED_GRANT_TYPE = "unsupported_grant_type";

    public static final String INVALID_SCOPE = "invalid_scope";

    public static final String SERVER_ERROR = "server_error";

}
