package rebue.scx.jwt.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyPair;

import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

public class KeyPairUtils {

    public static KeyPair parseRsaKeyPair(final InputStream in) throws IOException {
        final PEMParser pemParser = new PEMParser(
                new InputStreamReader(in));
        PEMKeyPair      pemKeyPair;
        try {
            pemKeyPair = (PEMKeyPair) pemParser.readObject();
        } finally {
            pemParser.close();
        }
        // Convert to Java (JCA) format
        final JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
        return converter.getKeyPair(pemKeyPair);
    }

}
