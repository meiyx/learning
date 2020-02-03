package com.study.meiyx.learning.concurrent.threadpool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    private  int a=1;

    public static void main(String[] args) throws Exception {
        //案例，
        // 小饭店标准容纳10个人，高峰期20人挤挤也能行，多拿出10个凳子出来；提交的任务吃饭
        //

        LinkedBlockingQueue blockingQueue = new LinkedBlockingQueue<>(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 12, 0, TimeUnit.SECONDS, blockingQueue, new MyRejectedExecutionHandler());
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.execute(() -> {
                int count = 1000;
                while (count > 0) {
                    count--;
                }
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
