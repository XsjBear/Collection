package org.example.io.Thread.DataTransfer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description:
 * @Author Xsj 2022年07月11日 10:34
 */
public class Demo05 implements Runnable{

    private int num;

    private CyclicBarrier countDownLatch;

    private Object lock = new Object();

    public Demo05(int num, CyclicBarrier countDownLatch) {
        this.num = num;
        this.countDownLatch = countDownLatch;
    }

    public void testOut(Integer a){
        System.out.println(a);
    }

    @Override
    public void run() {
        System.out.println("test" + num);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        synchronized (lock){
            testOut(num);
        }

    }
}
