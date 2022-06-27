package org.example.Easy.child;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 无重复子串计算
 * @author: Xsj
 * @create: 2022-06-23 20:39
 **/
public class NoRepetitionWordString {

    public static void main(String[] args) {
        String a = "c";
        System.out.println(NoRepetitionWordString.lengthOfLongestSubstring(a));
    }

    /**
     * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     * 示例：
     *  输入: s = "abcabcbb"
     *  输出: 3
     *  解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {

        char[] chars = s.toCharArray();
        int maxLength = 0;

        Map<String,Integer> map = new HashMap<String, Integer>();
        Map<String,Integer> preMap = new HashMap<String, Integer>();
        if (s.equals(" ")){
            return 1;
        }
        if (chars.length == 0){
            return 0;
        }
        for (int i = 0; i < chars.length; i++) {
            if (!preMap.containsKey(String.valueOf(chars[i]))) {
                preMap.put(String.valueOf(chars[i]),i + 1);
            } else {
                System.out.println(JSON.toJSONString(preMap));
                System.out.println(JSON.toJSONString(map));
                System.out.println("");
                map.put(String.valueOf(chars[i]),i - preMap.get(String.valueOf(chars[i])) + 1);
            }
            preMap.put(String.valueOf(chars[i]),i + 1);
        }


        System.out.println(JSON.toJSONString(preMap));
        System.out.println(JSON.toJSONString(map));

        for (Integer value : map.values()) {
            if (value > maxLength){
                maxLength = value;
            }
        }
        if (map.isEmpty()){
            for (Integer value : preMap.values()) {
                if (value > maxLength){
                    maxLength = value;
                }
            }
        }

        return maxLength;
    }




}
