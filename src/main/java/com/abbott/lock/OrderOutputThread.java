package com.abbott.lock;

/**
 * Created by jinyb on 2017/9/4.
 */
public class OrderOutputThread implements Runnable {
    private int num;
    private Object prev;
    private Object self;

    OrderOutputThread(int num, Object prev, Object self) {
        this.num = num;
        this.prev = prev;
        this.self = self;
    }

    public void run() {

        while (true){
            synchronized (prev){
                synchronized (self){
                    System.out.println(num);
                    //唤醒下一个线程
                    self.notify();
                }

                //阻塞当前线程，待续唤醒
                try {
                    prev.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
