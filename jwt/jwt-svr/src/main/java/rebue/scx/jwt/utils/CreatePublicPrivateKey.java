package rebue.scx.jwt.utils;

import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyPair;

public class CreatePublicPrivateKey {

    public static KeyPair parseRsaKeyPair(InputStream in) throws IOException
    {
        PEMParser pemParser = new PEMParser(
                new InputStreamReader(in));
        PEMKeyPair pemKeyPair;
        try {
            pemKeyPair = (PEMKeyPair) pemParser.readObject();
        } finally {
            pemParser.close();
        }
        // Convert to Java (JCA) format
        JcaPEMKeyConverter converter = new JcaPEMKeyConverter();
        return converter.getKeyPair(pemKeyPair);
    }

}
