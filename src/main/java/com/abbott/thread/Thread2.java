package com.abbott.thread;

/**
 * Created by jinyb on 2017/9/4.
 */
public class Thread2 implements Runnable {
    private String name;

    public Thread2(String name) {
        this.name = name;
    }


    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行  :  " + i);
            try {
                Thread.sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
