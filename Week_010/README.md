#### 一、数组、链表、跳表

1、特性：

- 数组：内存地址连续，可随机访问，时间为 O(1)，但增加、删除元素时，时间为 O(N)。
- 链表：内存地址不连续，增加、删除元素时间为 O(1)，但访问某个元素所需时间为 O(N)，且需要额外的空间保存下一个结点的指针。
- 跳表：Skip List，该数据结构只能用于元素有序的情况，对标的是平衡树（AVL Tree）和二分查找，插入/删除/搜索的时间复杂度都为 O(log N)，Redis 中使用了该数据结构。跳表的实现是通过添加多级索引（升维思想 + 空间换时间）。

2、跳表在工程中的应用

- LRU Cache：

https://www.jianshu.com/p/b1ab4a170c3c 

https://leetcode-cn.com/problems/lru-cache/
- Redis：

https://www.zhihu.com/question/20202931 

https://redisbook.readthedocs.io/en/latest/internal-datastruct/skiplist.html

3、相关解题技巧
- 双指针
- 快慢指针


#### 二、栈、队列、双端队列、优先队列

1、特性

- 栈(Stack)：先进后出。 
  ```
  Stack<T> stack = new Stack();
  stack.push(1);//末尾添加元素
  stack.pop();  //弹出末尾元素
  stack.peek(); //查看末尾元素
  ```
- 队列(Queue)：先进先出。
  ```
  Queue<T> queue = new LinkedList();
  queue.offer(1);//末尾添加元素
  queue.poll();  //弹出末尾元素
  queue.peek();  //查看末尾元素
  ```
- 双端队列(Deque)：两端都可以进出的队列。Java 中，可直接使用 Deque 来代替栈和队列。官方文档：https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html
  ```
  Deque<T> deque = new LinkedList<>();
  deque.addFirst(1);   //队头添加元素
  deque.removeFirst(); //队头删除元素
  deque.peekFirst();   //查看队头元素
  deque.addLast(1);    //队尾添加元素
  deque.removeLast();  //队尾删除元素
  deque.peekLast();    //查看队尾元素
  ```
