package org.example;

public interface Bar extends FooInterface{
    // FooInterface 가 제공하는 메소드를 가지고 싶지 않다면?

    // 다시 이런식으로 적으면, Bar를 상속받는 애가 다시 재정의 해야하는 방법으로 할 수 있다.
    // void printNameUpperCase();

    default void printNameUpperCase() {
        System.out.println("bar");
    }

}
