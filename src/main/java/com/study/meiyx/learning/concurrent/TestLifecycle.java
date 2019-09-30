package com.study.meiyx.learning.concurrent;

public class TestLifecycle {
    public static void main(String[] args) {
        Thread th = Thread.currentThread();
        while(true) {
            if(th.isInterrupted()) {
                break;
            }
            // 省略业务代码无数
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

}
