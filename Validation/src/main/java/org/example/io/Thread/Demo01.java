package org.example.io.Thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * @author Xsj
 * @Descript:
 * @date 2022年06月29日 17:21
 */
public class Demo01 {

    private static volatile Integer result = 0;


    private static Integer num = 100000000;

    private static Object lock = new Object();

    static Vector<Integer> content = new Vector<>();

    static CountDownLatch count = new CountDownLatch(num);



    public static void main(String[] args) throws InterruptedException {
        for (long i = 0; i < num; i++) {
            content.add(1);
        }

        long time = new Date().getTime();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < content.size(); i++) {
                        result += content.get(i);
//            executorService.execute(new ContentDeal(content.get(i)));
        }
        // 等待线程都执行完,避免因有线程还未执行完就输出结果的情况
//        count.await();
        System.out.println("时间：" + (new Date().getTime() - time));
        System.out.println("结果: " + result);
        return;
    }

    static class ContentDeal implements Runnable {
        private  Integer content;
        public ContentDeal(Integer content) {
             this.content = content;
        }
        public ContentDeal() {
        }

        @Override
        public void run() {
            synchronized (lock){
                result += content;
                count.countDown();
            }
        }
    }

}
