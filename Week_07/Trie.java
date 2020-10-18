package com.thinpeng.geek.algorithm.week07;

//208. 实现 Trie (前缀树)
//实现一个 Trie (前缀树)，包含 insert, search, 和 startsWith 这三个操作。
//
// 示例:
//
// Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // 返回 true
//trie.search("app");     // 返回 false
//trie.startsWith("app"); // 返回 true
//trie.insert("app");
//trie.search("app");     // 返回 true
//
// 说明:
//
//
// 你可以假设所有的输入都是由小写字母 a-z 构成的。
// 保证所有输入均为非空字符串。
//
// Related Topics 设计 字典树
// 👍 432 👎 0

public class Trie {

    Trie[] next;
    boolean isEnd = false; //加入 isEnd 标识当前结点是否可以为一个单词的结尾。
                            //比如 word 与 words 在 d 处把 isEnd 设置为 true 才能标明 word 是一个单词。

    /** Initialize your data structure here. */
    public Trie() {
        next = new Trie[26];
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        char[] c = word.toCharArray();
        Trie curr = this;
        for (int i = 0; i < c.length; i++) {
            int index = c[i] - 'a';
            if (curr.next[index] == null) {
                curr.next[index] = new Trie();
            }
            curr = curr.next[index];
        }
        curr.isEnd = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie cur = this;
        char[] c = word.toCharArray();
        for (int i = 0; i < c.length; i++) {
            int index = c[i] - 'a';
            if (cur.next[index] == null) {
                return false;
            }
            cur = cur.next[index];
        }
        return cur.isEnd;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie cur = this;
        char[] c = prefix.toCharArray();
        for (int i = 0; i < c.length; i++) {
            int index = c[i] - 'a';
            if (cur.next[index] == null) {
                return false;
            }
            cur = cur.next[index];
        }
        return true;
    }

    public static void main(String[] args) {
        
    }
}
