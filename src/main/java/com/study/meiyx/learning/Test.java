package com.study.meiyx.learning;

public class Test {
    public static void main(String[] args) {
        String s = new String("hello");
        String s1 = "hello";
        //false
        System.out.println(s == s1);
        // ture
        System.out.println(s.equals(s1));
        //false
        System.out.println(s == null);
        //false
        System.out.println(s.equals(""));

        String arg1[]=new String[10];
        String[] arg2=arg1;
        arg2[0]="hello";
        System.out.println(arg1[0]);

    }
}
