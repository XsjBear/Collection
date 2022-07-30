package org.example.io.Thread.DataTransfer;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description:多线程模拟数据迁移
 * @Author Xsj 2022年07月09日 17:31
 */
public class Demo04 {

    public static void main(String[] args) {
        int num = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        for (int i = 0; i < num; i++) {
            executorService.execute(new Demo05(i,cyclicBarrier));
        }
    }

}


