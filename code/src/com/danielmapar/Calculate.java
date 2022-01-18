package com.danielmapar;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public final class Calculate {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: Calculate [int] [operator] [int]");
            return;
        }

        Calculator calculator = new Calculator();
        calculator.registerOperation("+", (a, b) -> a + b);
        calculator.registerOperation("-", (a, b) -> a - b);
        calculator.registerOperation("/", (a, b) -> a / b);
        calculator.registerOperation("*", (a, b) -> a * b);

        int a = Integer.parseInt(args[0]);
        String operator = args[1];
        int b = Integer.parseInt(args[2]);

        System.out.println(calculator.calculate(a, operator, b));
    }
}

final class Calculator {
    private final Map<String, BinaryOperator<Integer>> operators = new HashMap<>();

    public void registerOperation(String symbol, BinaryOperator<Integer> operator) {
        operators.put(symbol.strip(), operator);
    }

    public int calculate(int a, String operator, int b) {
        return operators.get(operator).apply(a, b);
    }
}