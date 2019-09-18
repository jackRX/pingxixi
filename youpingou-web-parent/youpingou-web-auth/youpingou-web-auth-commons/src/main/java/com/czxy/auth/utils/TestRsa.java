package com.czxy.auth.utils;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 8:20 2018-12-18
 */
public class TestRsa {

    /**
     * 测试RSAUtils工具类的使用
     *
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        // rsa.pub文件名随意，例如：rsa.pub、rsa.io、pub.opp、rsapub.tyrf、rsa.txt、、、、
        String pubKeyPath = "E:\\hfr\\rsa.pub";
        String priKeyPath = "E:\\hfr\\rsa.pri";
        // 明文
//        String secret = "czxy-ytjh-qqwd";
//        RsaUtils.generateKey(pubKeyPath,priKeyPath,secret);
//
//
//        System.out.println("ok");




        /**************解密**************/
        PublicKey publicKey = RsaUtils.getPublicKey(pubKeyPath);
        System.out.println("公钥:"+publicKey);


        PrivateKey privateKey = RsaUtils.getPrivateKey(priKeyPath);
        System.out.println("私钥："+privateKey);

    }
}
