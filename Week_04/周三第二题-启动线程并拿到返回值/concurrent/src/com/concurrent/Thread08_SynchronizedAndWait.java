package com.concurrent;

import java.util.concurrent.ThreadLocalRandom;


public class Thread08_SynchronizedAndWait {

    private static int a = 0;
    public static Object obj = new Object();

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    a = sum();
                    obj.notify();
                }
            }
        });
        thread1.start();

        synchronized (obj){
            obj.wait();
            System.out.println("异步计算结果为："+a);
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
