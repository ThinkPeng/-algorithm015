package com.thinpeng.geek.algorithm.week01;

//面试题 01.01 判定字符是否唯一
//实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
//
// 示例 1：
//
// 输入: s = "leetcode"
//输出: false
//
//
// 示例 2：
//
// 输入: s = "abc"
//输出: true
//
//
// 限制：
//
// 0 <= len(s) <= 100
// 如果你不使用额外的数据结构，会很加分。
//
// Related Topics 数组
// 👍 51 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
public class CharUnique {
    //二进制 + 位运算解法（时间复杂度 O(n)，空间复杂度 O(1)）
    public static boolean isUnique(String astr) {
        int mask = 0;
        for (int i = 0; i < astr.length(); i++) {
            int move_bit = astr.charAt(i) - 'a';
            if((mask & (1 << move_bit)) != 0) {
                return false;
            } else {
                mask |= (1 << move_bit);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isUnique(("_c")));
        System.out.println(isUnique("abc"));
    }
}
//leetcode submit region end(Prohibit modification and deletion)

// 思路总结：
// 1、可以使用双重循环的暴力求解方法，时间复杂度为 O(n^2)，空间复杂度为 O(1);
//
// 2、更快速的方法是借助 Set 来存放所有字符，时间复杂度为 O(n)，空间复杂度为 O(n);
//
// 3、假设字符串中的字符是 [a-z]，可以使用一个大小为 26 的 bool 数组，遍历字符串中的字符，
//    通过 char - 'a' 求出字符对应的数组下标值，若该位置的值已经是 1，则表示该字符重复，
//    否则，将该数组该位置的值设置为 1。时间复杂度为 O(n)，空间复杂度为 O(n)；
//
// 4、题目要求不使用额外的数据结构，可以采用 二进制 + 位运算 来实现类似【3】中的思路：
//    使用一个 int 值 mask 来代替 bool 数组，mask 初始值为 0，对应二进制为 000...00(26 个 0)，
//    对于字符串中的每个字符，检查其在 mask 二进制中对应的位置是否为 0。
//   （1）如何求字符对应的 bit 位？
//      先求出该字符距离字符 'a' 的距离（move_bit = astr.charAt(i) - 'a'），然后使用 1 << move_bit 求出
//      对应下标为 1，其余为 0 的数。最后通过 mask |= (1 << move_bit) 将该字符对应 mask 的 bit 位设置为了 1。
//    (2) 如何判断字符重复？
//      使用 (mask & (1 << move_bit)) != 0 可以判断出当前处理的字符已重复。
//    时间复杂度 O(n)，空间复杂度 O(1)


