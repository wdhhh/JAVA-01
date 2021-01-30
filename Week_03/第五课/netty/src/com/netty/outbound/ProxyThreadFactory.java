package com.netty.outbound;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程工厂
 */
public class ProxyThreadFactory implements ThreadFactory {

    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private final String namePreFix;
    private final boolean deamon;

    public ProxyThreadFactory(String namePreFix, boolean deamon){
        this.deamon = deamon;
        this.namePreFix = namePreFix;
        SecurityManager s = System.getSecurityManager();
        group = (null != s)?s.getThreadGroup() :Thread.currentThread().getThreadGroup();
    }

    public ProxyThreadFactory(String namePreFix){
        this(namePreFix,false);
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r ,namePreFix + "-thread-" + threadNumber.getAndIncrement(),0);
        t.setDaemon(deamon);
        return t;
    }
}
