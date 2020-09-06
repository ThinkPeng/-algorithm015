package com.thinpeng.geek.algorithm.week02;

//94. 二叉树的中序遍历
//给定一个二叉树，返回它的中序 遍历。
//
// 示例:
//
// 输入: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//输出: [1,3,2]
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
// Related Topics 栈 树 哈希表
// 👍 657 👎 0


import com.thinpeng.geek.algorithm.bean.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class InorderTraversal {

    //递归：时间复杂度 O(N), 空间复杂度 O(logN)，最坏是 O(N)
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        helper(root, res);
        return res;
    }
    private void helper(TreeNode root, List<Integer> res) {
        if (root != null) {
            if (root.left != null) helper(root.left, res);
            res.add(root.val);
            if (root.right != null) helper(root.right, res);
        }
    }

    //基于栈的遍历 时间复杂度 O(n), 空间复杂度 O(n)
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList();
        Deque<TreeNode> stack = new ArrayDeque();

        TreeNode curr = root;
        while(curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.addLast(curr);
                curr = curr.left;
            }
            curr = stack.pollLast();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }


}
