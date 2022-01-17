package com.danielmapar;

import java.util.ArrayList;
import java.util.List;

public class GenericsExcercise {

    public static void main(String[] args) {
        ArrayList<Object> variables = new ArrayList<Object>();
        Double doubleNumber = 1.5;
        String name = "Sally";
        int intNumber = 1;
        char letter = 'a';

        variables.add(doubleNumber);
        variables.add(name);
        variables.add(intNumber);
        variables.add(letter);

        for (Object variable : variables) {
            GenericsExcercise.displayClassName(variable);
        }

    }
    static <T> void displayClassName(T variable) {
        System.out.println(variable.getClass().getName());
    }

}
;