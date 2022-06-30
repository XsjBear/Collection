package org.example.io.Thread;


import java.util.Date;
import java.util.Vector;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Xsj
 * @Descript:多线程处理集合数据
 * @date 2022年06月30日 10:09
 */
public class Demo02 {

    static volatile Integer result = 0;

    static Object lock = new Object();



    public static void main(String[] args) throws InterruptedException {

        Integer num = 10000;


        Vector<Integer> content = new Vector<>();

        CountDownLatch count = new CountDownLatch(num);

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
            executorService.execute(new Demo02.ContentDeal(content.get(i),count));
        }
        count.await();
        System.out.println("时间：" + (new Date().getTime() - time));
        System.out.println("结果: " + result);
        executorService.shutdown();
    }

    static class ContentDeal implements Runnable {
        private  Integer content;

        private CountDownLatch count;

        public ContentDeal(Integer content, CountDownLatch count) {
            this.content = content;
            this.count = count;
        }


        public ContentDeal(Integer content) {
            this.content = content;
        }
        public ContentDeal() {
        }

        @Override
        public void run() {
            synchronized (lock){
                // 业务处理
                result += content;
            }
            // 模拟业务处理耗时
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count.countDown();
        }
    }





}
