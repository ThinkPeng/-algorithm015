package com.thinpeng.geek.algorithm.week02;

//剑指 Offer 40. 最小的k个数
//输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
//
//
//
// 示例 1：
//
// 输入：arr = [3,2,1], k = 2
//输出：[1,2] 或者 [2,1]
//
//
// 示例 2：
//
// 输入：arr = [0,1,2,1], k = 1
//输出：[0]
//
//
//
// 限制：
//
//
// 0 <= k <= arr.length <= 10000
// 0 <= arr[i] <= 10000
//
// Related Topics 堆 分治算法
// 👍 127 👎 0

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class GetLeastNumbers {

    //方法1：利用优先队列的二叉堆(大根堆)，时间复杂度 O(nlogK), 空间复杂度 O(k)
    public int[] getLeastNumbers1(int[] arr, int k) {
        if(k == 0 || arr.length == 0) {
            return new int[0];
        }

        Queue<Integer> priorityQueue = new PriorityQueue<>(k, (v1,v2) -> v2 - v1);
        for (int i = 0; i < arr.length; i++) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(arr[i]);
            } else if (arr[i] < priorityQueue.peek()) {
                priorityQueue.poll();
                priorityQueue.add(arr[i]);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }

    //方法2：利用优先队列的二叉堆(小根堆)，时间复杂度 O(nlogn), 空间复杂度 O(n)
    public int[] getLeastNumbers2(int[] arr, int k) {
        Queue<Integer> priorityQueue = new PriorityQueue<>();
        for(int i = 0; i < arr.length; i++) {
            priorityQueue.add(arr[i]);
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll();
        }
        return res;
    }

    //方法3：排序，时间复杂度 O(NlogN)，空间复杂度 O()
    public int[] getLeastNumbers3(int[] arr, int k) {
        Arrays.sort(arr);

        int[] res = new int[k];
        for(int i = 0; i < k; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    //方法4：快排/分治思想，时间复杂度 O()，空间复杂度 O()
    public int[] getLeastNumbers4(int[] arr, int k) {

    }
}
