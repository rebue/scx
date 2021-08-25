package rebue.scx.jwt.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.Security;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

/**
 * 打印出公钥base64 测试用
 */
public class CreatePublicPrivateKey {

    private static final String RSA_KEY_FILE_PATH = "jwt-dev-key-pair.pem";

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

    private static String publicKeyBase64(RSAPublicKey publicKey)
    {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    public static void main(String[] args) throws Exception
    {
        Security.addProvider(new BouncyCastleProvider());
        try (InputStream in = ResourcesWrapper.getInputStream(RSA_KEY_FILE_PATH)) {
            KeyPair keyPair = parseRsaKeyPair(in);
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            System.out.println(publicKeyBase64(publicKey));
        }
    }

}
