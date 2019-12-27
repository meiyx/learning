package com.study.meiyx.learning.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    Object obj = new Object();
    // **************************Synchronized的使用方式**************************

        // 3.用于方法
        public synchronized void testSys() {
        // 4.可重入
        for(int i = 0;i< 100;i++){

            synchronized (this) {
            }

            synchronized (obj) {
            }
        }

    }

    // **************************ReentrantLock的使用方式**************************
    public void test() throws Exception {
        // 1.初始化选择公平锁、非公平锁
        ReentrantLock lock = new ReentrantLock(true);
        // 2.可用于代码块
        lock.lock();
        try {
            try {
                // 3.支持多种加锁方式，比较灵活; 具有可重入特性
                if (lock.tryLock(100, TimeUnit.MILLISECONDS)) {
                }
            } finally {
                // 4.手动释放锁
                lock.unlock();
            }
        } finally {
            lock.unlock();
        }
    }
}
