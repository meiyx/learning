package com.study.meiyx.learning.concurrent.future;


import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;

@Slf4j
public class FutureTaskDemo {
    @Test
    public void threadPoolRunnableTest() throws Exception {

        // 创建 FutureTask
        FutureTask<Integer> futureTask
                = new FutureTask<>(() -> 1 + 2);
        // 创建线程池
        ExecutorService es =
                Executors.newCachedThreadPool();
        // 提交 FutureTask
        es.submit(futureTask);
        // 获取计算结果
        Integer result = futureTask.get();
        log.info("----执行结果是:" + result);
    }

    @Test
    public void threadRunnableTest() throws Exception {
        // 创建 FutureTask
        FutureTask<Integer> futureTask
                = new FutureTask<>(() -> 1 + 2);
        //创建并启动线程
        Thread t = new Thread(futureTask);
        t.start();
        //获取计算结果
        Integer result = futureTask.get();
        log.info("----执行结果是:" + result);

    }

    @Test
    public void callableTest() throws Exception {
        // 创建任务 T2 的 FutureTask
        FutureTask<String> ft2 = new FutureTask<>(new T2Task());
        // 创建任务 T1 的 FutureTask
        FutureTask<String> ft1 = new FutureTask<>(new T1Task(ft2));
        // 线程 T1 执行任务 ft1
        Thread T1 = new Thread(ft1);
        T1.start();
        // 线程 T2 执行任务 ft2
        Thread T2 = new Thread(ft2);
        T2.start();
        // 等待线程 T1 执行结果
        log.info(ft1.get());

    }

    // T1Task 需要执行的任务：
    // 洗水壶、烧开水、泡茶
    class T1Task implements Callable<String> {
        FutureTask<String> ft2;

        // T1 任务需要 T2 任务的 FutureTask
        T1Task(FutureTask<String> ft2) {
            this.ft2 = ft2;
        }

        @Override
        public String call() throws Exception {
            log.info("T1: 洗水壶...");
            TimeUnit.SECONDS.sleep(1);

            log.info("T1: 烧开水...");
            TimeUnit.SECONDS.sleep(15);
            // 获取 T2 线程的茶叶
            String tf = ft2.get();
            log.info("T1: 拿到茶叶:" + tf);

            log.info("T1: 泡茶...");
            return " 上茶:" + tf;
        }
    }

    // T2Task 需要执行的任务:
    // 洗茶壶、洗茶杯、拿茶叶
    class T2Task implements Callable<String> {
        @Override
        public String call() throws Exception {
            log.info("T2: 洗茶壶...");
            TimeUnit.SECONDS.sleep(1);

            log.info("T2: 洗茶杯...");
            TimeUnit.SECONDS.sleep(2);

            log.info("T2: 拿茶叶...");
            TimeUnit.SECONDS.sleep(1);
            return " 龙井 ";
        }
    }

}
