package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Thread03 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();

        int core = Runtime.getRuntime().availableProcessors();
        BlockingDeque<Runnable> blockingDeque = new LinkedBlockingDeque<>(500);
        CustomThreadFactory factory = new CustomThreadFactory();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(core,core,1,TimeUnit.MINUTES,blockingDeque,factory);
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
