package com.thinpeng.geek.algorithm.week04;

//55. 跳跃游戏
//给定一个非负整数数组，你最初位于数组的第一个位置。
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。
//
// 判断你是否能够到达最后一个位置。
//
// 示例 1:
//
// 输入: [2,3,1,1,4]
//输出: true
//解释: 我们可以先跳 1 步，从位置 0 到达 位置 1, 然后再从位置 1 跳 3 步到达最后一个位置。
//
//
// 示例 2:
//
// 输入: [3,2,1,0,4]
//输出: false
//解释: 无论怎样，你总会到达索引为 3 的位置。但该位置的最大跳跃长度是 0 ， 所以你永远不可能到达最后一个位置。
//
// Related Topics 贪心算法 数组
// 👍 816 👎 0
public class JumpGame {

    //查找是否有无法抵达的0元素，时间复杂度 O(n^2), 空间复杂度 O(1)
    public boolean canJump(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                boolean canJump = false;
                for (int j = i - 1; j >= 0; j--) {
                    if (nums[j] > i - j) {
                        canJump = true;
                        break;
                    }
                }
                if (!canJump) return false;
            }
        }
        return true;
    }

    //贪心，保存当前能抵达的最右边界，时间复杂度 O(n), 空间复杂度 O(1)
    public boolean canJump2(int[] nums) {
        int rightMost = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= rightMost) {
                rightMost = Math.max(rightMost, i + nums[i]);
                if (rightMost >= nums.length - 1)
                    return true;
            }
        }
        return false;
    }
}
