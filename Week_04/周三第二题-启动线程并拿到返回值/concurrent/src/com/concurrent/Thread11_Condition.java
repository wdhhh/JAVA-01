package com.concurrent;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread11_Condition {

    private static int result = 0;
    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();
        Lock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    result = sum();
                    condition.signal();
                }finally {
                    lock.unlock();
                }
            }
        });
        thread1.start();

        lock.lock();
        try {
            condition.await();
            System.out.println("异步计算结果为：" + result);
        }finally {
            lock.unlock();
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
