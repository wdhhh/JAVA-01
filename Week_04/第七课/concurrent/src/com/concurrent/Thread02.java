package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Thread02 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //ExecutorService executor = Executors.newSingleThreadExecutor();
        long start=System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<FutureTask> list = new ArrayList<>();
        for (int i=0;i<5;i++){
            CallableTask callableTask = new CallableTask();
            FutureTask<String> future = new FutureTask<>(callableTask);
            executor.submit(future);
            list.add(future);
        }
       for (FutureTask futureTask : list){
           System.out.println("异步计算结果为：" + futureTask.get());
       }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

       executor.shutdown();

    }
}
