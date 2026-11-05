package com.substring.tools;

import dev.langchain4j.agent.tool.Tool;

public class Calculator {

    @Tool(name = "add")
    public static int add(int a, int b) {
        IO.println("Adding " + a + " and " + b);
        return a + b;
    }

    @Tool(name = "subtract")
    public static int subtract(int a, int b) {
        IO.println("Subtracting " + b + " from " + a);
        return a - b;
    }

    @Tool(name = "multiply")
    public static int multiply(int a, int b) {
        IO.println("Multiplying " + a + " and " + b);
        return a * b;
    }

    @Tool(name = "divide")
    public static double divide(int a, int b) {
        IO.println("Dividing " + a + " by " + b);
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return (double) a / b;
    }
}
