package com.thinpeng.geek.algorithm.week01;

//412. Fizz Buzz
//写一个程序，输出从 1 到 n 数字的字符串表示。
//
// 1. 如果 n 是3的倍数，输出“Fizz”；
//
// 2. 如果 n 是5的倍数，输出“Buzz”；
//
// 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
//
// 示例：
//
// n = 15,
//
//返回:
//[
//    "1",
//    "2",
//    "Fizz",
//    "4",
//    "Buzz",
//    "Fizz",
//    "7",
//    "8",
//    "Fizz",
//    "Buzz",
//    "11",
//    "Fizz",
//    "13",
//    "14",
//    "FizzBuzz"
//]
//
// 👍 70 👎 0

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FizzBuzz {

    //解法1：字符串拼接（时间复杂度 O(n), 空间复杂度 O(1)）
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            boolean divBy3 = (i % 3 == 0);
            boolean divBy5 = (i % 5 == 0);
            String str = "";
            if (divBy3) {
                str += "Fizz";
            }
            if (divBy5) {
                str += "Buzz";
            }
            if(str.length() == 0) {
                str += i;
            }
            result.add(str);
        }
        return result;
    }

    //解法2：字符串拼接 + Map 自定义规则（时间复杂度 O(n), 空间复杂度 O(1)）
    public List<String> fizzBuzzWithMap(int n) {
        Map<Integer, String> ruleMap = new HashMap<>();
        ruleMap.put(3, "Fizz");
        ruleMap.put(5, "Buzz");
        List<String> result = new ArrayList<String>();
        for (int i = 1; i <= n; i++) {
            String str = "";
            for(Integer divideNum : ruleMap.keySet()) {
                if(i % divideNum == 0) {
                    str += ruleMap.get(divideNum);
                }
            }

            if (str.length() == 0) {
                str = String.valueOf(i);
            }
            result.add(str);
        }
        return result;
    }
}
