package com.thinpeng.geek.algorithm.week02;

//104. 二叉树的最大深度
//给定一个二叉树，找出其最大深度。
//
// 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
//
// 说明: 叶子节点是指没有子节点的节点。
//
// 示例：
//给定二叉树 [3,9,20,null,null,15,7]，
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
// 返回它的最大深度 3 。
// Related Topics 树 深度优先搜索
// 👍 688 👎 0

import com.thinpeng.geek.algorithm.bean.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaxTreeDepth {

    //递归法 时间复杂度 O(n), 空间复杂度 O(height)
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxDepth = Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        return maxDepth;
    }

    //广度优先搜索 时间复杂度 O(n), 最坏情况空间复杂度 O(n)
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Deque<TreeNode> queue = new ArrayDeque();
        queue.addLast(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int curLevelNodes = queue.size();
            while(curLevelNodes > 0) {
                curLevelNodes--;
                TreeNode node = queue.pollFirst();
                if(node.left != null) queue.addLast(node.left);
                if(node.right != null) queue.addLast(node.right);
            }
            res++;
        }
        return res;
    }
}
