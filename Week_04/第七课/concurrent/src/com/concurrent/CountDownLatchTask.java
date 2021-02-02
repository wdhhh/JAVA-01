package com.concurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchTask implements Callable {

    private CountDownLatch countDownLatch;

    public CountDownLatchTask(CountDownLatch countDownLatch){
        this.countDownLatch = countDownLatch;
    }

    @Override
    public String call(){
        this.countDownLatch.countDown();
        String threadName = Thread.currentThread().getName();
        return "sum=" + sum()+ " by " +threadName;
    }

    private int sum() {
        return fibo(36);
    }

    private int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
