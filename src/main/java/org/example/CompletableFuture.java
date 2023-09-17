package org.example;

public class CompletableFuture {
    public static void main(String[] args) throws InterruptedException {
        // 쓰레드 만드는 방법 상속 받아서 생성
        MyThread myThread = new MyThread();
        myThread.start();

        System.out.println("Hello! " + Thread.currentThread().getName());

        // 두번째 방법
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread : " + Thread.currentThread().getName());
            }
        });
        thread.start();

        // 람다로 바꿔 쓸 수 있다 자바 8에서
        Thread thread2 = new Thread(() -> {
            System.out.println("Thread : " + Thread.currentThread().getName());
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {
           while(true) {
               System.out.println("Thread : " + Thread.currentThread().getName());
               try {
                   Thread.sleep(1000L);
               } catch(InterruptedException e) { // 누군가 깨우면 종료해라
                   System.out.println("exti!");

                   // 인터럽트를 받았음에도 return 하지 않으면 종료가 되지는 않는다.
                   return ;
               }
           }

        });
        thread3.start();

        Thread.sleep(3000L);
        // 3번 인터럽트 실행
        // 인터럽트를 보내는것이지 종료를 하라는 말이아니다.
        thread3.interrupt();


        Thread thread4 = new Thread(() -> {
            while(true) {
                System.out.println("Thread : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000L);
                } catch(InterruptedException e) { // 누군가 깨우면 종료해라
                    throw new IllegalStateException(e);
                }
            }
        });
        thread4.start();

        // join은 해당 스레드를 기다렸다가. 진행하는 의미이다.
        // 쓰레드가 계속 대기를 하는데, 그 도중에 누군가가 메인스레드를 인터럽트 한다.
        // 그럼 여기서 또 인터럽트 익스셉션이 발생한다.
        thread4.join();
        System.out.println(thread + " is finished");
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Thread : " + Thread.currentThread().getName());
        }
    }
}
