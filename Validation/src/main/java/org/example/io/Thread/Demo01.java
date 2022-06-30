package org.example.io.Thread;

import java.util.Date;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author Xsj
 * @Descript:
 * @date 2022年06月29日 17:21
 */
public class Demo01 {

    private static volatile Integer result = 0;


    private static Integer num = 1000;

    private static Object lock = new Object();

    static Vector<Integer> content = new Vector<>();



    public static void main(String[] args) throws InterruptedException {
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        Semaphore semaphore = new Semaphore(50);
        System.out.println(semaphore.getQueueLength());

        for (long i = 0; i < num; i++) {
            content.add(1);
        }

        long time = new Date().getTime();
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        for (int i = 0; i < content.size(); i++) {
//                try {
//                    Thread.sleep(5);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//              result += content.get(i);
            executorService.execute(new ContentDeal(content.get(i),semaphore));
        }
        while (semaphore.getQueueLength() == 30){
            System.out.println(semaphore.getQueueLength());
            System.out.println("时间：" + (new Date().getTime() - time));
            System.out.println("结果: " + result);
            executorService.shutdown();
        }


    }

    static class ContentDeal implements Runnable {
        private  Integer content;

        private Semaphore semaphore;

        public ContentDeal(Integer content, Semaphore semaphore) {
            this.content = content;
            this.semaphore = semaphore;
        }

        public ContentDeal(Integer content) {
             this.content = content;
        }
        public ContentDeal() {
        }

        @Override
        public void run() {
            if (semaphore.tryAcquire()){
                synchronized (lock){
                    result += content;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

}
