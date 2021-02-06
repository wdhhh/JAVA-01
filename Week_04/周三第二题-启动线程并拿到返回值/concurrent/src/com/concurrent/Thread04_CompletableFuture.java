package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Thread04_CompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        List<CompletableFuture> list = new ArrayList<>();
        for (int i=0;i<5;i++){
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(()->{
                return "sum = " + sum();
            });
            list.add(completableFuture);
        }
        for (CompletableFuture future : list){
            System.out.println("异步计算结果为："+future.get());
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static int sum() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return fibo(random.nextInt(10,30));
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
