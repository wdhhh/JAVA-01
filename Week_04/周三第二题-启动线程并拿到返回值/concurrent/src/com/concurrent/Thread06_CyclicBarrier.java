package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Thread06_CyclicBarrier {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        List<CompletableFuture> list = new ArrayList<>();
        for (int i=0;i<5;i++){
            CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(()->{
                CyclicBarrierTask task = new CyclicBarrierTask(cyclicBarrier);
                try {
                    return task.call();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            });
            list.add(completableFuture);
        }
        for (CompletableFuture future : list){
            System.out.println("异步计算结果为：" + future.get());
        }

        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");

    }

    public static class CyclicBarrierTask implements Callable {

        private CyclicBarrier cyclicBarrier;

        public CyclicBarrierTask(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public Integer call() throws BrokenBarrierException, InterruptedException {
            this.cyclicBarrier.await();
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
