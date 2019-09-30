package com.study.meiyx.learning.concurrent;

public class VolatileExample {
    int x = 0;
    volatile boolean v = false;
    public void writer() {
        x = 42;
        v = true;
    }
    public void reader() {
        if (v == true) {
            System.out.println(v);
        }
    }

    public void startThread(){
        VolatileExample v=new VolatileExample();

        Thread a=new Thread(new Runnable(){
            @Override
            public void run() {
                v.writer();
            }
        });
    }


    public void fun() {
        synchronized (this) { // 此处自动加锁
            // x 是共享变量, 初始值 =10
            if (this.x < 12) {
                this.x = 12;
            }
        } // 此处自动解锁

    }
    public static void main(String[] args) {
        VolatileExample v=new VolatileExample();
        v.reader();
    }

}
