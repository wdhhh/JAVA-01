package com.concurrent;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.LockSupport;

public class Thread09_LockSupport {

    private static int result = 0;

    public static void main(String[] args) {

        long start=System.currentTimeMillis();
        Thread threadMain = Thread.currentThread();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                result = sum();
                LockSupport.unpark(threadMain);
            }
        });
        thread1.start();
        LockSupport.park();


        System.out.println("异步计算结果为：" + result);

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
