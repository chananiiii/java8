package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompleteableFutureApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // completable future 을 쓰면 비동기 작업을 할 수 있다.
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> future = executorService.submit(() -> "hello");
        future.get();

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("chanwan");

        System.out.println(completableFuture.get());

        CompletableFuture <Void> voidCompletableFuture = CompletableFuture.runAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
        });
        voidCompletableFuture.get();

        // 리턴타입이 있는 경우 사용
        // void 로 바꾸면 리턴값이 없이 사용가능하다 ( 콜백에서)
        // 러너블없이 어케 가능해? fork join pool 때문에 가능하다. java 1.7에서 나왔다.
        CompletableFuture <String> test = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return "hello";
        }).thenApply((s) -> {
            // callback 가능했다, 이제는 가능하다.
            // 전에는 불가능했다.
            // s 에 넣어져서 온다. 리턴값이
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(test.get());

        // 두번째 인자로 executorService 를 넣게되면 ( 이건우리가 위에서 생성해준 쓰레드이다.)
        // 그럼 그것을 사용해서 실행시킨다.
        // 출력 보면 currentThread().getName() 의 이름이 달라진것을 볼 수 있다.
        CompletableFuture <String> test2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello" + Thread.currentThread().getName());
            return "hello";
        }, executorService).thenApply((s) -> {
            // callback 가능했다, 이제는 가능하다.
            // 전에는 불가능했다.
            // s 에 넣어져서 온다. 리턴값이
            System.out.println(Thread.currentThread().getName());
            return s.toUpperCase();
        });
        System.out.println(test.get());


        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("Hello " + Thread.currentThread().getName());
            return "hello";
        });

        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("World " + Thread.currentThread().getName());
            return "World";
        });

        // 지금은
        CompletableFuture<String> completableFuture3 = completableFuture1.thenCompose(s -> getWorld(s));
        System.out.println(completableFuture3.get());
    }

    private static CompletableFuture <String> getWorld(String message) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(message + Thread.currentThread().getName());
            return message + " World";
        });
    }
}
