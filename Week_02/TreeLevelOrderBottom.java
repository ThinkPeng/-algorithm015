package com.thinpeng.geek.algorithm.week02;

import com.thinpeng.geek.algorithm.bean.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//107. 二叉树的层次遍历 II
//给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
//
// 例如：
//给定二叉树 [3,9,20,null,null,15,7],
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
//
//
// 返回其自底向上的层次遍历为：
//
// [
//  [15,7],
//  [9,20],
//  [3]
//]
//
// Related Topics 树 广度优先搜索
// 👍 317 👎 0

public class TreeLevelOrderBottom {
    //时间复杂度 O(N), 空间复杂度 O(N)
    //注意要使用 LinkedList 保存结果
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelOrderList = new LinkedList();
        if (root == null) {
            return levelOrderList;
        }

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> curNodeList = new ArrayList();
            int size = queue.size();
            while (size > 0) {
                size--;
                TreeNode curNode = queue.poll();
                curNodeList.add(curNode.val);
                if (curNode.left != null) queue.add(curNode.left);
                if (curNode.right != null) queue.add(curNode.right);
            }
            levelOrderList.add(0, curNodeList);
        }
        return levelOrderList;
    }
}
