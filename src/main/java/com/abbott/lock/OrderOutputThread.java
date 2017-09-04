package com.abbott.lock;

/**
 * Created by jinyb on 2017/9/4.
 * 目标：
 * 建立三个线程，A线程打印10次1，B线程打印10次2,C线程打印10次3，要求线程同时运行，交替打印10次ABC。这个问题用Object的wait()，notify()就可以很方便的解决。
 *
 * 思路分析
 *
 * 1、先来解释一下其整体思路，从大的方向上来讲，该问题为三线程间的同步唤醒操作，
 *
 * 2、为了控制线程执行的顺序，那么就必须要确定唤醒、等待的顺序，所以每一个线程必须同时持有两个对象锁，才能继续执行。
 *
 * 3、一个对象锁是prev，就是前一个线程所持有的对象锁。还有一个就是自身对象锁。
 *
 * 4、主要的思想就是，为了控制执行的顺序，必须要先持有prev锁，也就前一个线程要释放自身对象锁，再去申请自身对象锁，两者兼备时打印
 *
 *
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
