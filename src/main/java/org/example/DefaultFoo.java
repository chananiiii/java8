package org.example;

public class DefaultFoo implements FooInterface {
    String name;

    public DefaultFoo(String name) {
        this.name = name;
    }

    @Override
    public void printName() {
        System.out.println("DefaultFoo");
    }

    @Override
    public String getName() {
        return this.name;
    }
}
