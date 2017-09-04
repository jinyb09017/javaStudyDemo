package com.abbott.lock;

/**
 * Created by jinyb on 2017/9/4.
 */
public class Consumer implements Runnable {
    private QueueBuffer q;

    Consumer(QueueBuffer q) {
        this.q = q;
        new Thread(this, "consumer").start();
    }

    public void run() {
        while (true) {
            q.get();
        }
    }
}
