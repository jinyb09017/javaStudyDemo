package com.abbott.string;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

/**
 * 1、字符串常量池在jdk1.6中存 对象以及对象的引用（方法区的永生代）  在jdk1.7及以后存对象的引用（堆中）
 * 2、字符常量池的作用是避免创建重复的字符串对象。所以这个特性也将导致String对象是不可变对象。
 * 3、
 */
public class StringTest {
    public static void main(String[] args) {

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();
    }


    //我们知道，intern方法是手动的方式，将字符串加入到常量池。
    @Timer
    public void test1() {

        String s0 = "abc";
        String s1 = new String("abc");
        String s2 = s1.intern();
        System.out.println(s1 == s1.intern());
        System.out.println(s1 == s2);
        System.out.println(s0 == s2);


    }

    @Timer
    public void test2() {
        //在堆中创建字符串"hello world"  在常量池中分别创建"hello"与"world"
        String s1 = new String("hello") + new String("World");
        /*
         * 由于常量池中不存在"hello world",但是在堆中有该字符串,
         * 所以将引用s1返回，即s2也指向堆中的字符串
         */
        String s2 = s1.intern();//此处是直接将hello world在堆中的引用返回到常量池中
        System.out.println(s2);       //helloWorld
        //所以地址相同
        System.out.println(s2 == s1);   //true

        String s3 = "helloWorld";
        //常量池中已经存在"helloWorld"的引用，所以s3也直接指向堆中的字符串
        System.out.println(s3 == s1);  //true
    }


    @Timer
    //解释new的时候创建了几个对象
    public void test3() {

        //因为String s1= new String("Hello");这句执行完，
        // 堆上有两个String对象。第一个是.class文件里的字面量"Hello"对应的，
        // 被驻留到字符串常量池里，比如地址0xfff。第二个是new出来的，被赋值给变量s1，
        // 比如地址0xeee。如果执行 String s2 = s1.intern();
        // 那s2被赋于字符串常量池里的那个引用0xfff。所以s1 != s2

        String s1 = new String("hello");
        String s2 = s1.intern();

        System.out.println(s2 == s1);   //false

        String s3 = "hello";
        //常量池中已经存在"helloWorld"的引用，所以s3也直接指向堆中的字符串
        System.out.println(s3 == s2);  //true
    }


    @Timer
    public void test4() {
        //1、在堆中创建java great两个对象，并且添加到常量池。
        //2、在堆中创建javagreat对象，
        //3、将javagreat对象添加到常量池。
        //注意在jdk1.6中执行结果会是false。因为jdk1.6中会将javagreat对象复制到常量池中 常量表中存的是常量池的javagreat地址。

        String s1 = new StringBuilder("java").append("great").toString();
        System.out.println(s1 == s1.intern()); //true


        //这个结果与上面不同的原因是因为，肯定在其它某个地方，java已经被加入到常量池中去了。
        //所以s2在new的过程中，会重新在堆中创建一个java的引用。而s2.intern会返回原来之前java
        //加载的地址。故不相同。
        String s2 = new StringBuilder("ja").append("va").toString();
        System.out.println(s2 == s2.intern());//false
    }
}
