package org.example.io.Thread.TaskDeal;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author Xsj
 * @Descript:
 * @date 2022年07月01日 16:44
 */
public class test {
    public static void main(String[] args) {
        Date date = new Date();
        // 时间格式化 HH:mm:ss
        DateTime parse = DateUtil.parse(date.toString(), "HH:mm:ss");
        System.out.println(parse.toString());
    }

}
