package com.study.meiyx.learning.collection;

import java.util.Map;
import java.util.UUID;

public class TestMap {
    public static void main(String[] args) throws Exception{
        Map map = new HashMap<>();
//        for(int i=0;i<1<<20;i++){
//            map.put(UUID.randomUUID().toString(),"");
//        }


        //HashMap在并发下才转成红黑树，偶尔还会有异常
        Thread t = new Thread(() -> {
            for (int i = 0; i < 15000; i++) {
                new Thread(() -> {
                    map.put(UUID.randomUUID().toString(), "");
                }, "ftf" + i).start();
                System.out.println("当前线程："+Thread.currentThread().getName());
            }
        });
        System.out.println("当前线程："+Thread.currentThread().getName());
        t.start();
        t.join();
        System.out.println("最终Map的size="+map.size());
    }
}
