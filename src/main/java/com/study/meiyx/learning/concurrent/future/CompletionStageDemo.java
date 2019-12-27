package com.study.meiyx.learning.concurrent.future;

import com.study.meiyx.learning.concurrent.BaseDemo;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletionStageDemo  extends BaseDemo {

    //串行关系
    @Test
    public void test() {

        CompletableFuture<String> f0 =
                CompletableFuture.supplyAsync(
                        () -> "Hello World")      //①
                        .thenApply(s -> s + " QQ")  //②
                        .thenApply(String::toUpperCase);//③

        System.out.println(f0.join());

    }

    //or聚合关系
    @Test
    public void orCombineTest() {
        CompletableFuture<String> f1 =
                CompletableFuture.supplyAsync(() -> {
                    int t = getRandom(5, 10);
                    System.out.println("-----线程T1 sleep十时间：" + t);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f2 =
                CompletableFuture.supplyAsync(() -> {
                    int t = getRandom(5, 10);
                    System.out.println("-----线程T2 sleep十时间：" + t);
                    sleep(t, TimeUnit.SECONDS);
                    return String.valueOf(t);
                });

        CompletableFuture<String> f3 =
                f1.applyToEither(f2, s -> {
                    System.out.println("-----s=" + s);
                    return s;
                });

        System.out.println("-----" + f3.join());

    }

    @Test
    public void testException() {
        CompletableFuture<Integer> f0 =
                CompletableFuture.supplyAsync(
                        () -> 7 / 0)
                        .thenApply(r -> r * 10)
                        .exceptionally(e -> 0);
        System.out.println(f0.join());
    }



    @Test
    public void testRandom() {
        System.out.println(this.getRandom(5, 10));
    }

}
