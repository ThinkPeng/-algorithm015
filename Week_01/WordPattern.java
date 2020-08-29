package com.thinpeng.geek.algorithm.week01;

//290. 单词规律
//给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
//
// 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
//
// 示例1:
//
// 输入: pattern = "abba", str = "dog cat cat dog"
//输出: true
//
// 示例 2:
//
// 输入:pattern = "abba", str = "dog cat cat fish"
//输出: false
//
// 示例 3:
//
// 输入: pattern = "aaaa", str = "dog cat cat dog"
//输出: false
//
// 示例 4:
//
// 输入: pattern = "abba", str = "dog dog dog dog"
//输出: false
//
// 说明:
//你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
// Related Topics 哈希表
// 👍 186 👎 0

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    public static void main(String[] args) {
        wordPattern("abba", "dog cat cat dog");
    }


    //（时间复杂度 O(n), 空间复杂度 O(1)，虽然使用了两个 HashMap，但是每个 Map 最多存放 26 个映射关系）
    public static boolean wordPattern(String pattern, String str) {
        //使用两个 HashMap 的原因是为了解决类似 pattern = "abba", str = "dog dog dog dog" 这类问题。
        Map<String, Character> map_str = new HashMap<>();
        Map<Character, String> map_char = new HashMap<>();

        String[] words = str.split(" ");
        if(pattern.length() != words.length) {
            return false;
        }

        for (int i = 0; i < words.length; i++) {
            char c = pattern.charAt(i);
            String targetStr = map_char.get(c);
            if (targetStr == null) {
               if (map_str.containsKey(words[i])) {
                   return false;
               }
               map_char.put(c, words[i]);
               map_str.put(words[i], c);
            } else {
                if (!targetStr.equals(words[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
