package com.abbott.synchronization;

public class Test {
    public volatile  int inc = 0;
//    public   int inc = 0;
    public void increase() {
//        try {
//            Thread.sleep();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        inc++;
    }

    public static void main(String[] args) {
        final Test test = new Test();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<10000;j++)
                        test.increase();
                };
            }.start();
        }

        while(Thread.activeCount()>2)  //保证前面的线程都执行完
            Thread.yield();

        Thread th[] = new Thread[Thread.activeCount()];
        // returns the number of threads put into the array
        Thread.enumerate(th);

        // prints active threads
        for (int i = 0; i < th.length; i++) {
            System.out.println(i + ": " + th[i]);
        }

        System.out.println(test.inc);
    }
}