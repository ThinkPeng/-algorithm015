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
  
  
  ```
- 双端队列(Deque)：两端都可以进出的队列。Java 中，可直接使用 Deque 来代替栈和队列。(Deque<T> deque = new LinkedList<>();)
