package org.example;

import java.util.function.Function;

public class Plus10 implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer a) {
        return a + 10;
    }
}
