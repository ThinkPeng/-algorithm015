package com.thinpeng.geek.algorithm.week09;

//72. 编辑距离
//给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数 。
//
// 你可以对一个单词进行如下三种操作：
//
//
// 插入一个字符
// 删除一个字符
// 替换一个字符
//
//
//
//
// 示例 1：
//
// 输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
//
//
// 示例 2：
//
// 输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
//
// Related Topics 字符串 动态规划
// 👍 1221 👎 0

public class MinDistance {
    //DP 解法，时间复杂度 O(MN)，空间复杂度 O(MN)
    //状态：dp[i][j] : 表示 word1 从 0...i, word2 从 0...j 的最小编辑距离
    //假设 word1[i] = ...x;  word2[j] = ...y;
    //当 x = y 时，不需要任何操作，则 dp[i][j] = dp[i - 1][j - 1];
    //当 x != y 时：
    //  可以进行替换，dp[i - 1][j - 1] + 1
    //  也可删除或插入一个字符，dp[i - 1][j] 或着 dp[i][j - 1];
    //取三种操作的最小值即为 dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1。
    public int minDistance(String word1, String word2) {
        int length1 = word1.length();
        int length2 = word2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        for (int i = 0; i <= length1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= length2; j++) {
            dp[0][j] = j;
        }

        char[] c1 = word1.toCharArray();
        char[] c2 = word2.toCharArray();
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                if (c1[i - 1] == c2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[length1][length2];
    }
}
