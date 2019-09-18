package com.czxy.config;

import com.czxy.auth.utils.RsaUtils;
import lombok.Data;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.RSAUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import sun.misc.Contended;

import javax.annotation.PostConstruct;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 17:50 2019-01-02
 */
@Data
@ConfigurationProperties(prefix = "sc.jwt")
@Configuration
public class JwtProperties {

    private String pubKeyPath;

    private PublicKey publicKey;

    public String getPubKeyPath() {
        return pubKeyPath;
    }

    public void setPubKeyPath(String pubKeyPath) {
        this.pubKeyPath = pubKeyPath;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    @PostConstruct
    public void init(){
        try {
             this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
