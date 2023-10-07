package step.learning.async;

import java.util.Locale;
import java.util.concurrent.CountDownLatch;

public class AsyncDemo {

    private double sum;

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
    public void run() {
        System.out.println( "Async demo" ) ;
        int months = 12;
        Thread[] threads = new Thread[months];
        sum = 100;
        activeThreadsCount = months;
        //multiThreadDemo();
        for (int i = 0; i < 12; i++) {
            threads[i] = new Thread(new MonthRate(i + 1));
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