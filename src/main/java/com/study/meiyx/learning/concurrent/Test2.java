package com.study.meiyx.learning.concurrent;

public class Test2 {
    public static void main(String[] args) {
        // NEW 初始化状态，创建线程对象
        Thread thread = new Thread(()->{});
        // 从NEW状态转换到RUNNABLE状态
        thread.start();
        //休眠状态，有时限等待TIM3ED_WAITING
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //休眠状态，无时限等待WAITING
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //线程执行完，从 RUNNABLE 到 TERMINATED 状态

    }
}
