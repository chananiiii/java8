package org.example;

import java.util.function.BinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

public class Lamda {

    public static void main(String[] args) {
        Foo foo = new Foo();
        foo.run();


        Supplier<Integer> get10 = () -> 10;

        BinaryOperator<Integer> get_10 = (a, b) -> a + b;
        // BinaryOperator<Integer> get_10 = (Integer a, Integer b) -> a + b;
    }
}
