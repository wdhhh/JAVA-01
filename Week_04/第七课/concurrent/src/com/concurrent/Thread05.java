package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class Thread05 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(5);
        List<CompletableFuture> list = new ArrayList<>();
        for (int i=0;i<5;i++){
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
                CountDownLatchTask countDownLatchTask = new CountDownLatchTask(latch);
                return countDownLatchTask.call();
            });
            list.add(completableFuture);
        }
        latch.wait();
        for (CompletableFuture future : list){
            System.out.println("异步计算结果为：" + future.get());
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }
}
