package com.thinpeng.geek.algorithm.week01;

//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
//
// 示例:
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
//
// Related Topics 链表
// 👍 603 👎 0

import com.thinpeng.geek.algorithm.bean.ListNode;

public class SwapListNodePair {

    //递归解法（时间复杂度 O(n), 空间复杂度 O(n)）
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        head.next = swapPairs(next.next);
        next.next = head;
        //每层递归中，第二个节点将变为上一层的下一个节点
        return next;
    }
}
