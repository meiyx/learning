package com.study.meiyx.learning.concurrent;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class BaseDemo {

    /**
     * 延迟 u单位 t 时长
     * @param t
     * @param u
     */
    public void sleep(int t, TimeUnit u) {
        try {
            u.sleep(t);
        } catch (InterruptedException e) {
        }
    }

    /**
     * 获取给定范围的随机数
     * @param origin
     * @param bound
     * @return
     */
    public int getRandom(int origin, int bound) {
        Random random = new Random();
        if (origin < bound) {
            int n = bound - origin;
            if (n > 0) {
                return random.nextInt(n) + origin;
            } else {  // range not representable as int
                int r;
                do {
                    r = random.nextInt();
                } while (r < origin || r >= bound);
                return r;
            }
        } else {
            return random.nextInt();
        }
    }
}
