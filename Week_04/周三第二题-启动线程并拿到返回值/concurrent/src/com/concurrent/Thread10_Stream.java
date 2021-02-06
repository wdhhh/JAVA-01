package com.concurrent;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Thread10_Stream {
    private static int result = 0;

    public static void main(String[] args) throws InterruptedException {
        long start=System.currentTimeMillis();

        IntStream.range(0, 1).parallel().forEach(i -> result = sum());

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
