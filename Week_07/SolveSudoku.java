package com.thinpeng.geek.algorithm.week07;

//37. 解数独
//编写一个程序，通过填充空格来解决数独问题。
//
// 一个数独的解法需遵循如下规则：
//
//
// 数字 1-9 在每一行只能出现一次。
// 数字 1-9 在每一列只能出现一次。
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
//
//
// 空白格用 '.' 表示。
//
//
//
// 一个数独。
//
//
//
// 答案被标成红色。
//
// 提示：
//
//
// 给定的数独序列只包含数字 1-9 和字符 '.' 。
// 你可以假设给定的数独只有唯一解。
// 给定数独永远是 9x9 形式的。
//
// Related Topics 哈希表 回溯算法
// 👍 664 👎 0

public class SolveSudoku {
    //冲突判断数组
    boolean[][] row = new boolean[9][10];
    boolean[][] col = new boolean[9][10];
    boolean[][] block = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        //初始化冲突判断数组
        initCheckArray(board);
        //回溯
        backTrack(board, 0);
    }

    //level表示第i个格子，总共有81个格子，根据level可计算出横坐标与纵坐标。
    private boolean backTrack(char[][] board, int level) {
        if (level == 81)
            return true;

        int i = level / 9;
        int j = level % 9;
        //当前格子已填，进入下一个格子
        if (board[i][j] != '.')
            return backTrack(board, level + 1);

        //遍历 1 - 9 所有可能填到当前格子
        for (int k = 1; k <= 9; k++) {
            if (isValid(i, j, k)) {
                int blockIndex = i / 3 * 3 + j / 3;
                board[i][j] = (char)(k + 48);
                row[i][k] = true;
                col[j][k] = true;
                block[blockIndex][k] = true;
                if (backTrack(board, level + 1)) {
                    return true;
                }
                row[i][k] = false;
                col[j][k] = false;
                block[blockIndex][k] = false;
            }
        }
        return false;
    }

    //判断指定位置，填入 curNum 后是有效
    private boolean isValid(int i, int j, int curNum) {
        int blockIndex = i / 3 * 3 + j / 3;
        if (row[i][curNum] || col[j][curNum] || block[blockIndex][curNum]) {
            return false;
        }
        return true;
    }

    private void initCheckArray(char[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int cur = board[i][j] - '0';
                    int blockIndex = i / 3 * 3 + j / 3;
                    row[i][cur] = true;
                    col[j][cur] = true;
                    block[blockIndex][cur] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] boards = new char[][]{{'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        SolveSudoku s = new SolveSudoku();
        s.solveSudoku(boards);
    }
}
