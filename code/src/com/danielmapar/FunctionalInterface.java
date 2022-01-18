package com.danielmapar;

@java.lang.FunctionalInterface
interface MyInterface {

    boolean authorize(int val);

    default boolean authorize(String value) {
        return true;
    }
}

public class FunctionalInterface {

    public static void main(String[] args) {

        MyInterface func = (val) -> true;
        System.out.println(func.authorize(1));
    }
}
