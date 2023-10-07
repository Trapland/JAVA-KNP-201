package step.learning.async;

import java.util.Locale;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class AsyncDemo {

    private double sum;
    private StringBuilder str;

    private final Object sumLocker = new Object();

    private final Object atcLocker = new Object();
    private int activeThreadsCount;

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
    class MyThreads implements Runnable{
        private final int delay;
        private int num;
        public MyThreads(int delay, int num) {
            this.delay = delay;
            this.num   = num;
        }


        @Override
        public void run() {
            try {

                if(num == 3)
                    Thread.sleep(500);
                System.out.println(num + "Thread start");
                Thread.sleep(delay);

            } catch (InterruptedException ignore) {
            }
            synchronized (atcLocker) {
                activeThreadsCount--;
                if (activeThreadsCount == 0) {
                    System.out.println("Thread final");
                }
            }
            System.out.println(num + "Thread finish");
        }

    }

    Random r = new Random();

    class ThreadsStrBuilder implements Runnable{
        private char value;

        @Override
        public void run() {
            try {
                Thread.sleep(1000);

            } catch (InterruptedException ignore) {
            }
            synchronized (sumLocker) {
                char c = ((char) (r.nextInt(10) + 48));

                while (str.toString().contains(String.valueOf(c)))
                {
                    c = ((char)(r.nextInt(10) + 48));
                }
                str.append(c);
                value = c;
            }
            System.out.println(" Thread with value " + value + " finish");

            synchronized (atcLocker) {
                activeThreadsCount--;
                if (activeThreadsCount == 0) {
                    System.out.println("---------------------------------");
                    System.out.println("Result: " + str);
                    System.out.println("---------------------------------");
                }
            }
        }

    }
    public void run() {
        System.out.println( "Async demo" ) ;
//        int months = 12;
//        Thread[] threads = new Thread[months];
//        sum = 100;
//        activeThreadsCount = months;
//        //multiThreadDemo();
//        for (int i = 0; i < 12; i++) {
//            threads[i] = new Thread(new MyThreads(i + 1));
//            threads[i].start();
//        }
//        int thr = 3;
//        Thread[] myThreads = new Thread[thr];
//        activeThreadsCount = 3;
//        myThreads[0] = new Thread(new MyThreads(3000,1));
//        myThreads[1] = new Thread(new MyThreads(0,2));
//        myThreads[2] = new Thread(new MyThreads(1500,3));
//        myThreads[0].start();
//        myThreads[1].start();
//        myThreads[2].start();
        int values = 10;
        Thread[] threads = new Thread[values];
        str = new StringBuilder();
        activeThreadsCount = values;
        for (int i = 0; i < values; i++) {
            threads[i] = new Thread(new ThreadsStrBuilder());
            threads[i].start();
        }
    }



    private void multiThreadDemo(){
        Thread thread = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            System.out.println("Thread starts");
                            Thread.sleep(2000);
                            System.out.println("Thread finishes");
                        }
                        catch (InterruptedException ex){
                            System.err.println("Sleeping broken" + ex.getMessage());
                        }
                    }
                }
        );
        try {
            thread.join();
        }
        catch (InterruptedException ex){
            System.err.println("Thread joining interrupted" + ex.getMessage());
        }
        //      thread.start();
        System.out.println("multiThreadDemo() finishes");
    }

}