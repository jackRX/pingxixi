package com.czxy;

import org.junit.Test;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 8:47 2018-12-29
 */
public class A  {
    public static void main(String[] args) throws Exception {
        Class<?> c1 = Class.forName("com.czxy.E");
        Object obj = c1.newInstance();
        Method method = c1.getMethod("getInt",int.class);
        Integer num =(Integer) method.invoke(obj, 10);
        System.out.println(num++);
    }
}
