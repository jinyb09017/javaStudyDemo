package com.abbott.thread.interupt;

import com.abbott.annotation.Timer;
import com.abbott.utils.TimerUtil;

/**
 * 线程中断
 * 在线程执行后，我们如何中断呢。
 *
 * 1、设置标志位。(本质上是让run方法执行完)
 * 2、使用interrupt
 */
public class InterruptTest {

    class MyThread extends Thread{
        //多线程下，可以保证更新后可以及时更新到内存。并能将寄存器中的缓存设置为无效。保证数据的一致性。
        private volatile boolean isCanceled;//

        @Override
        public void run() {

            //在循环单元这里做变量判断即可
            while (!isCanceled){
                System.out.println("i am running");
            }

            System.out.println("end");
        }

        public void setCanceled(){
            isCanceled = true;
        }
    }



    @Timer
    public void testFlag(){
        MyThread thread = new MyThread();
        thread.start();

        thread.setCanceled();

//        try {
//            Thread.sleep(1000);
//            thread.setCanceled();
//            //这步非常重要，让
////            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    public static void main(String[] args) {

        TimerUtil timerUtil = new TimerUtil();
        timerUtil.getTime();
    }


    class MyRun implements Runnable{
        private String name;
        public MyRun(String name){
            this.name = name;
        }

        @Override
        public void run() {
            int i=0;
            while (i < 100){
                System.out.println(name + " is running");
                i ++;
            }

        }
    }


    @Timer
    /**
     * join操作表示，当前线程进入休眠，等join的线程执行结束后，再进入可运行状态。
     */
    public void joinTest() throws InterruptedException {
        MyRun myRun1 = new MyRun("a");
        MyRun myRun2 = new MyRun("b");
        Thread thread1 = new Thread(myRun1,myRun1.name);
        Thread thread2 = new Thread(myRun2,myRun2.name);

        thread1.start();
        thread2.start();

        System.out.println("main 操作");

        //自己执行完后，主线程再执行
        thread2.join();
        thread1.join();

        System.out.println("main end");

    }

    class InterruptThread extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("in run i was running");
                Thread.sleep(2000);
                System.out.println("woke up");
            } catch (InterruptedException e) {
//                e.printStackTrace();
                System.out.println("i was interrupt");
                return;
            }

            System.out.println("leave normally");
        }
    }



    @Timer
    //
    public void interruptTest() throws InterruptedException {
        InterruptThread interruptThread = new InterruptThread();
        interruptThread.start();
        Thread.sleep(1000);
        //注意了，这里只是给当前线程打下一个中断的标志。一般我们可以通过isInterrupted来判断
        //是否中断，进行跳出循环。
        //当然我们也可以通过sleep触发中断异常，来结束run方法
        //另外一种情况，如果线程在调用 sleep()方法前被中断，那么该中断称为待决中断，它会在刚调用 sleep()方法时，立即抛出 InterruptedException 异常。
        //需要注意的是：而一旦 sleep()方法抛出异常，它将清空中断标志
        interruptThread.interrupt();


    }

}
