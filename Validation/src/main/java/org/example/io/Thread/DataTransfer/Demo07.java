package org.example.io.Thread.DataTransfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * @Description:
 * @Author Xsj 2022年07月11日 11:12
 */
public class Demo07 {

    @Autowired
    private Demo06 demo06;

    @Transactional
    public void exChange(){
        System.out.println("事务开始");

        System.out.println("扣金额");

        // 调用远程接口进行卡券兑换

        if (!demo06.change()){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }

    }


}
