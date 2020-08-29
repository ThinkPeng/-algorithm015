package com.thinpeng.geek.algorithm.week01;

//21. 合并两个有序链表
//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
// 示例：
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
//
// Related Topics 链表
// 👍 1237 👎 0

import com.thinpeng.geek.algorithm.bean.ListNode;

public class MergeTwoLists {
    //迭代解法（时间复杂度 O(n + m), 空间复杂度 O(1)）
    public ListNode mergeTwoListsByIteration(ListNode l1, ListNode l2) {
        ListNode newHead = new ListNode(-1);
        ListNode cur = newHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = (l1 == null ? l2 : l1);
        return newHead.next;
    }

    //递归解法(时间复杂度 O(n + m)，空间复杂度 O(n + m)，递归调用时需要消耗栈空间，栈空间的大小取决于递归调用的深度)
    public ListNode mergeTwoListsByRecursion(ListNode l1, ListNode l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        if(l1.val < l2.val) {
            l1.next = mergeTwoListsByRecursion(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsByRecursion(l1, l2.next);
            return l2;
        }
    }
}
