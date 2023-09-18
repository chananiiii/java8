package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class CallableAndFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Callable <String> hello = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000L);
                return "Hello";
            }
        };

        Future<String> helloFuture = executorService.submit(hello);
        System.out.println(helloFuture.isDone());
        System.out.println("Started!");

        // 현재 진행중인 작업을 인터럽트하면서 종료
        // false 면 기다린다.
        helloFuture.cancel(true);

        // 캔슬을 하면 get 으로 값을 가져올 수 없다.
        // 가져오려고 하면 에러가 난다.
        // helloFuture.get(); // blocking call

        System.out.println(helloFuture.isDone());
        System.out.println("End!!");

        Callable <String> helloJava = () -> {
            Thread.sleep(3000L);
            return "Java";
        };

        Callable <String> helloPython = () -> {
            Thread.sleep(2000L);
            return "Python";
        };

        Callable <String> helloC = () -> {
            Thread.sleep(1000L);
            return "C";
        };

        // 이것을 한번에 보낼 수 있다.
        List<Future<String>> futures = executorService.invokeAll(Arrays.asList(helloJava, helloPython, helloC));
        for(Future<String> f : futures) {
            System.out.println(f.get());
        }

        // invokeAny 응답이 하나 오면 끝내기
        // 그래서 반환형도 하나이다.
        // 가장 짧은게 실행된다.
        String s = executorService.invokeAny(Arrays.asList(helloJava, helloPython, helloC));
        System.out.println(s);


        executorService.shutdown();

    }
}
