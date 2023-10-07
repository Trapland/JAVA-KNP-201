package step.learning.async;

import java.util.Locale;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class TaskDemo {

    private int activeThreadsCount;
    private double sum;

    private final Object sumLocker = new Object();

    private final Object atcLocker = new Object();
    private final ExecutorService threadPool = Executors.newFixedThreadPool(6) ;

    class MonthRate implements Runnable{
        private final int month;
        public MonthRate(int month) {
            this.month = month;
        }


        @Override
        public void run() {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException ignore) {}
            double p = 0.1;
            double localSum;
            synchronized (sumLocker) {
                localSum = sum = sum * (1.0 + p);

            }
            System.out.printf(Locale.US,"month: %02d, percent: %.2f, sum: %.2f%n", month, p, localSum);
            synchronized (atcLocker){
                activeThreadsCount--;
                if(activeThreadsCount == 0){
                    System.out.printf(Locale.US,"---------%ntotal: %.2f%n",sum);
                }
            }

        }


    }
    public void run(){
//        long t1 = System.nanoTime();
//        Future<String> task1 = threadPool.submit(new Callable<String>() {
//
//            @Override
//            public String call() throws Exception {
//                System.out.println( "Task 1 start" ) ;
//                Thread.sleep( 1000 ) ;
//                return "Task 1 finish";
//            }
//        });
//
//        for (int i = 0; i < 10; i++) {
//            printNumber(10 + i);
//        }
//
//        Future<String> supplyTask = CompletableFuture
//                .supplyAsync(supplier)
//                .thenApply(continuation)
//                .thenApply(continuation2);
//        Future<?> task2 = CompletableFuture.supplyAsync(supplier2)
//                .thenApply(continuation2)
//                .thenAccept(acceptor);
//
//        try {
//            String res = task1.get();
//            System.out.println(res);
//            System.out.println(supplyTask.get());
//        } catch (ExecutionException | InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            threadPool.shutdown();
//            threadPool.awaitTermination(1,TimeUnit.MINUTES);
//            threadPool.shutdownNow();
//        }
//        catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        long t2 = System.nanoTime();
//
//        System.out.println("Main finish " + (t2-t1) / 1e9);

        int months = 12;
        Thread[] threads = new Thread[months];
        sum = 100;
        activeThreadsCount = 6;
        for (int i = 0; i < activeThreadsCount; i++) {
            inflation(i+1);
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = activeThreadsCount; i < months; i++) {
            inflation(i+1);
        }
        threadPool.shutdown();
    }

    private final Function<String, String> continuation = str -> str + " continued";

    private final Supplier<String> supplier =() -> {
        System.out.println("Task supply start");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Task supply finish";
    };

    private final Supplier<String> supplier2 = new Supplier<String>() {
        @Override
        public String get() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Supplier 2";
        }
    };

    private final Function<String,String> continuation2 = new Function<String, String>() {
        @Override
        public String apply(String s) {
            return s + " continuation2";
        }
    };

    private final Consumer<String> acceptor = new Consumer<String>() {
        @Override
        public void accept(String s) {
            System.out.println(s + " accepted");
        }
    };
    private Future<?> printNumber( int num ) {
        return threadPool.submit(
                () -> {
                    try {
                        System.out.println("Task starts for number" + num);
                        Thread.sleep(1000);
                        System.out.println("Number: " + num);
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
        );
    }


    private Future<?> inflation( int month ) {
        return threadPool.submit(
                () -> {
                    try {
                        System.out.println("Task starts for month" + month);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                    synchronized (sumLocker) {
                        sum *= (1.0 + 0.1);
                        System.out.println("Current sum: " + sum);
                    }
                }
        );
    }
}