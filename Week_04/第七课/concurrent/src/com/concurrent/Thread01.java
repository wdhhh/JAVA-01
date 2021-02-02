package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Thread01 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        CallableTask callableTask = new CallableTask();
        List<FutureTask> list = new ArrayList<>();
        for (int i=0;i<5;i++){
            FutureTask<String> future = new FutureTask<>(callableTask);
            new Thread(future).start();
            list.add(future);
        }
        for (FutureTask futureTask : list){
            System.out.println("异步计算结果为：" + futureTask.get());
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}
