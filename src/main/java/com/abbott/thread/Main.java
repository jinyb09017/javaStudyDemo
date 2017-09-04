package com.abbott.thread;

/**
 * Created by jinyb on 2017/9/4.
 */
public class Main {

    /**
     * 程序说明：程序启动运行main时候，java虚拟机启动一个进程，主线程main在main()调用时候被创建。
     * 随着调用MitiSay的两个对象的start方法，另外两个线程也启动了，这样，整个应用就在多线程下运行。
     *
     * start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable），什么时候运行是由操作系统决定的。
     * 从程序运行的结果可以发现，多线程程序是乱序执行。因此，只有乱序执行的代码才有必要设计为多线程。
     * Thread.sleep()方法调用目的是不让当前线程独自霸占该进程所获取的CPU资源，以留出一定时间给其他线程执行的机会。
     * 实际上所有的多线程代码执行顺序都是不确定的，每次执行的结果都是随机的。
     */
    public static void main(String[] args) {
        threadTest();

        runableTest();

    }

    public static void threadTest(){
        Thread1 myThread1 = new Thread1("A");
        Thread1 myThread2 = new Thread1("B");

        System.out.println("pri = " + myThread1.getPriority());


        myThread1.start();
        myThread2.start();
    }

    public static void runableTest(){
        new Thread(new Thread2("C")).start();
        new Thread(new Thread2("D")).start();


    }
}