- 优先队列(PriorityQueue)：按照元素优先级出队的队列。插入操作 O(1)，取出操作 O(log N)。官方文档：https://docs.oracle.com/javase/10/docs/api/java/util/PriorityQueue.html
  ```
  PriorityQueue<T> priorityQueue = new PriorityQueue<>();
  priorityQueue.offer(1);
  priorityQueue.poll();
  priorityQueue.peek();
  ```
  
  2、常见数据数据结构时间复杂度
  ![image](https://github.com/ThinkPeng/-algorithm015/blob/master/Week_010/CommonDataStructure.png)
  
  #### 三、哈希表、映射、集合
  
  1、特性
  
  - 哈希表通过将 Key 使用映射函数映射到数组中的一个位置来加快访问速度。插入、查找、删除的时间复杂度为 O(1)。Java 对应的数据结构有 HashMap / TreeMap / HashSet / TreeSet。
  
  #### 四、树、二叉树、二叉搜索树
  
  1、二叉树数据结构
  ```
  public class TreeNode { 
    public int val;
    public TreeNode left, right; 
    public TreeNode(int val) {
      this.val = val; 
      this.left = null; 
      this.right = null;
    } 
  }
  ```
  
  2、二叉树的遍历
  - 前序(Pre-order)：根-左-右 
  - 中序(In-order)：左-根-右
  - 后序(Post-order)：左-右-根。以上三种遍历一般通过递归或栈来实现。
  - 层序遍历 (Level-order)：一般使用队列来实现。
  
  ```
  //树的中序遍历
  //题目：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
  //递归：时间复杂度 O(N), 空间复杂度 O(logN)，最坏是 O(N)
  //对于前序、后序遍历，就是调换 helper 方法中 res.add 的位置。
  public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> res = new ArrayList();
      helper(root, res);
      return res;
  }
  private void helper(TreeNode root, List<Integer> res) {
      if (root != null) {
          if (root.left != null) helper(root.left, res);
          res.add(root.val);
          if (root.right != null) helper(root.right, res);
      }
  }

  //非递归方式
  public List<Integer> inorderTraversal(TreeNode root) {
      List<Integer> res = new ArrayList();
      Deque<TreeNode> stack = new ArrayDeque();

      TreeNode curr = root;
      while(curr != null || !stack.isEmpty()) {
          while (curr != null) {
              stack.addLast(curr);
              curr = curr.left;
          }
          curr = stack.pollLast();
          res.add(curr.val);
          curr = curr.right;
      }
      return res;
  }
  ```
  
  ```
  public List<List<Integer>> levelOrder(TreeNode root) {
    List<List<Integer>> allResults = new ArrayList<>();
    if (root == null) {
        return allResults;
    }
    Queue<TreeNode> nodes = new LinkedList<>();
    nodes.add(root);
    while (!nodes.isEmpty()) {
        int size = nodes.size();
        List<Integer> results = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            TreeNode node = nodes.poll();
            results.add(node.val);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        allResults.add(results);
    }
    return allResults;
  }
  ```  
  
  3、二叉搜索树
  
  特性：
  - 左子树上所有结点的值均小于它的根结点的值;
  - 右子树上所有结点的值均大于它的根结点的值;
  - 以此类推:左、右子树也分别为二叉查找树。
  - 二叉搜索树的中序遍历必定是升序的。
  
  #### 五、递归
  
  1、递归模版
  ```
  public void recur(int level, int param) {
      // 终止条件
      if (level > MAX_LEVEL) {
          return; 
      }
      
      //处理当前层逻辑
      process(level, param);
     
      //下探到下一层
      recur(level + 1, newParam);
      
      //还原当前层的状态（可选）
  }
  ```
  
  #### 六、堆、二叉堆
  
  1、堆的定义
  
  可以迅速找到一堆数中的最大值（大顶堆）或最小值（小顶堆）的数据结构。
  
  时间复杂度：
  - 获取最大值：O(1)
  - 删除最大值：O(logN)
  - 插入：O(logN) or O(1)
  
  2、二叉堆的性质（以大顶堆为例）
  - 是一课完全树
  - 树中任意结点的值 >= 其子结点的值
  
  3、二叉堆的实现 （具体可查看 Java HashMap 的源代码）
  - 二叉堆一般通过数组来实现。
  - 索引为 i 的左孩子索引是 (2 * i + 1)，右孩子是 (2 * i + 2)，父结点索引是 floor((i - 1) / 2)。
  - 插入操作：
    - 新元素一律插入到堆的尾部。
    - 依次向上上调整个堆的结构(一直到根)。 =》 HeapifyUp
  - 删除操作
    - 将堆尾元素替换到顶部
    - 依次从根部向下调整整个堆的结构。 =》 HeapifyDown
    
  4、使用方法
  在 Java 中可直接使用优先队列 PriorityQueue 来实现堆的功能。
  ```
  //二叉堆的实现可以借助优先队列(PriorityQueue)
  //大顶堆
  PriorityQueue pq = new PriorityQueue((v1, v2), v2 - v1);
  //小顶堆
  PriorityQueue pq = new PriorityQueue();

  //优先队列自定义优先规则
  Queue<int[]> priorityQueue = new PriorityQueue(new Comparator<int[]>() {
      public int compare (int[] m, int[] n) {
          return m[1] - n[1];//小顶堆
      }
  });
  ```
    
  #### 七、图
  
  1、图的表示
  
  - 用二维数组表示，arr[i][j] 表示结点 i 和 j 之间的连通情况。
  
  #### 八、分治、回溯
  
  1、分治模版
  
  ```
  private static int divide_conquer(Problem problem) { 
    //终止条件
    if (problem == NULL) {
      int res = process_last_result();
      return res;     
    }
    subProblems = split_problem(problem);//处理当前层逻辑（把大问题分割成小问题）

    res0 = divide_conquer(subProblems[0]);//下探到下一层
    res1 = divide_conquer(subProblems[1]);

    result = process_result(res0, res1);//组装结果

    return result;
  }
  ```
    
  2、回溯
  
  回溯法采用试错的思想，它尝试分步的去解决一个问题。在分步解决问题的过程中，当它通过尝试发现现有的分步答案不能得到有效的正确的解答的时候，它将取消上一步甚至是上几步的计算，再通过其它的可能的分步解   答再次尝试寻找问题的答案。
  
  回溯法通常用最简单的递归方法来实现，在反复重复上述的步骤后可能出现两种 情况:
  - 找到一个可能存在的正确的答案;
  - 在尝试了所有可能的分步方法后宣告该问题没有答案。 在最坏的情况下，回溯法会导致一次复杂度为指数时间的计算。
  
  回溯代码模版参考递归的代码模版。
  
  #### 九、DFS 和 BFS
  
  代码模版参考树的遍历。
  
  #### 十、贪心算法
  
  1、定义
  
  贪心算法是一种在每一步选择中都采取在当前状态下最好或最优(即最有利)的选择，从而希望导致结果是全局最好或最优的算法。
  
  贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。动态规划则会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。
  
  2、适用场景
  
  问题能够分解成子问题来解决，子问题的最优解能递推到最终问题的最优解。这种子问题最优解称为最优子结构。
  
  #### 十一、二分查找
  
  1、二分查找的前提
  - 目标函数单调性（单调递增或递减）
  - 存在上下界面
  - 能够通过索引访问
  
  2、代码模版
  ```
  //求中间位置时使用 mid = (right - left) / 2 + left
  //不直接使用 (left + right) / 2 的原因是 left + right 结果有可能大于 2147483647 导致溢出。
  //也可使用 (left + right) >> 1 ，但可能也需要考虑溢出问题。
  public int binarySearch(int[] array, int target) {
      int left = 0, right = array.length - 1, mid;
      while (left <= right) {
          mid = (right - left) / 2 + left;

          if (array[mid] == target) {
              return mid;
          } else if (array[mid] > target) {
              right = mid - 1;
          } else {
              left = mid + 1;
          }
      }

      return -1;
  }
  ```
 
  #### 十二、动态规划
  
  1、关键点
  
  动态规划 和 递归或者分治 没有根本上的区别(关键看有无最优的子结构)
  
  共性：找到重复子问题。
  
  差异性：最优子结构、中途可以淘汰次优解。
  
  2、做题步骤
  - 寻找最优子结构。
  - 定义状态
  - 定义状态转移方程
  
  #### 十三、字典树、并查集
  
  1、字典树(Trie)
  - 定义：字典树，即 Trie 树，又称单词查找树或键树，是一种树形结构。典型应用是用于统计和排序大量的字符串(但不仅限于字符串)，所以经常被搜索引擎系统用于文本词频统计。
  - 优点：最大限度地减少无谓的字符串比较，查询效率比哈希表高。
  - 性质：
    - 结点本身不存放完整单词
    - 从根结点到某一结点，路径上经过的字符连接起来，为该结点对应的字符串;
    - 每个结点的所有子结点路径代表的字符都不相同。
  - 代码模版：
  ```
  class Trie {
    private boolean isEnd;
    private Trie[] next;
    /** Initialize your data structure here. */
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            int n = words[i] - 'a';
            if (curr.next[n] == null) curr.next[n] = new Trie();
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0;i < words.length;i++) {
            node = node.next[words[i] - 'a'];
            if (node == null) return null;
        }
        return node;
    }
  }
  ```

  2、并查集(Disjoint Set)
  - 适用场景：组团、配对问题
  - 基本操作：
    - makeSet(s)：建立一个新的并查集，其中包含 s 个单元素集合。
    - unionSet(x, y)：把元素 x 和元素 y 所在的集合合并，要求 x 和 y 所在的集合不相交，如果相交则不合并。
    - find(x)：找到元素 x 所在的集合的代表，该操作也可以用于判断两个元 素是否位于同一个集合，只要将它们各自的代表比较一下就可以了。
  - 代码模版：
  ```
  class UnionFind { 
	private int count = 0; 
	private int[] parent; 
	public UnionFind(int n) { 
         count = n;
         parent = new int[n];
         for (int i = 0; i < n; i++) {
             parent[i] = i;
         }
	} 
	public int find(int p) { 
        while (parent[p] != p) {
            parent[p] = parent[parent[p]];//状态压缩
            p = parent[p];
        }
        return p;
	}
	public void union(int p, int q) { 
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;
        parent(rootP) = rootQ;。//合并
        count--;
	}  
    public int getCount() {
        return count;
    }
  }
  ```

  #### 十四、高级搜索
  
  1、剪枝
  
  对回溯算法的优化，对一些能够提前判定其不满足最终答案的分枝，提前结束当前层的递归。

  2、双向 BFS
  
  相关题目：
  - https://leetcode-cn.com/problems/word-ladder/
  - https://leetcode-cn.com/problems/minimum-genetic- mutation/
  
  3、启发式搜索 A* search
  
  相关题目：
  - https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/
  - https://leetcode-cn.com/problems/sliding-puzzle/
  - https://leetcode-cn.com/problems/sudoku-solver/

  #### 十五、高级树、AVL 树、红黑树
  
  1、AVL 树
  
  - 是一种平衡二叉搜索树
  - 每个结点存 balancefactor = {-1,0,1}
  - 适用四种旋转操作来维持平衡
  - 缺点：结点需要存放额外信息，且调整次数频繁

  2、红黑树(Red-Black Tree)
  
  - 定义：红黑树是一种近似平衡的二叉搜索树(Binary Search Tree)，它能够确保任何一个结点的左右子树的高度差小于两倍。
  - 红黑树是满足如下条件的二叉搜索树:
    - 每个结点要么是红色，要么是黑色
    - 根结点是黑色
    - 每个叶结点(NIL结点，空结点)是黑色的。
    - 不能有相邻接的两个红色结点
    - 从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点。
  - 关键性质：从根到叶子的最长的可能路径不多于最短的可能路径的两倍长。
  
  3、AVL 树 VS 红黑树
  - AVL trees provide faster lookups than Red Black Trees because they are more strictly balanced.
  - Red Black Trees provide faster insertion and removal operations than AVL trees as fewer rotations are done due to relatively relaxed balancing.
  - AVL trees store balance factors or heights with each node, thus requires storage for an integer per node whereas Red Black Tree requires only 1 bit of information per node.
  - Red Black Trees are used in most of the language libraries like map, multimap, multisetin C++whereas AVL trees are used in databases where faster retrievals are required.

  #### 十六、位运算
  
  1、指定位置的位运算
  - 将x最右边的 n 位清零：x & (~0 << n)
  - 获取x的第 n 位值(0 或者 1)：(x >> n) & 1
  - 获取x的第 n 位的幂值：x & (1 << n)
  - 仅将第 n 位置为 1：x | (1 << n)
  - 仅将第 n 位置为 0：x & (~(1 << n))
  - 将x最高位至第 n 位(含)清零：x & ((1 << n) - 1)

  2、实战位运算要点
  - 判断奇偶：
    - x % 2 == 1 —> (x & 1) == 1 
    - x % 2 == 0 —> (x & 1) == 0 
  - x >> 1 —> x / 2 即：x = x / 2; —> x = x >> 1; mid = (left + right) / 2; —> mid = (left + right) >> 1;
  - X = X & (X - 1)清零最低位的1
  - X & -X => 得到最低位的1
  - X & ~X => 0

  3、N 皇后的位运算解法
  ```
  class Solution {
    private int size;
    private int count;

    private void solve(int row, int ld, int rd) { 
        if (row == size) {
            count++;
            return; 
        }
        int pos = size & (~(row | ld | rd)); while (pos != 0) {
            int p = pos & (-pos);
            pos -= p; // pos &= pos - 1;
            solve(row | p, (ld | p) << 1, (rd | p) >> 1);
        } 
    }

    public int totalNQueens(int n) { 
        count = 0;
        size = (1 << n) - 1;
        solve(0, 0, 0);
        return count; 
    }
  }
  ```
  
  #### 十七、布隆过滤器、LRU Cache
  
  1、布隆过滤器(Bloom Filter)
  - 布隆过滤器一个很长的二进制向量和一系列随机映射函数。布隆过滤器可以用于检索一个元素是否在一个集合中。
  - 优点是空间效率和查询时间都远远超过一般的算法，缺点是有一定的误识别率和删除困难。因此，布隆过滤器一般用于确定一个元素是否【不在】一个集合中。
  - Java 实现：https://github.com/lovasoa/bloomfilter/blob/master/src/main/ java/BloomFilter.java
  
  2、LRU Cache
  - 两个要素：大小、替换策略
  - 数据结构：HashTable + Double LinkedList
  - 替换策略：
    - LFU - least frequently used 
    - LRU - least recently used
  - 相关题目：https://leetcode-cn.com/problems/lru-cache/#/
  - 代码：
  ```
  public class LRUCache {
    private Map<Integer, Integer> map;

    public LRUCache(int capacity) {
        map = new LinkedCappedHashMap<>(capacity);
    }
    public int get(int key) { 
        if (!map.containsKey(key)) { 
            return -1; 
       }
       return map.get(key);
   }
   public void put(int key, int value) { 
       map.put(key,value);
   }
   private static class LinkedCappedHashMap<K,V> extends LinkedHashMap<K,V> {
       int maximumCapacity;
        LinkedCappedHashMap(int maximumCapacity) { 
            super(16, 0.75f, true); 
            this.maximumCapacity = maximumCapacity;
       }
        protected boolean removeEldestEntry(Map.Entry eldest) { 
            return size() > maximumCapacity;
        } 
    }
  }
  ```
  
  #### 十八、排序算法
  
  1、分类：
  - 比较类排序：通过比较来决定元素间的相对次序，由于其时间复杂度不能突破 O(nlogn)，因此也称为非线性时间比较类排序。
  - 非比较类排序：不通过比较来决定元素间的相对次序，它可以突破基于比较排序的时间下界，以线性时间运行，因此也称为线性时间非比较类排序。
  
  2、排序算法总揽
  ![image](https://github.com/ThinkPeng/-algorithm015/blob/master/Week_010/AllOrderAlgorithm.png)
  
  ![image](https://github.com/ThinkPeng/-algorithm015/blob/master/Week_010/OrderAlgorithmDetail.png)
  
  #### 十九、高级动态规划
  
  1、复杂度来源
  - 状态拥有更多维度(二维、三维、或者更多、甚至需要压缩) 
  - 状态方程更加复杂
  
  
  #### 二十、字符串
  
  1、常见算法题目
  - 判断回文串：https://leetcode-cn.com/problems/valid-palindrome/
  - 最长子串、子序列、编辑距离
    - https://leetcode-cn.com/problems/longest-common-subsequence/
    - https://leetcode-cn.com/problems/edit-distance/

  2、字符串匹配算法
  - 暴力法
  - Rabin-Karp 算法                       
  - KMP 算法
 
