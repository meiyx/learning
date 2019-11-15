package com.study.meiyx.learning.concurrent;

import java.util.concurrent.CountDownLatch;

public class LatchDemo implements Runnable {

    private CountDownLatch latch;

    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(50);
        LatchDemo ld = new LatchDemo(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 50; i++) {
            new Thread(ld).start();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));


    }
}