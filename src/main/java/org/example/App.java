package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class App {
    public static void main(String[] args) {
        Greeting greeting = new Greeting();
        // 인스턴스 메소드를 참조 할 수도 있다.
        UnaryOperator<String> hi2 = greeting :: hello;
        System.out.println(hi2.apply("chanwan"));

        // 메소드 레퍼런스
        UnaryOperator<String> hi1 = Greeting :: hi;

        // 생성자도 참조할 수 있다.
        // 아래 두개가 같은 생성자 이지만, 인자를 받는지 아닌지의 여부가 있다.
        Supplier<Greeting> newGreeting = Greeting:: new;
        newGreeting.get(); // 여기 까지 해야 만들어 지는 것이다.

        Function<String, Greeting> chanwanGreeting = Greeting::new;
        Greeting chanwan = chanwanGreeting.apply("chanwan");
        System.out.println(chanwan.getName());

        String [] names = {"chanwan", "whiteshop", "toby"};
        Arrays.sort(names, new Comparator<String>() {
            @Override
            public int compare(String s, String t1) {
                return 0;
            }
        });

        Arrays.sort(names, (String o1, String o2) -> {return 0;});

        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
    }
}
