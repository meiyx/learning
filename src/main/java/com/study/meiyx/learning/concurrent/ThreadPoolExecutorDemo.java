package com.study.meiyx.learning.concurrent;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

    public static void main(String[] args) throws Exception {
        //案例，
        // 游泳池的大小一般10个人，但是高峰期20个挤挤也行；提交的任务：游泳
        // 小饭店标准容纳10个人，高峰期20人挤挤也能行，多拿出10个凳子出来；提交的任务吃饭
        //

        LinkedBlockingQueue blockingQueue = new LinkedBlockingQueue<>(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 12, 0, TimeUnit.SECONDS, blockingQueue, new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(() -> {
//                System.out.println("--------- " + Thread.currentThread().getName() + "线程开始运行---------");
                int count = 1000;
                while (count > 0) {
                    count--;
                }
//                System.out.println("--------- " + Thread.currentThread() + "线程运行结束---------");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
            System.out.println(" 线程池中活跃的线程数" + threadPoolExecutor.getPoolSize());
            if (blockingQueue.size() > 0) {
                System.out.println("--------- 队列中阻塞的线程数" + blockingQueue.size());
            }
        }


        threadPoolExecutor.shutdown();


    }
}
