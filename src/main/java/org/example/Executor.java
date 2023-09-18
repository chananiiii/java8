package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {
    public static void main(String[] args) {
        // 싱글스레드로 태스크 하나를 실행
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread " + Thread.currentThread().getName());
            }
        });
        // 익스큐터는 명시적으로 셧다운을 해야한다., 익스큐터는 graceful ? shutdown 이다.
        executorService.shutdown();

        // 스레드는 2개지만 5개 실행이 된다.
        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        executorService1.submit(getRunable("1"));
        executorService1.submit(getRunable("2"));
        executorService1.submit(getRunable("3"));
        executorService1.submit(getRunable("4"));
        executorService1.submit(getRunable("5"));
    }

    private static Runnable getRunable(String message) {
        return () -> System.out.println(message + " " + Thread.currentThread().getName());
    }
}
