package com.abbott.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jinyb on 2017/9/4.
 *
 * wait(): Causes the current thread to wait until another thread invokes the notify() method or the notifyAll() method for this object.
 *
 * notify(): Wakes up a single thread that is waiting on this object's monitor.
 *
 * notifyAll(): Wakes up all threads that are waiting on this object's monitor.
 * 这三个方法，都是Java语言提供的实现线程间阻塞(Blocking)和控制进程内调度(inter-process communication)的底层机制。在解释如何使用前，先说明一下两点：
 *
 * 1. 正如Java内任何对象都能成为锁(Lock)一样，任何对象也都能成为条件队列(Condition queue)。而这个对象里的wait(),
 * notify()和notifyAll()则是这个条件队列的固有(intrinsic)的方法。
 *
 * 2. 一个对象的固有锁和它的固有条件队列是相关的，为了调用对象X内条件队列的方法，你必须获得对象X的锁。
 * 这是因为等待状态条件的机制和保证状态连续性的机制是紧密的结合在一起的。
 *
 * 根据上述两点，在调用wait(), notify()或notifyAll()的时候，必须先获得锁，且状态变量须由该锁保护，而固有锁对象与固有条件队列对象又是同一个对象。
 * 也就是说，要在某个对象上执行wait，notify，先必须锁定该对象，而对应的状态变量也是由该对象锁保护的。
 */
public class LockMain {
    public static void main(String[] args)  {

//        testNotify();
//        orderOutPut();
//        orderThreeOutPut();
//        orderThreeOutPut2();
        orderThreeOutPut3();
    }


    /**
     * 抛出异常
     * Exception in thread "main" java.lang.IllegalMonitorStateException
     * 这是因为，如果没有锁，wait和notify有可能会产生竞态条件(Race Condition)。考虑以下生产者和消费者的情景：
     * @throws InterruptedException
     */
    public static void testLock() throws InterruptedException {
        Object obj = new Object();
        obj.wait();
        obj.notifyAll();
    }


    public static void testNotify(){
        QueueBuffer q = new QueueBuffer();
        new Producer(q);
        new Consumer(q);
        System.out.println("Press Control-C to stop.");
    }

    public static void orderOutPut()  {
        Object lock = new Object();
        new Thread(new OutputThread(1,lock)).start();
        new Thread(new OutputThread(2,lock)).start();

    }

    public static void orderThreeOutPut(){
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();

        new Thread(new OrderOutputThread(1,c,a)).start();

        //确保按顺序A、B、C执行
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new OrderOutputThread(2,a,b)).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new OrderOutputThread(3,b,c)).start();
    }


    public static void orderThreeOutPut2(){
        AtomicInteger synObj = new AtomicInteger(1);
        new Thread(new OrderOutputThread2(1,1,synObj)).start();

        new Thread(new OrderOutputThread2(2,2,synObj)).start();

        new Thread(new OrderOutputThread2(3,0,synObj)).start();
    }


    public static void orderThreeOutPut3(){
        Integer integer = new Integer(1);
        new Thread(new OrderOutputThread3(1,1,integer)).start();

        new Thread(new OrderOutputThread3(2,2,integer)).start();

        new Thread(new OrderOutputThread3(3,0,integer)).start();
    }
}
