package com.abbott.lock;

/**
 * Created by jinyb on 2017/9/4.
 */
public class QueueBuffer {
    int n;
    boolean valueSet = false;

    synchronized int get() {
        if (!valueSet) {
            System.out.println("get wait");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        System.out.println("Got: " + n);
        valueSet = false;
        notify();
        return n;
    }

    synchronized void put(int n) {
        if (valueSet) {
            System.out.println("put wait");
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        this.n = n;
        valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}
