package com.abbott.proxy.auto;

import com.abbott.proxy.common.Subject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by jinyb on 2017/8/8.
 */
public class ProxyHandler implements InvocationHandler {
    Subject subject;

    public ProxyHandler(Subject subject) {
        this.subject = subject;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("auto proxy before");
        Object object = method.invoke(subject, args);
        System.out.println("auto proxy end");
        return object;
    }
}
