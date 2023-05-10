package com.oik.api.utils.jwt;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author 15093
 * @description TODO
 * @date 2023/5/10 16:19
 */
@Data
@ConfigurationProperties("rsa.key")     //指定配置文件的key
public class RsaKeyProperties {

    private String pubKeyPath;

    private String priKeyPath;

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @PostConstruct
    public void createKey() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }
}

