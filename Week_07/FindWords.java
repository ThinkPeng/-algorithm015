package com.thinpeng.geek.algorithm.week07;

//212. 单词搜索 II
//给定一个二维网格 board 和一个字典中的单词列表 words，找出所有同时在二维网格和字典中出现的单词。
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母在一个单词中不允许被重复使用。
//
//
// 示例:
//
// 输入:
//words = ["oath","pea","eat","rain"] and board =
//[
//  ['o','a','a','n'],
//  ['e','t','a','e'],
//  ['i','h','k','r'],
//  ['i','f','l','v']
//]
//
//输出: ["eat","oath"]
//
// 说明:
//你可以假设所有输入都由小写字母 a-z 组成。
//
// 提示:
//
//
// 你需要优化回溯算法以通过更大数据量的测试。你能否早点停止回溯？
// 如果当前单词不存在于所有单词的前缀中，则可以立即停止回溯。什么样的数据结构可以有效地执行这样的操作？散列表是否可行？为什么？ 前缀树如何？如果你想学习如何
//实现一个基本的前缀树，请先查看这个问题： 实现Trie（前缀树）。
//
// Related Topics 字典树 回溯算法
// 👍 262 👎 0

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TrieNode {
    String word;
    TrieNode[] next = new TrieNode[26];
}

public class FindWords {
    int[] dx = new int[]{1, -1, 0, 0};
    int[] dy = new int[]{0, 0, 1, -1};

    //方法1：简洁点
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        //将所有单词构造成字典树。
        TrieNode rootTrie = buildTrie(words);

        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, board, rootTrie, res);
            }
        }
        return res;
    }

    private void dfs(int i, int j, char[][] board, TrieNode trieNode, List<String> res) {
        if (i < 0 || j < 0 || i == board.length || j == board[0].length || board[i][j] == '@') {
            return;
        }

        char letter = board[i][j];
        TrieNode nextTrieNode = trieNode.next[letter - 'a'];
        if (nextTrieNode == null) {
            return ;
        } else if (nextTrieNode.word != null) {
            res.add(nextTrieNode.word);
            nextTrieNode.word = null;
            //这里不能结束
        }

        board[i][j] = '@';
        for (int k = 0; k < 4; k++) {
            dfs(i + dx[k], j + dy[k], board, nextTrieNode, res);
        }
        board[i][j] = letter;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            TrieNode cur = root;
            char[] charArray = words[i].toCharArray();
            for (char c : charArray) {
                int index = c - 'a';
                if (cur.next[index] == null) {
                    cur.next[index] = new TrieNode();
                }
                cur = cur.next[index];
            }
            cur.word = words[i];
        }
        return root;
    }

    //方法2：
    Set<String> res = new HashSet<>();
    public List<String> findWords2(char[][] board, String[] words) {
        //将所有单词构造成字典树。
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(i + " " + j);
                dfs2(i, j, board, trie, "");
            }
        }
        return new ArrayList(res);
    }

    private void dfs2(int i, int j, char[][] board, Trie trie, String path) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == '@') {
            return;
        }

        if (!trie.startsWith(path)) {
            return;
        }

        path += board[i][j];
        if(trie.search(path)) {
            res.add(path);
        }

        char temp = board[i][j];
        board[i][j] = '@';
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            dfs2(x, y, board, trie, path);
        }
        board[i][j] = temp;
    }

    public static void main(String[] args) {
        FindWords fw = new FindWords();
        char[][] boards = new char[][]{
            {'b','a','a','b','a','b'},
            {'a','b','a','a','a','a'},
            {'a','b','a','a','a','b'},
            {'a','b','a','b','b','a'},
            {'a','a','b','b','a','b'},
            {'a','a','b','b','b','a'},
            {'a','a','b','a','a','b'}
        };
        String[] words = new String[]{"bbaabaabaaaaabaababaaaaababb",
                "aabbaaabaaabaabaaaaaabbaaaba","babaababbbbbbbaabaababaabaaa",
                "bbbaaabaabbaaababababbbbbaaa","babbabbbbaabbabaaaaaabbbaaab",
                "bbbababbbbbbbababbabbbbbabaa","babababbababaabbbbabbbbabbba",
                "abbbbbbaabaaabaaababaabbabba","aabaabababbbbbbababbbababbaa",
                "aabbbbabbaababaaaabababbaaba","ababaababaaabbabbaabbaabbaba",
                "abaabbbaaaaababbbaaaaabbbaab","aabbabaabaabbabababaaabbbaab",
                "baaabaaaabbabaaabaabababaaaa","aaabbabaaaababbabbaabbaabbaa",
                "aaabaaaaabaabbabaabbbbaabaaa","abbaabbaaaabbaababababbaabbb",
                "baabaababbbbaaaabaaabbababbb","aabaababbaababbaaabaabababab",
                "abbaaabbaabaabaabbbbaabbbbbb","aaababaabbaaabbbaaabbabbabab",
                "bbababbbabbbbabbbbabbbbbabaa","abbbaabbbaaababbbababbababba",
                "bbbbbbbabbbababbabaabababaab","aaaababaabbbbabaaaaabaaaaabb",
                "bbaaabbbbabbaaabbaabbabbaaba","aabaabbbbaabaabbabaabababaaa",
                "abbababbbaababaabbababababbb","aabbbabbaaaababbbbabbababbbb",
                "babbbaabababbbbbbbbbaabbabaa"};
        System.out.println(fw.findWords2(boards, words));
    }
}
