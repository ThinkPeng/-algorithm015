package com.thinpeng.geek.algorithm.week04;

import java.util.*;

//51. N 皇后
//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
//
//
// 上图为 8 皇后问题的一种解法。
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
//
//
// 示例：
//
// 输入：4
//输出：[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
//
//
//
//
// 提示：
//
//
// 皇后彼此不能相互攻击，也就是说：任何两个皇后都不能处于同一条横行、纵行或斜线上。
//
// Related Topics 回溯算法
// 👍 635 👎 0
public class SolveNQueens {

    //回溯法，时间复杂度 O(N!), 空间复杂度 O(N)
    private List<List<String>> res = new ArrayList<>();
    //用于冲突判断，也可改用 boolean 数组。参考 TotalNQueens.java
    private Set<Integer> colSet = new HashSet<>();
    private Set<Integer> pieSet = new HashSet<>();
    private Set<Integer> naSet = new HashSet<>();
    public List<List<String>> solveNQueens(int n) {
        dfs(0, n, new ArrayList<Integer>());
        return res;
    }

    private void dfs(int row, int n, List<Integer> tempRes) {
        if (row == n) {
            res.add(convert2Board(tempRes));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (colSet.contains(i) || pieSet.contains(row + i) || naSet.contains(row - i)) {
                continue;
            }

            colSet.add(i);
            pieSet.add(row + i);
            naSet.add(row - i);
            tempRes.add(i);
            //recur
            dfs(row + 1, n, tempRes);
            //reverse
            colSet.remove(i);
            pieSet.remove(row + i);
            naSet.remove(row - i);
            tempRes.remove(tempRes.size() - 1);
        }
    }

    private List<String> convert2Board(List<Integer> path) {
        List<String> board = new ArrayList<>();
        for (Integer col : path) {
            char[] row = new char[path.size()];
            Arrays.fill(row, '.');
            row[col] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
