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

    public void run() {

        // 원래는 final 이 붙어야 바꾸지를 못하는데,
        // 사실상 final 이다. 어디서도 변경을 하지 못한다. (참조는 가능)
        // 로컬클래스와, 익명클래스는 쉐도잉이다.
        // 하지만 람다는 쉐도잉이 되지 않는다.
        int baseNumber = 10;


        // 로컬 클래스
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11; // 같은 이름이지만 내부 스콥이 밖에있는 scope을 가렸다. 이것을 쉐도잉이라고한다.
                System.out.println(baseNumber); // 11 이 찍힌다.
            }
        }

        // 익명 클래스
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer baseNumber) {
                System.out.println(baseNumber);
            }
        };

        // 얘는 위에 있는 baseNumber와 같은 레벨의 scope 이기 때문에,
        // 입력값에 i 대신 baseNumber를 넣으면 오류가 난다.
        // 그리고 람다에서는 밖의 변수를  final 처럼쓰고 있기 때문에,
        // 바꾸려고 하면 에러가 난다.
        IntConsumer printInt = (i) -> System.out.println(i + baseNumber);

        printInt.accept(10);
    }
}
