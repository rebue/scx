package rebue.scx.jwt.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Test;
import rebue.wheel.core.ResourcesWrapper;

import java.io.InputStream;
import java.security.KeyPair;
import java.security.Security;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class CreatePublicPrivateKeyTest {

    private static final String RSA_KEY_FILE_PATH = "jwt-dev-key-pair.pem";

    private static String publicKeyBase64(RSAPublicKey publicKey)
    {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }

    @Test
    public void test() throws Exception
    {
        Security.addProvider(new BouncyCastleProvider());
        try (InputStream in = ResourcesWrapper.getInputStream(RSA_KEY_FILE_PATH, CreatePublicPrivateKeyTest.class)) {
            KeyPair keyPair = CreatePublicPrivateKey.parseRsaKeyPair(in);
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            System.out.println(publicKeyBase64(publicKey));
        }
    }

}