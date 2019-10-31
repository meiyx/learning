package com.study.meiyx.learning.lambda;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Stream.of;

public class TestStream {

    static void mapflatMapTest(){
        List<String> collected = of("a", "b").collect(Collectors.toList());

    }

    public static void main(String[] args) {

    }
}
