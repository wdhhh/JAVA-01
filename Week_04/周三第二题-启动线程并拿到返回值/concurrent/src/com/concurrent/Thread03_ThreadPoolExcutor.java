package com.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Thread03_ThreadPoolExcutor {

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

    public static class CallableTask implements Callable {

        @Override
        public Integer call() throws Exception {
            return sum();
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

    public static class CustomThreadFactory implements ThreadFactory {
        private AtomicInteger num = new AtomicInteger();

        @Override
        public Thread newThread(Runnable r) {
            String name = "threadByFactory-"+num.getAndIncrement();
            Thread thread = new Thread(r);
            thread.setName(name);
            thread.setDaemon(false);
            return thread;
        }
    }
}
