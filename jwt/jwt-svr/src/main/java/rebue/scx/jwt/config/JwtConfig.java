package rebue.scx.jwt.config;

import java.io.InputStream;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.SneakyThrows;
import rebue.scx.jwt.util.KeyPairUtils;
import rebue.wheel.core.ResourcesWrapper;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {

    @Bean
    @SneakyThrows
    JwtKey jwtKey(final JwtProperties jwtProperties) {
        final JwtKey jwtKey = new JwtKey();
        KeyPair      keyPair;
        try (InputStream in = ResourcesWrapper.getInputStream(jwtProperties.getKeyPair(), JwtConfig.class)) {
            keyPair = KeyPairUtils.parseRsaKeyPair(in);
        }
        jwtKey.setPrivateKey((RSAPrivateKey) keyPair.getPrivate());
        jwtKey.setPublicKey((RSAPublicKey) keyPair.getPublic());
        jwtKey.setExp(jwtProperties.getExpirationDuration().toMillis());
        return jwtKey;
    }

}
