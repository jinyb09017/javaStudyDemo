package com.abbott.lock;

import java.io.OutputStream;

/**
 * Created by jinyb on 2017/9/4.
 */
public class OutputThread implements Runnable {
    private int num;
    private Object lock;

    OutputThread(int num, Object lock) {
        this.num = num;
        this.lock = lock;
    }

    /**
     * wait和notify方法均可释放对象的锁，但wait同时释放CPU控制权，即它后面的代码停止执行，线程进入阻塞状态，
     *
     * 而notify方法不立刻释放CPU控制权，而是在相应的synchronized(){}语句块执行结束，再自动释放锁。
     */
    public void run() {
        while (true) {
            synchronized (lock) {
                lock.notify();
                System.out.println(num);
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }
}
