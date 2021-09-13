package rebue.scx.orp.exception;

public class OidcAuthenticationException extends RuntimeException {

    public OidcAuthenticationException(String message)
    {
        super(message);
    }

    public OidcAuthenticationException(Throwable cause)
    {
        super(cause);
    }

    public OidcAuthenticationException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
