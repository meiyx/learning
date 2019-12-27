package com.study.meiyx.learning.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class TestAtomic {
    AtomicLong count = new AtomicLong(0);
    @org.junit.Test
    public void add10K() {
        int idx = 0;
        while(idx++ < 10000) {
            count.getAndIncrement();
        }
        System.out.println("count最后结果" + count);

    }
}
