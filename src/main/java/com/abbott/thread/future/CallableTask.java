package com.abbott.thread.future;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {

        System.out.println("callable is run");
        int i=0;
        while (true){

            System.out.println(""+i++);
            Thread.sleep(100);
        }


//
//        return 100;

//        while (true) {
//            System.out.println("Task: Test\n");
//            Thread.sleep(100);
//        }
    }
}
