package com.abbott.proxy;

import com.abbott.annotation.GET;
import com.abbott.annotation.Query;
import com.abbott.proxy.auto.ProxyHandler;
import com.abbott.proxy.common.MyProxy;
import com.abbott.proxy.common.RealSubject;
import com.abbott.proxy.common.Subject;
import com.abbott.proxy.demo.Api;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;

/**
 * Created by jinyb on 2017/8/8.
 */
public class Client {
    public static void main(String[] args) {
        Subject subject = new RealSubject();
        MyProxy proxy = new MyProxy(subject);
        proxy.request();

        autoProxy();

        apiTest();
    }


    public static void autoProxy() {
        RealSubject subject = new RealSubject();
        System.out.println(subject);
        ProxyHandler proxyHandler = new ProxyHandler(subject);
        Subject proxy = (Subject) Proxy.newProxyInstance(Client.class.getClassLoader(), RealSubject.class.getInterfaces(), proxyHandler);
        proxy.request();
        System.out.println(proxy);
    }

    public static void apiTest() {
        Api api = (Api) Proxy.newProxyInstance(Api.class.getClassLoader(), new Class<?>[]{Api.class}, new InvocationHandler() {
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Integer pageCount = (Integer) args[0];
                Integer pageIndex = (Integer) args[1];

                System.out.println("参数：" + pageCount + "," + pageIndex);
                System.out.println("方法名：" + method.getName());

                Annotation[] annotations = method.getAnnotations();
                System.out.println("注解：" + annotations[0].toString());

                Type[] parameterTypes = method.getGenericParameterTypes();
                Annotation[][] parameterAnnotationsArray = method.getParameterAnnotations();

                for (Type parameterType : parameterTypes) {
                    System.out.println(parameterType);
                }

                //这里是一个参数对应一个注释的数组。因为一个参数可以接收多个注解器
                System.out.println("parameterAnnotationsArray length：" + parameterAnnotationsArray.length);

                int i =0;
                for (Annotation[] annotations1 : parameterAnnotationsArray) {
                    System.out.println("param = "+method.getParameters()[i]);
                    System.out.println("annotations length：" + annotations1.length);
                    for (Annotation annotation : annotations1) {
                        System.out.println(annotation);
                        if(annotation.annotationType() == Query.class){
                            System.out.println("good test");
                        }
                    }
                    i++;
                }
                return pageCount + pageIndex;
            }
        });


        int result = api.getData(1,5, 8, "www", "good");
        System.out.println(result);
    }
}
