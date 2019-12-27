package com.study.meiyx.learning.concurrent;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestWriteInRead {
    public void doTest() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock(false); // unfair!
        // obtain read lock
        System.out.println("MainThread - lock");
        lock.readLock().lock();

        System.out.println("MainThread - lock");
        lock.readLock().lock();
        System.out.println("MainThread - unlock");
        lock.readLock().unlock();

        // start Writer and wait that Writer takes the lock
        new Thread(new WriteThread(lock)).start();
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }

        // start Reader and wait that Reader takes the lock
        new Thread(new ReadThread(lock)).start();
        try {
            Thread.sleep(1000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("MainThread - lock");
        lock.readLock().lock();
        System.out.println("MainThread - unlock");
        lock.readLock().unlock();

        System.out.println("MainThread - unlock");
        lock.readLock().unlock();
    }

    public static void main(String[] args) {

        TestWriteInRead tester = new TestWriteInRead();
        tester.doTest();
    }

    class ReadThread extends Thread {

        public void run() {
            System.out.println("                         ReadThread - lock");
            lock.readLock().lock();

            System.out.println("                         ReadThread - unlock");
            lock.readLock().unlock();
        }

        public ReadThread (ReadWriteLock lock) {
            this.lock = lock;
        }

        ReadWriteLock lock;
    }

    class WriteThread extends Thread {

        public void run() {
            System.out.println("                                                  WriteThread - lock");
            lock.writeLock().lock();

            System.out.println("                                                  WriteThread - unlock");
            lock.writeLock().unlock();
        }

        public WriteThread (ReadWriteLock lock) {
            this.lock = lock;
        }

        ReadWriteLock lock;
    }
}
