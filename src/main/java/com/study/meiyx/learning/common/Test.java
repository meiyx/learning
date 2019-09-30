package com.study.meiyx.learning.common;

public class Test {

    public static void main(String[] args) {
        Integer i  = 6;
        Integer j = 6;
        System.out.println(i==j);

        String a="ABC";
        String A="ABC";
        String Aa=new String("ABC");
        System.out.println(A==Aa);
        System.out.println(((1<3)?"a":"b")+3+4);
        System.out.println(((1<3)?"a":"b")+(3+4));

    }
}
