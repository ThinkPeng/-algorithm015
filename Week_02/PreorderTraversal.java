package com.thinpeng.geek.algorithm.week02;

//144. 二叉树的前序遍历
//给定一个二叉树，返回它的 前序 遍历。
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
//输出: [1,2,3]
//
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
// Related Topics 栈 树
// 👍 357 👎 0
import com.thinpeng.geek.algorithm.bean.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PreorderTraversal {

    //递归：时间复杂度 O(N), 空间复杂度 O(logN)，最坏是 O(N)
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList();
        helper(root, res);
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        helper(root.left, res);
        helper(root.right, res);
    }
}
