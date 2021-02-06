package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Thread05_CountDownLatch {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(5);
        List<CompletableFuture> list = new ArrayList<>();
        for (int i=0;i<5;i++){
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
                System.out.println("thread working...");
                CountDownLatchTask countDownLatchTask = new CountDownLatchTask(latch);
                return countDownLatchTask.call();
            });
            list.add(completableFuture);
        }
        latch.await();
        System.out.println("await...");
        for (CompletableFuture future : list){
            System.out.println("异步计算结果为：" + future.get());
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
    }

    public static class CountDownLatchTask implements Callable {

        private CountDownLatch countDownLatch;

        public CountDownLatchTask(CountDownLatch countDownLatch){
            this.countDownLatch = countDownLatch;
        }

        @Override
        public Integer call(){
            this.countDownLatch.countDown();
            return sum();
        }

        public int sum() {
            ThreadLocalRandom random = ThreadLocalRandom.current();
            return fibo(random.nextInt(10,30));
        }

        private int fibo(int a) {
            if ( a < 2)
                return 1;
            return fibo(a-1) + fibo(a-2);
        }
    }
}
