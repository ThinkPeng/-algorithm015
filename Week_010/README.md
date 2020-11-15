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
  
  
  
  
  
  
  
  
  
  
  
 
