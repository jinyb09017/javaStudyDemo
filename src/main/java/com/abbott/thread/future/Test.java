package com.abbott.thread.future;

import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> future = executorService.submit(new CallableTask());

        System.out.println("任务开始执行了");

        int num =  29 & 0;
        System.out.println("12-"+num);


        Thread.sleep(100);

        if(future.cancel(true)){
            System.out.println("task is canceled");
            System.out.println("future called:  " + future.isCancelled());
            System.out.println("future done :  " + future.isDone());

            executorService.shutdown();
            return;
        }

        Integer result = future.get();

        System.out.println("i was blocked until the call is complete");
        System.out.println(result.toString());

    }

}
