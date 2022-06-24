package org.Easy.twoSum;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 两数之和
 * @author: Xsj
 * @create: 2022-06-19 08:37
 **/
public class TwoSun {

    /**
     * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
     * 示例：
     *      输入：nums = [2,7,11,15], target = 9
     *      输出：[0,1]
     *      解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
     * 解题收获：
     *      1、对于多次出现的变量，用一个临时变量来进行接受以避免多次取值造成寻址操作浪费时间
     *      2、map中判断是否存在key用包含关系做判断
     *      3、对于只需要在结尾返回的数据不需要在开始就进行初始化用变量承接，避免浪费栈空间
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        // 结果集
        Map<Integer,Integer> map = new HashMap<>(nums.length - 1);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])){
                return new int[]{i,map.get(target - nums[i])};
            }
            if (!map.containsKey(nums[i])){
                map.put(nums[i],i);
            }
        }
        throw new RuntimeException("无结果");
    }


}
