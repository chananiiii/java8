package org.example;

import java.util.function.*;

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


        Plus10 plus10 = new Plus10();
        System.out.println(plus10.apply(1)); // 11

        // 람다식 이용
        Function<Integer, Integer> plus10_2 = (i) ->  i+10;
        // Function<Integer, Integer> plus10_2 = (i) -> {return i+10;};

        Function<Integer, Integer> multiply2 = (i) -> i * 2;
        System.out.println(multiply2.apply(2)); //4

        plus10.compose(multiply2); // compose : 입력 값을 가지고 먼저 뒤에오는 함수를 적용하고, 그 결과값을 plsu10의 입력값으로 사용한다.
        Function <Integer, Integer> multiply2AndPlus10 = plus10.compose(multiply2);
        System.out.println(multiply2AndPlus10.apply(2));

        System.out.println(plus10.andThen(multiply2).apply(2)); // plus10 먼저 진행 후, multiply 진행한다. andThen 앞에서 부터 실행

        // 컨슈머는 반환만 한다.
        Consumer <Integer> printT = (i) -> System.out.println(i);
        printT.accept(10);

        // 입력 값은 없고, 반환만하는 함수형 인터페이스이다.
        Supplier <Integer> get10 = () -> 10;
        System.out.println(get10.get());

        // true, false 를 리턴해주는 함수형 인터페이스이다.
        Predicate <String> startsWithChanwan = (s) -> s.startsWith("chanwan");
        Predicate <Integer> isOdd = i -> i%2 == 0;
        if(startsWithChanwan.test("chanwan")) {
            System.out.println("chanwan come in.");
        }
        if(!startsWithChanwan.test("test")) {
            System.out.println("chanwan not come in.");
        }

        // 입력값의 타입과 결과값의 타입이 같으면 unaryOperator 사용가능
        // Function 을 상속받았다.
        UnaryOperator<Integer> plus = (i) ->  i+10;

        // 2개의 인자를 받고 1개의 객체를 리턴하는 함수형 인터페이스
        BiFunction<String, String, String> biFunction = new BiFunction<String, String, String>() {
            @Override
            public String apply(String a, String b) {
                return a + b;
            }
        };
        System.out.println(biFunction.apply("a", "b"));

        // BinaryOpertor 는 BiFunction을 상속받았고, 3개 다 타입이 같을 경우를 기대하고 사용한다.
        BinaryOperator<String> binaryOperator = (a, b) -> a + b;
        System.out.println(binaryOperator.apply("aa", "bb"));
    }
}
