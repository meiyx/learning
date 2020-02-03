package com.study.meiyx.learning.concurrent.threadpool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class MyRejectedExecutionHandler implements RejectedExecutionHandler {

    public MyRejectedExecutionHandler() { }

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        log.info("Task " + r.toString() +
                " rejected from " + executor.toString());
    }
}
