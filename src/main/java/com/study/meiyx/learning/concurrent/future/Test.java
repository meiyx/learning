package com.study.meiyx.learning.concurrent.future;

import java.util.concurrent.*;

public interface Test<V> {

    // 取消任务
    boolean cancel(boolean mayInterruptIfRunning);
    // 判断任务是否已取消
    boolean isCancelled();
    // 判断任务是否已结束
    boolean isDone();
    // 获得任务执行结果
    V get();
    // 获得任务执行结果，支持超时
    V get(long timeout, TimeUnit unit);



    // 提交Runnable任务
    Future<?> submit(Runnable task);
    // 提交Callable任务
    <T> Future<T> submit(Callable<T> task);
    // 提交Runnable任务及结果引用
    <T> Future<T>
    submit(Runnable task, T result);








    // 创建线程池
    ExecutorService executor =
            Executors.newFixedThreadPool(3);
    // 创建CompletionService
    CompletionService<Integer> cs = new
            ExecutorCompletionService<>(executor);
    // 异步向电商S1询价
    cs.submit(()->getPriceByS1());
    // 异步向电商S2询价
    cs.submit(()->getPriceByS2());
    // 异步向电商S3询价
    cs.submit(()->getPriceByS3());
    // 将询价结果异步保存到数据库
    for (int i=0; i<3; i++) {
            Integer r = cs.take().get();
            executor.execute(()->save(r));
        }
}
