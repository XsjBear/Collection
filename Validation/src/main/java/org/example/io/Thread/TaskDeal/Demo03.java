package org.example.io.Thread.TaskDeal;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.*;

/**
 * @author Xsj
 * @Descript:
 * @date 2022年06月30日 10:38
 */
public class Demo03 {


    /**
     * 结果
     */
    static volatile Integer result = 0;

    /**
     * 锁
     */
    static Object lock = new Object();



    public static void main(String[] args) throws InterruptedException {
        // 操作数据量
        Integer num = 10000;
        // 源数据
        Vector<AddContent> content = new Vector<>();
        // 结果集
        List<AddContent> resultList = new ArrayList<>();

        // 添加源数据
        for (Integer i = 0; i < num; i++) {
            content.add(AddContent.builder().content(i).build());
        }

        // 计时
        long time = new Date().getTime();
        // 线程池
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < content.size(); i++) {
            // 提交任务
            Future submit = executorService.submit(new ContentDeal(content.get(i)));
            try {
                // 获取结果
                resultList.add((AddContent) submit.get());
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        // 结果判断，判断是否都处理完成
        for (AddContent addContent : resultList) {
            if (addContent.getResult() == null){
                System.out.println(JSON.toJSONString(addContent));
            }
        }
        System.out.println("时间：" + (new Date().getTime() - time));
        System.out.println("结果: " + result);
        executorService.shutdown();
    }

    static class ContentDeal implements Callable {
        private  AddContent addContent;

        public ContentDeal(AddContent addContent) {
            this.addContent = addContent;
        }
        public ContentDeal() {
        }

        @Override
        public Object call() throws Exception {
            synchronized (lock){
                // 业务处理
                addContent.setResult(addContent.getContent());
                Thread.sleep(5);
            }
            return addContent;
        }
    }



}
