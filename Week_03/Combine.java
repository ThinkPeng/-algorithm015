package com.thinpeng.geek.algorithm.week03;

import java.util.ArrayList;
import java.util.List;

//77. 组合
//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
//
// 示例:
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//]
// Related Topics 回溯算法
// 👍 392 👎 0
public class Combine {

    //递归
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();
    public List<List<Integer>> combine(int n, int k) {
        recur(1, n, k);
        return res;
    }

    private void recur(int cur, int n, int k) {
        //剪枝，后面剩余的数字不够组成 K 个树
        if (temp.size() + (n - cur + 1) < k) {
            return ;
        }

        if (temp.size() == k) {
            res.add(new ArrayList<Integer>(temp));
            return ;
        }

        temp.add(cur);
        recur(cur + 1, n, k);
        temp.remove(temp.size() - 1);
        recur(cur + 1, n, k);
    }
}
