package com.abbott.synchronization;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

public class Volatile {

//    private static sys int num = 0;
    private  int num = 0;

    private  void inc(){
        num++;
    }


    @Timer
    public void TestVolatile(){
        final Volatile vol = new Volatile();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i1 = 0; i1 < 1000; i1++) {
                        vol.inc();
                    }

                }
            }).start();

//            inc();
        }
        System.out.println(Thread.currentThread().getName());



        //注意了，如果是通过intellij启动的时候，会再开起一个线程，执行是否中断的监听。所以这里
        //的数量，需要根据具体情况做调整。
        while(Thread.activeCount()>2) {  //保证前面的线程都执行完
            //表示让出时间片，再次进入可运行的状态。、、或者可以通过join所有线程变可达到同样的目的
            Thread.yield();
        }

        System.out.println(vol.num);

        System.out.println(num);
    }


    public static void main(String[] args) {

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();
    }

}
