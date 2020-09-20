package com.thinpeng.geek.algorithm.week04;

import com.thinpeng.geek.algorithm.bean.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//404. 左叶子之和
//计算给定二叉树的所有左叶子之和。
//
// 示例：
//
//
//    3
//   / \
//  9  20
//    /  \
//   15   7
//
//在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
//
//
// Related Topics 树
// 👍 220 👎 0
public class SumOfLeftLeaves {

    //DFS，时间复杂度 O(n), 空间复杂度 O(n)
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int ans = 0;
        if (root.left != null) {
            ans += isLeafNode(root.left) ? root.left.val : sumOfLeftLeaves(root.left);
        }
        if (root.right != null) {
            ans += sumOfLeftLeaves(root.right);
        }

        return ans;
    }

    private boolean isLeafNode(TreeNode node) {
        return node.left == null && node.right == null;
    }

    //BFS,时间复杂度 O(n), 空间复杂度 O(n)
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int sum = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                if (isLeafNode(cur.left)) {
                    sum += cur.left.val;
                } else {
                    queue.offer(cur.left);
                }
            }

            if (cur.right != null && !isLeafNode(cur.right)) {
                queue.offer(cur.right);
            }
        }
        return sum;
    }
}
