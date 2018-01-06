package neo.java.util.concurrent;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;

public class CyclicBarrierTest {
    public static void main(String[] args) {
       final Random random = new Random();
        final CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            public void run() {
                System.out.println("sleep finished");
            }
        });
        for(int i=0;i<5;i++) {
            new Thread(){
                @Override public void run() {
                    int slp = random.nextInt(5000);
                    System.out.println("thread sleep " + slp + " ms");
                    try {
                        sleep(slp);
                        barrier.await();
                    } catch (InterruptedException e) {
                    } catch (BrokenBarrierException e) {
                    }

                }
            }.start();
        }
        try {
            Thread.currentThread().sleep(10000);
            System.out.println("finished");
        } catch (InterruptedException e) {
        }
    }
}