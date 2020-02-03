package com.study.meiyx.learning.concurrent.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class MyLock implements Lock {

    private final Sync sync=new Sync();

    private static class Sync extends AbstractQueuedSynchronizer {
        //是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState()==1;
        }
        /**
         * 获取锁
         * @param acquire
         * @return
         */
        @Override
        protected boolean tryAcquire(int acquire) {
            int state=getState();
            final Thread current = Thread.currentThread();
            if(state==0) {
                if (compareAndSetState(0, 1)) {
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                int next = state + acquire;
                if (next < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(next);
                return true;
            }
            return false;
        }

        /**
         * 释放锁
         * @param release
         * @return
         */
        @Override
        protected boolean tryRelease(int release) {
            if(getState()==0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        /**
         * 获取等待通知组件
         * @return
         */
        Condition newCondition(){
            return new ConditionObject();
        }
    }

    /**
     * 阻塞的获取锁
     */
    public void lock(){
        sync.acquire(1);
    }

    /**
     * 非阻塞的获取锁
     * @return
     */
    public boolean tryLock(){
        return sync.tryAcquire(1);
    }

    /**
     * 释放锁
     */
    public void unlock(){
        sync.release(1);
    }

    /**
     * 获取等待通知组件
     * @return
     */
    public Condition newCondition(){
        return sync.newCondition();
    }

    /**
     * 当前线程是否获取到锁
     * @return
     */
    public boolean isLock(){
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads(){
        return sync.hasQueuedThreads();
    }
    public void lockInterruptibly() throws  InterruptedException{
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException{
        return sync.tryAcquireNanos(1,unit.toNanos(timeout));
    }

    public static void main(String[] args) {

    }
}
