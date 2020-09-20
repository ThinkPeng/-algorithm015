package com.thinpeng.geek.algorithm.week04;

import java.util.LinkedList;
import java.util.Queue;

//200. 岛屿数量
//给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
//
// 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
//
// 此外，你可以假设该网格的四条边均被水包围。
//
//
//
// 示例 1:
//
// 输入:
//[
//['1','1','1','1','0'],
//['1','1','0','1','0'],
//['1','1','0','0','0'],
//['0','0','0','0','0']
//]
//输出: 1
//
//
// 示例 2:
//
// 输入:
//[
//['1','1','0','0','0'],
//['1','1','0','0','0'],
//['0','0','1','0','0'],
//['0','0','0','1','1']
//]
//输出: 3
//解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
//
// Related Topics 深度优先搜索 广度优先搜索 并查集
// 👍 765 👎 0
public class NumIsLands {

    int rows = 0;
    int columns = 0;
    //解法1，DFS，时间复杂度 O(MN),空间复杂度 O(MN), M、N分别为行数和列数
    public int numIslands(char[][] grid) {
        rows = grid.length;
        if (rows == 0) return 0;
        columns = grid[0].length;

        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(grid[i][j] == '1') {
                    res++;
                    dfsMask(i, j, grid);
                }
            }
        }
        return res;
    }

    private void dfsMask(int i, int j, char[][] grid) {
        if (i < 0 || j < 0 ||i >= rows || j >= columns || grid[i][j] == '0')
            return;
        grid[i][j] = '0';
        dfsMask(i + 1, j, grid);
        dfsMask(i - 1, j, grid);
        dfsMask(i, j + 1, grid);
        dfsMask(i, j - 1, grid);
    }

    //解法2，BFS，时间复杂度 O(MN),空间复杂度 O(min(M,N)), M、N分别为行数和列数
    public int numIslands2(char[][] grid) {
        rows = grid.length;
        if (rows == 0) return 0;
        columns = grid[0].length;

        int res = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if(grid[i][j] == '1') {
                    res++;
                    bfsMask(i, j, grid);
                }
            }
        }
        return res;
    }

    private void bfsMask(int i, int j, char[][] grid) {
        grid[i][j] = '0';
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        while (!queue.isEmpty()) {
            int[] position = queue.poll();
            int row = position[0];
            int col = position[1];
            if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                grid[row - 1][col] = '0';
                queue.offer(new int[]{row - 1, col});
            }
            if (row + 1 < rows && grid[row + 1][col] == '1') {
                grid[row + 1][col] = '0';
                queue.offer(new int[]{row + 1, col});
            }
            if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                grid[row][col - 1] = '0';
                queue.offer(new int[]{row, col - 1});
            }
            if (col + 1 < columns && grid[row][col + 1] == '1') {
                grid[row][col + 1] = '0';
                queue.offer(new int[]{row, col + 1});
            }
        }
    }
}
