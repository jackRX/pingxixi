package com.czxy;

/**
 * @Author: huangfurong
 * @Description:
 * @Date: Create in 9:56 2018-12-17
 */
public class B extends A{

    @Override
    public void rr() {
        super.hh();
    }

    @Override
    public void hh() {

    }

    public static void main(String[] args) {
        try {
            System.out.println(1/0);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }finally {
            System.out.println("执行");
        }
    }

}
