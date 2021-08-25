package rebue.scx.jwt.config;

import lombok.Getter;
import org.springframework.stereotype.Component;
import rebue.scx.jwt.utils.CreatePublicPrivateKey;
import rebue.wheel.core.ResourcesWrapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
public class JwtKey {

    @Getter
    private RSAPrivateKey privateKey;

    @Getter
    private RSAPublicKey publicKey;

    @Getter
    private long exp;

    @Resource
    private JwtProperties jwtProperties;

    @PostConstruct
    private void init() throws Exception
    {
        try (InputStream in = ResourcesWrapper.getInputStream(jwtProperties.getKeyPair(), JwtKey.class)) {
            KeyPair keyPair = CreatePublicPrivateKey.parseRsaKeyPair(in);
            privateKey = (RSAPrivateKey) keyPair.getPrivate();
            publicKey = (RSAPublicKey) keyPair.getPublic();
        }
        exp = jwtProperties.getExpirationDuration().toMillis();
    }

}
