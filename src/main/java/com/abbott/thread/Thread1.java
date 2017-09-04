package com.abbott.thread;

/**
 * Created by jinyb on 2017/9/4.
 */
public class Thread1 extends Thread {
    private String name;

    public Thread1(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行：" + i);
            try {
                //Thread.sleep()方法调用目的是不让当前线程独自霸占该进程所获取的CPU资源，以留出一定时间给其他线程执行的机会。
//                sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
