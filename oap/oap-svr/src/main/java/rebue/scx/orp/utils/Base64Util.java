package rebue.scx.orp.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {

    public static String encode(String raw)
    {
        byte[] encode = Base64.getEncoder().encode(raw.getBytes(StandardCharsets.UTF_8));
        return new String(encode, StandardCharsets.UTF_8);
    }

    public static String decode(String raw)
    {
        byte[] decode = Base64.getDecoder().decode(raw);
        return new String(decode, StandardCharsets.UTF_8);
    }

}
