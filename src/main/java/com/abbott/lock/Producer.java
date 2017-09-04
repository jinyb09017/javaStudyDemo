package com.abbott.lock;

/**
 * Created by jinyb on 2017/9/4.
 */
public class Producer implements Runnable {
    private QueueBuffer q;

    Producer(QueueBuffer q) {
        this.q = q;
        new Thread(this, "producer").start();
    }

    public void run() {
        int i = 0;
        while (true) {
            q.put(i++);
        }

    }
}
