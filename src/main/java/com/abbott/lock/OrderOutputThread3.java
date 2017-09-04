package com.abbott.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jinyb on 2017/9/4.
 * 目标：
 * 按顺序输出1,2,3 10次。
 * 
 * 思路分析
 * 1、使用AtomicInteger保证线程安全。在Java语言中，++i和i++操作并不是线程安全的，在使用的时候，
 * 不可避免的会用到synchronized关键字。而AtomicInteger则通过一种线程安全的加减操作接口
 *
 * 2、通过数据递增求余的方法，来保证输出的顺序性
 *
 * 3、使用求余的方法，如果余数与目标一致，则正常输出，并唤醒所有其他等待的线程。如果不一致，则线程等待。
 *
 * features:
 * 这里使用Integer来实现数据的递增。但是因为无法使用notify方法来实现锁定。
 *
 */
public class OrderOutputThread3 implements Runnable {
    private int num;
    private int flag;
    //number++;之后已经是另一个object了啊。Integer是不可修改类，++之后是一个新的对象了。
    //所有使用integer会抛出异常 java.lang.IllegalMonitorStateException。
    private Integer atomicInteger;

    OrderOutputThread3(int num, int flag, Integer atomicInteger) {
        this.num = num;
        this.flag = flag;
        this.atomicInteger = atomicInteger;
    }

    public void run() {
        int count = 0;
        while (true) {
            synchronized (atomicInteger){

                if (atomicInteger % 3 == flag) {
                    System.out.println(num);
                    atomicInteger = atomicInteger + 1;
                    count++;

                    //唤醒其它线程
                    atomicInteger.notifyAll();
                    if (count >= 10) {
                        break;
                    }


                }else{


                    //如果不能被整除，则直接等待其它线程唤醒
                    try {
                        atomicInteger.wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }


                }
            }

        }

    }
}
