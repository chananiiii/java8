package org.example;

public class Foo {
    public static void main(String[] args) {
        RunSomething runSomething = (a) -> (a);


        // 사실 함수형 인터페이스는 값이 초기화 되면 안된다.
        // 아무튼 밖에서 선언한 함수는 static 이 있는 것을 가정한다.
        // 만약 함수형 인터페이스에서 사용하였다면! 밖에서는 바꾸지 못한다.
        // pure function 을 달성하기 위해서는 주의가 필요하다. 함수밖의 값을 참조하거나 바꾸려하면 안된다..
        // 오직 함수가 전달 받은 값만 사용하여야한다.
        int baseNumber = 10;
        RunSomething runSomething1 = new RunSomething() {
            @Override
            public int doIt(int number) {
                return number + baseNumber;
            }
        };
    }
}
