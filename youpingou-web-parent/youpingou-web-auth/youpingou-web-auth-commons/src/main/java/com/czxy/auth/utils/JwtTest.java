package com.czxy.auth.utils;


import com.czxy.auth.entity.UserInfo;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {

    private static final String pubKeyPath = "E:\\rsa\\rsa.pub";

    private static final String priKeyPath = "E:\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20, "jack"),
                privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoieGlucWkiLCJleHAiOjE1MzI1NzA4Mzl9.hy5VaxZIsSkJsYqnZeUhfBDCZ4rR0uKdDDE30E8qOQCqVnWAznIMLTTu6dLQspqrs2HQ7mPqcJXNQg5EcJ9P7M7DaUP6Dw-VG8z20vVDL6vyvEKcgR4S8QVsX6aM2r5sEEohByplSYoI3wkZg6Zxk6hqBvTMGtmsKlzrKxnWhY8";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}