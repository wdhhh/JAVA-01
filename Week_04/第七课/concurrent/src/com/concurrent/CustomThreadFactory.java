package com.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {
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
