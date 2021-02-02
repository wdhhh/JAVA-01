package com.concurrent;

import java.util.concurrent.Callable;

public class CallableTask implements Callable {

    @Override
    public String call() throws Exception {
        String threadName = Thread.currentThread().getName();
        return "sum=" + sum()+ " by " +threadName;
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if ( a < 2)
            return 1;
        return fibo(a-1) + fibo(a-2);
    }
}
