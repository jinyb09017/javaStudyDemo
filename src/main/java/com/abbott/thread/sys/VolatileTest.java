package com.abbott.thread.sys;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class VolatileTest {

    public volatile  int num = 0;

    public static void main(String[] args) throws UnsupportedEncodingException {
        char c = '陈';

        VolatileTest volatileTest = new VolatileTest();
//        volatileTest.start1();
//        volatileTest.start2();



        //测试str的编码。正常的输出则能够表明其编码
        String str = "测试";
        String str1 = new String(str.getBytes("ISO-8859-1"));
        String str2 = new String(str.getBytes("utf-8"));
        String str3 = new String(str.getBytes("GBK"));
        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
    }


    public void start1() {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("start 1 num =" + num);
                }
            }
        });

        thread.start();
    }


    public void start2() {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    Random random = new Random();
                    num = random.nextInt(100);
                    System.out.println("start 2 num = " + num);
                }


            }
        });

        thread.start();
    }
}
