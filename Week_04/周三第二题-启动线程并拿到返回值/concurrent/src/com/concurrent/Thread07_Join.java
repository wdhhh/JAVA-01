package com.concurrent;

import java.util.concurrent.ThreadLocalRandom;

public class Thread07_Join {
    private static Integer[] results = new Integer[5];
    private static int num = 5;

    public static void main(String[] args) throws InterruptedException {

        long start=System.currentTimeMillis();
        for (int i=0;i<num;i++){
            int finalI = i;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    results[finalI] = sum();
                }
            });
            thread.start();
            thread.join();
        }
        for (Integer result:results){
            System.out.println("异步计算结果为：" + result);
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
