package com.abbott.proxy.common;

/**
 * Created by jinyb on 2017/8/8.
 */
public class RealSubject implements Subject {
    public void request() {
        System.out.println("i want request web");
    }


}
