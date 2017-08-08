package com.abbott.proxy.common;

/**
 * Created by jinyb on 2017/8/8.
 */

/**
 * 代理对象
 */
public class MyProxy {
    private Subject subject;

    public MyProxy(Subject subject) {
        this.subject = subject;
    }


    public void request() {
        System.out.println("request before");
        subject.request();
        System.out.println("request end");
    }
}
