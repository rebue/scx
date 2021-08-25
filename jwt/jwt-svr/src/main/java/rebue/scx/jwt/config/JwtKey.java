package rebue.scx.jwt.config;

import lombok.Data;
import org.springframework.stereotype.Component;
import rebue.scx.jwt.utils.CreatePublicPrivateKey;
import rebue.scx.jwt.utils.ResourcesWrapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Data
@Component
public class JwtKey {

    private RSAPrivateKey privateKey;

    private RSAPublicKey publicKey;

    @Resource
    private JwtProperties jwtProperties;

    @PostConstruct
    private void init() throws Exception
    {
        try (InputStream in = ResourcesWrapper.getInputStream(jwtProperties.getKeyPair())) {
            KeyPair keyPair = CreatePublicPrivateKey.parseRsaKeyPair(in);
            privateKey = (RSAPrivateKey) keyPair.getPrivate();
            publicKey = (RSAPublicKey) keyPair.getPublic();
        }
    }

}
