package com.study.meiyx.learning.concurrent;

import org.junit.Test;

import java.util.concurrent.*;

public class CompltetionServiceDemo extends BaseDemo {

    /**
     * 需要等待S1执行完后才能保存S2,S3，及时S2,S3先返回
     *
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        // 创建线程池
        ExecutorService executor =
                Executors.newFixedThreadPool(3);
        // 异步向电商 S1 询价
        Future<Integer> f1 =
                executor.submit(
                        () -> getPriceByS1());
        // 异步向电商 S2 询价
        Future<Integer> f2 =
                executor.submit(
                        () -> getPriceByS2());
        // 异步向电商 S3 询价
        Future<Integer> f3 =
                executor.submit(
                        () -> getPriceByS3());

        // 获取电商 S1 报价并保存
        Integer r1 = f1.get();
        executor.execute(() -> save(r1));

        // 获取电商 S2 报价并保存
        Integer r2 = f2.get();
        executor.execute(() -> save(r2));

        // 获取电商 S3 报价并保存
        Integer r3 = f3.get();
        executor.execute(() -> save(r3));
    }

    /**
     * 把获取结果放入到阻塞队列中
     *
     * @throws Exception
     */
    @Test
    public void putBlockingQueueTest() throws Exception {
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(6);
        // 异步向电商 S1 询价
        Future<Integer> f1 =
                executor.submit(
                        () -> getPriceByS1());
        // 异步向电商 S2 询价
        Future<Integer> f2 =
                executor.submit(
                        () -> getPriceByS2());
        // 异步向电商 S3 询价
        Future<Integer> f3 =
                executor.submit(
                        () -> getPriceByS3());

        // 创建阻塞队列
        BlockingQueue<Integer> bq = new LinkedBlockingQueue<>();
        // 电商 S1 报价异步进入阻塞队列
        executor.execute(() -> {
            try {
                bq.put(f1.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 电商 S2 报价异步进入阻塞队列
        executor.execute(() -> {
            try {
                bq.put(f2.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 电商 S3 报价异步进入阻塞队列
        executor.execute(() -> {
            try {
                bq.put(f3.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 异步保存所有报价
        for (int i = 0; i < 3; i++) {
            System.out.println(bq.size());
            Integer r = bq.take();
            executor.execute(() -> save(r));
        }

    }

    @Test
    public void testCompltetionService() throws Exception {

        // 创建线程池
        ExecutorService executor =
                Executors.newFixedThreadPool(3);
        // 创建 CompletionService
        CompletionService<Integer> cs = new
                ExecutorCompletionService<>(executor);
        // 异步向电商 S1 询价
        cs.submit(() -> getPriceByS1());
        // 异步向电商 S2 询价
        cs.submit(() -> getPriceByS2());
        // 异步向电商 S3 询价
        cs.submit(() -> getPriceByS3());
        // 将询价结果异步保存到数据库
        for (int i = 0; i < 3; i++) {
            Integer r = cs.take().get();
            executor.execute(() -> save(r));
        }
    }


    public int getPriceByS1() {
        System.out.println("------获取电商S1的出价:" + 1000);
        sleep(10, TimeUnit.SECONDS);
        return 1000;
    }

    public int getPriceByS2() {
        System.out.println("------获取电商S2的出价:" + 500);
        sleep(5, TimeUnit.SECONDS);

        return 500;
    }

    public int getPriceByS3() {
        System.out.println("------获取电商S3的出价:" + 100);
        sleep(1, TimeUnit.SECONDS);
        return 100;
    }


    public void save(int price) {
        System.out.println("------保存出价:" + price);

    }


}
