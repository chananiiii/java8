package org.example;

@FunctionalInterface
public interface RunSomething {
    int doIt(int number);


    // 인터페이스의 다양한 타입의 메서드를 정의할 수 있도록 업그레이드 되었다.
    // 함수형 인터페이스이다. 아래가 있어도..
    static void printName() {
        System.out.println("chanwan");
    }

    default void printAge() {

    }
}
