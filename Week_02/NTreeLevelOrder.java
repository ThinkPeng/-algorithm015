package com.thinpeng.geek.algorithm.week02;

import com.thinpeng.geek.algorithm.bean.Node;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

//429. N叉树的层序遍历
//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。
//
// 例如，给定一个 3叉树 :
// 返回其层序遍历:
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 说明:
//
//
// 树的深度不会超过 1000。
// 树的节点总数不会超过 5000。
// Related Topics 树 广度优先搜索
// 👍 107 👎 0
public class NTreeLevelOrder {

    //队列：时间复杂度 O(N), 空间复杂度 O(N)
    public List<List<Integer>> levelOrder(Node root) {
        if(root == null) {
            return new ArrayList();
        }

        List<List<Integer>> res = new ArrayList();
        Deque<Node> queue = new LinkedList();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            List<Integer> curNodeList = new ArrayList();
            int size = queue.size();
            while (size > 0) {
                size--;
                Node curNode = queue.pollFirst();
                curNodeList.add(curNode.val);
                for (Node childNode : curNode.children) {
                    queue.addLast(childNode);
                }
            }
            res.add(curNodeList);
        }
        return res;
    }
}
