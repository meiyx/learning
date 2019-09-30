package com.study.meiyx.learning.collection;

import java.util.*;
import java.util.HashMap;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

public class TestHashMap {
    public static void main(String[] args) {
        Map<Integer,String> map=new HashMap<>();
        map.put(5,"a");
        map.put(2,"b");
        map.put(1,"e");

        Map<Integer,Object> result = new LinkedHashMap<>();

        map.entrySet().stream().sorted(Map.Entry.<Integer,String>comparingByValue()).forEachOrdered(e->result.put(e.getKey(),e.getValue()));
        System.out.println(result);

        List<String> l = new ArrayList(Arrays.asList("one", "two"));
        Stream<String> sl = l.stream();
        l.add("three");
        String s = sl.collect(joining(" "));
        System.out.println(s);

    }
}
