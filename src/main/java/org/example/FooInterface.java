package org.example;

public interface FooInterface {
    void printName();

    // 나중에 printNameUpperCase 가 추가가 되면 다른 곳도 다 추가 해주어야하기 때문에
    // default 메서드를 사용한다. 하위 클래스들은 이 기능을 모두 가지게 된다.

    /**
     * @implSpec
     * 이 구 현체는 getName()으로 가져온 문자열을 대문자로 바꿔출력한다.
     */
    default void printNameUpperCase() {
        System.out.println(getName().toUpperCase());
    }

    String getName();

    static void printAnything() {
        System.out.println("foo");
    }
}
