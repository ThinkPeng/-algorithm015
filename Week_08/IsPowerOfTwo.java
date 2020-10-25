package com.thinpeng.geek.algorithm.week08;

//231. 2的幂
//给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
//
// 示例 1:
//
// 输入: 1
//输出: true
//解释: 20 = 1
//
// 示例 2:
//
// 输入: 16
//输出: true
//解释: 24 = 16
//
// 示例 3:
//
// 输入: 218
//输出: false
// Related Topics 位运算 数学
// 👍 255 👎 0

public class IsPowerOfTwo {
    //只需判断 n 的二进制位是否有且仅有1个1
    //相关位运算 : x = x & (x - 1)，清零最低一位的1
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0 ? true : false;
    }
}
