package com.abbott.utils;

import com.abbott.annotation.Timer;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 计算方法所用时间
 */
public class TimerUtil {
    public void getTime() {
        // 获取当前类型名字
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            System.out.println("all stack:" + stackTraceElement.getClassName());
        }
        System.out.println("current className(expected):" + className);
        try {
            Class c = Class.forName(className);
            Object object = c.newInstance();
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {

                System.out.println("method :" + method.getName());

//                Annotation[] annotations = method.getAnnotations();
//                for (Annotation annotation : annotations) {
//                    System.out.println("annotation :"+annotation);
//
//                }
                //方法是否包含此注解
                if (method.isAnnotationPresent(Timer.class)) {
                    method.setAccessible(true);
                    long start = System.nanoTime();
                    method.invoke(object);
                    long end = System.nanoTime();

                    long total = end - start;
                    System.out.println(method.getName() + "耗时：" + total);
                }

            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
