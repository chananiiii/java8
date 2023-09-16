package org.example;

import java.time.Duration;
import java.util.*;

public class Option {
    public static void main(String[] args) {

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass springboot = new OnlineClass(1, "spring boot", true);


        // 매번 널체크를 할 수 없다.
        // null 리턴하는 것 자체가 문제다.

        // set 할때 optional 을 사용하게 되면, progress에서 받는 것에 대한
        // null 체크를 더 해야한다. 그래서 받는 파라미터에서 Optional 을 사용해도 의미가 없다.
        springboot.setProgress(null);

        // 안에서 boxing unboxing 이 진행된다.
        // 성능에 좋지 않다.
        Optional.of(10);

        // 이것을 권장한다.
        OptionalInt.of(10);

        Optional<OnlineClass> onlineClass = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        boolean present = onlineClass.isPresent();
        System.out.println(present);

        boolean present2 = onlineClass.isEmpty();
        System.out.println(present2);

        // get 으로 값을 꺼낼 수 있다.
        OnlineClass onlineClass1 = onlineClass.get();
        System.out.println(onlineClass1.getTitle());

        Optional<OnlineClass> emptyClass = springClasses.stream()
                .filter(oc -> oc.getTitle().startsWith("spring"))
                .findFirst();

        // emptyClass 에 aaa로 시작하는 클래스가 없어서. get으로 꺼낼때 exception 발생한다.
        // 확인을 하고 꺼내야한다.
        // OnlineClass emptyClass1 = emptyClass.get();
        // System.out.println(emptyClass1.getTitle());

        // 존재하면, 다음 람다 실행 하는 그렇게 null 체크할수도 있다.
        emptyClass.ifPresent(oc -> {
            System.out.println(oc.getTitle());
        });

        // 난 무조건 받아야한다 할때..
        // 없다면.. 안에 실행
        // aaa는 없으니까 createNewClass 로 파라미터로 넘겨준 애가 실행이된다.
        // 근데 있어도 실행이 된다. orElse 는 무조건 실행되는 것이다.
        OnlineClass example = emptyClass.orElse(createNewClass());

        // 있을땐 호출 안하고, 없는 경우에는 호출해서 생성한다.
        OnlineClass example2 = emptyClass.orElseGet(() -> createNewClass());


        // 비어있는 옵셔널이 나올 것이다.
        Optional <OnlineClass> optionalType = emptyClass
                .filter(oc -> !oc.isClosed());

        System.out.println(optionalType.isEmpty());

        Optional <Integer> integer = onlineClass.map(oc -> oc.getId());
        System.out.println(integer.isPresent());

        // flatMap을 사용하면 옵셔널 안에 옵셔널 사용하는 경우 안에 내용을 한번 까준다.
        Optional <Progress> progress = emptyClass.flatMap(OnlineClass::getProgress);

    }

    private static OnlineClass createNewClass() {
        System.out.println("creating new online class");
        return new OnlineClass(10 , "New class", false);
    }
}
