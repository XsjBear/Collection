package org.example.io.Thread;

import lombok.*;

/**
 * @author Xsj
 * @Descript:操作实体
 * @date 2022年06月30日 10:35
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddContent {

    /**
     * 操作前数据
     */
    private int content;


    /**
     * 操作后数据
     */
    private Integer result;

}
