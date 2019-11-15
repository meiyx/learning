package com.study.meiyx.learning.concurrent.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


//如何知道任务是否执行

//    Future<?> submit(Runnable task);
//    <T> Future<T> submit(Runnable task, T result);
//    <T> Future<T> submit(Callable<T> task);

//如何获取任务的执行结果 threadPoolExecutor.submit
public class FutureDemo {

    //new ThreadPoolExecutor.DiscardPolicy()
    public static void main(String[] args) throws Exception{

        LinkedBlockingQueue blockingQueue = new LinkedBlockingQueue<>(5);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 12, 0, TimeUnit.SECONDS, blockingQueue, new ThreadPoolExecutor.DiscardPolicy());
        List<Future> list=new ArrayList();
        for (int i = 0; i < 30; i++) {
            Future future=threadPoolExecutor.submit(new Task(),list);
            System.out.println(i+" 线程池中活跃的线程数" + threadPoolExecutor.getPoolSize());
            if (blockingQueue.size() > 0) {
                System.out.println("--------- 队列中阻塞的线程数" + blockingQueue.size());
            }
            list.add(future);


        }
        threadPoolExecutor.shutdown();

    }
   static class Task implements Runnable{
//        List<Integer> thread;
//        // 通过构造函数传入 result
//        Task(List<Integer> thread){
//            this.thread = thread;
//        }
       public void run() {
           try {
               Thread.sleep(300);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }

}
