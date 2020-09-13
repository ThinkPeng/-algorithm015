package com.thinpeng.geek.algorithm.week03;

//236. 二叉树的最近公共祖先
//剑指 Offer 68 - II. 二叉树的最近公共祖先
//给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
//
// 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（
//一个节点也可以是它自己的祖先）。”
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
// 示例 1:
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
//
//
// 示例 2:
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
//
//
// 说明:
//
//
// 所有节点的值都是唯一的。
// p、q 为不同节点且均存在于给定的二叉树中。
//
// Related Topics 树
// 👍 735 👎 0

import com.thinpeng.geek.algorithm.bean.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LowestCommonAncestor {

    //保存结果
    private TreeNode res;

    //解法1：递归解法，时间复杂度 O(n), 空间复杂度 O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p.val, q.val);
        return res;
    }

    private boolean dfs (TreeNode root, int p, int q) {
        if (root == null) {
            return false;
        }

        boolean lson = dfs(root.left, p, q);
        boolean rson = dfs(root.right, p, q);
        if (lson && rson || ((root.val == p || root.val == q ) && (lson || rson))) {
            res = root;
            return false;
        }

        return lson || rson || root.val == p || root.val == q;
    }

    Map<Integer, TreeNode> parentMap = new HashMap<>();
    Set<Integer> visitedSet = new HashSet<>();
    //解法2：存储根节点，时间复杂度 O(n), 空间复杂度 O(n)
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        //先将所有结点与其父节点的映射进行保存
        dfs2(root);
        while (p != null) {
            visitedSet.add(p.val);
            p = parentMap.get(p.val);
        }

        while (q != null) {
            if (visitedSet.contains(q.val)) {
                return q;
            }
            q = parentMap.get(q.val);
        }
        return root;
    }

    private void dfs2(TreeNode root) {
        if (root.left != null) {
            parentMap.put(root.left.val, root);
            dfs2(root.left);
        }
        if (root.right != null) {
            parentMap.put(root.right.val, root);
            dfs2(root.right);
        }
    }


}
