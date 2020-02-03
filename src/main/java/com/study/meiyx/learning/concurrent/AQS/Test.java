package com.study.meiyx.learning.concurrent.AQS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
    volatile long count = 0;
    MyLock lock=new MyLock();

    public  void add10K() {
        lock.lock();
        long idx = 0;

        try {
            while (idx++ < 10000L) {
                count += 1;
            }

        }finally {
            lock.unlock();
        }



    }
    public static void main(String[] args) throws Exception {
        Test test=new Test();
        ExecutorService executorService= Executors.newFixedThreadPool(6);
        for(int i=0;i<6;i++) {
            executorService.submit(() -> {
                test.add10K();
//                System.out.println(" currentThread:"+Thread.currentThread());
//                System.out.println(" currentThread isLock:"+test.lock.isLock());
//                System.out.println(" hasQueuedThreads:"+test.lock.hasQueuedThreads());
                ;



            });
        }
        Thread.sleep(1000);
        System.out.println("count="+test.count);
        executorService.shutdown();

    }

}
