## 一、本周学习内容

1. 数组、链表、跳表
2. 栈、队列、优先队列、双端队列

(1) 双端队列（Deque，Double-End Queue），一种可以从头和尾插入元素，也可以从头和尾删除元素的数据结构，可以理解为 Stack 和 Queue 的结合体。
在工程实现中，推荐直接使用 Deque 代替 Queue 和 Stack:
```
Deque<Integer> stack = new ArrayDeque<Integer>();//也可使用 LinkedList
```

(2) 优先队列（Priority Queue），可以按照元素的优先级获取元素的数据结构。
底层具体实现的数据结构较为多样和复杂，可以是：heap、bst、treap，其插入取出操作时间复杂度是 O(logN)，插入操作最好的情况能到 O(1)。

## 二、做题时使用的解题技巧
 
1. 双指针法
2. 两端夹逼法
3. 递归
3. 位运算 + 二进制代替 bool 数组

## 三、Queue 和 Priority Queue 源码解析
1、Queue
Java 中 Queue 定义了队列基本操作的接口：
add(e)、remove()、element()：分别对应队列的添加元素、弹出元素、查看队头元素三个操作，这三个方法操作失败会抛出异常。
offer(e)、poll()、peek()：也是分别对应队列的添加元素、弹出元素、查看队头元素三个操作，但这三个方法操作失败会返回一个特殊值。

2、Priority Queue
(1) 重要成员变量说明
- Object[] queue : 优先队列的底层使用了二叉堆这一数据结构。
- Comparator<? super E> comparator : 比较器，用于自定义元素的优先级规则。
- int size : 记录当前队列存在的元素个数。
- int DEFAULT_INITIAL_CAPACITY = 11 : 当创建优先队列未指定队列大小时，将使用默认大小11。

（2）基本操作实现分析
- 添加操作：add(e) == offer(e)，首先检查当前队列是否已满，若是，调用 grow() 方法进行扩容。然后执行插入操作，如果当前队列没有元素，则直接插入。否则，使用二叉堆的插入方式根据元素优先级进行插入，时间复杂度为 O(logN)。
- 扩容操作：grow(minCapacity)，如果原来的队列大小小于 64，则扩容为原来容量 + 2，否则，扩容为原来的 1.5 倍。
- 查看队头元素：peek()，返回 queue[0]，时间复杂度为 O(1)。
- 弹出队头元素：poll()，如果队列无元素，返会 null。否则，获取队头元素，用于最后的返回。返回之前，对二叉堆进行删除根节点和 siftDown 操作。时间复杂度为 O(logN)。

