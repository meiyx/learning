package com.study.meiyx.learning.concurrent;

public class FinalFieldExample {
    final int x;
    int y;
    static FinalFieldExample f;
    public FinalFieldExample() {
        x = 3;
        y = 4;
    }

    static void writer() {
        f = new FinalFieldExample();
    }

    static void reader() {
        if (f != null) {
            int i = f.x;
            int j = f.y;
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        writer();
        reader();
    }

}
