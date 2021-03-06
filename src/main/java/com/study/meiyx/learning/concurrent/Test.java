package com.study.meiyx.learning.concurrent;

import java.util.concurrent.atomic.AtomicLong;

public class Test {
    private static long count = 0;

    public void add10K() {
        long idx = 0;
        while (idx++ < 10000L) {
            count += 1;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName()+":"+count);
        }


    }

    public static long calc() {
        Test test = new Test();
        //开启俩个线程
        Thread t1 = new Thread(() -> {
            test.add10K();
        });
        Thread t2 = new Thread(() -> {
            test.add10K();
        });
        //处理俩个线程
        t1.start();
        t2.start();
        try {
            //等待俩个线程执行结束
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        Test2 t2 = new Test().new Test2();
        System.out.println("count最后结果" + calc());


        try {
            //业务逻辑
        } catch (RuntimeException x) {
            //按需处理
        } catch (Throwable x) {
            //按需处理
        }
    }


    public class Test2 {
        volatile long count = 0;

        public synchronized void add10K() {
            int idx = 0;
            while (idx++ < 10000) {
                count += 1;
            }
        }
    }


    public class Test3 {
        AtomicLong count =
                new AtomicLong(0);

        public void add10K() {
            int idx = 0;
            while (idx++ < 10000) {
                count.getAndIncrement();
            }
        }
    }



}
