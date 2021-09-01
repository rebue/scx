package com.github.rebue.third.party.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.springframework.stereotype.Component;

import com.github.rebue.third.party.demo.utils.ResourcesWrapper;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.SneakyThrows;

@Component
public class Jwt {

    private static final long EXPIRES = 10 * 60 * 1000; // jwt过期时间 10分钟

    @Resource
    private Configurations configurations;

    private RSAPrivateKey privateKey;

    private RSAPublicKey publicKey;

    @PostConstruct
    private void init() throws IOException
    {
        try (InputStream in = ResourcesWrapper.getInputStream("jwt-key-pair.pem", Jwt.class)) {
            KeyPair keyPair = parseRsaKeyPair(in);
            privateKey = (RSAPrivateKey) keyPair.getPrivate();
            publicKey = (RSAPublicKey) keyPair.getPublic();
        }
    }

    private static KeyPair parseRsaKeyPair(InputStream in) throws IOException
    {
        try (PEMParser pemParser = new PEMParser(new InputStreamReader(in))) {
            PEMKeyPair pemKeyPair = (PEMKeyPair) pemParser.readObject();
            return new JcaPEMKeyConverter().getKeyPair(pemKeyPair);
        }
    }

    @SneakyThrows
    public String sign(String accountId)
    {
        Date now = new Date();
        Date exp = new Date(now.getTime() + EXPIRES);
        JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder()
                .issuer(configurations.getIssuer())
                .subject(accountId)
                .audience(configurations.getClientId())
                .expirationTime(exp)
                .issueTime(now);
        JWTClaimsSet claims = builder.build();
        JWSSigner signer = new RSASSASigner(privateKey);
        SignedJWT jwt = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claims);
        jwt.sign(signer);
        return jwt.serialize();
    }

    public boolean verify(String sign)
    {
        try {
            SignedJWT signedJWT = SignedJWT.parse(sign);
            if (System.currentTimeMillis() > signedJWT.getJWTClaimsSet().getExpirationTime().getTime()) {
                return false;
            }
            return signedJWT.verify(new RSASSAVerifier(publicKey));
        } catch (Exception e) {
            return false;
        }
    }

}
