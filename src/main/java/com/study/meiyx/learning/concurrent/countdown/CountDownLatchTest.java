package com.study.meiyx.learning.concurrent.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * 说明await(),countDown()方法做的作用
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        CountDownLatch latch=new CountDownLatch(50);
        LatchDemo ld=new LatchDemo(latch);
        long start=System.currentTimeMillis();
        for(int i=0;i<50;i++){
            new Thread(ld).start();
        }
        try {
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        long end=System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
    }
}
