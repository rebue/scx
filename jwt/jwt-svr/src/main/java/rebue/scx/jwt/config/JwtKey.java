package rebue.scx.jwt.config;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import lombok.Data;

@Data
public class JwtKey {

    private RSAPrivateKey privateKey;

    private RSAPublicKey  publicKey;

    private long          exp;
}
