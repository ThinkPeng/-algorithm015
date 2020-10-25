package com.thinpeng.geek.algorithm.week08;

//338. 比特位计数
//给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
//
// 示例 1:
//
// 输入: 2
//输出: [0,1,1]
//
// 示例 2:
//
// 输入: 5
//输出: [0,1,1,2,1,2]
//
// 进阶:
//
//
// 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
// 要求算法的空间复杂度为O(n)。
// 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
//
// Related Topics 位运算 动态规划
// 👍 432 👎 0

public class CountBits {
    // 0001 1
    // 0010 2
    // 0011 3
    // 0100 4
    // 0101 5
    // 0110 6
    // 0111 7
    // 1000 8

    // 动态规划 + 最低有效位
    // 当 i 位奇数时，其二进制位 1 的个数等于 i / 2 的个数 + 1
    // 当 i 位偶数时，其二进制位 1 的个数等于 i / 2
    public int[] countBits3(int num) {
        int[] dp = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }


    //动态规划 + 奇偶判断
    // 通过观察发现，当 i 为奇数时，其二进制位 1 的个数比 i - 1 多 1 个。
    // 第 i 为偶数时，其二进制位 1 的个数等于 i / 2 d的个数。
    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        dp[0] = 0;
        if (num == 0) return dp;
        dp[1] = 1;
        for (int i = 2; i <= num; i++) {
            if ((i & 1) == 1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = dp[i / 2];
            }
        }
        return dp;
    }

    // 动态规划 + 最高有效位
    // 0 ~ N - 1 的二进制 1 个数 = N ~ 2N - 1 加 1
    public int[] countBits2(int num) {
        int[] dp = new int[num + 1];
        int i = 0, b = 1;
        while (b <= num) {
            while (i < b && (i + b) <= num) {
                dp[i + b] = dp[i++] + 1;
            }
            i = 0;
            b <<= 1;
        }
        return dp;
    }
}
