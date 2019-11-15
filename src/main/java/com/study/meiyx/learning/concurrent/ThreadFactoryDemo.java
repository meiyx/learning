package com.study.meiyx.learning.concurrent;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ThreadFactoryDemo {

    public static void main(String[] args) {
        new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build();
        System.out.println(Thread.currentThread().getName());
    }
}
