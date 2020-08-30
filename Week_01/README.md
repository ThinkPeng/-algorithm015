## 一、本周学习内容

1. 数组、链表、跳表
2. 栈、队列、优先队列、双端队列

(1)双端队列（Deque，Double-End Queue），一种可以从头和尾插入元素，也可以从头和尾删除元素的数据结构，可以理解为 Stack 和 Queue 的结合体。
在工程实现中，推荐直接使用 Deque 代替 Queue 和 Stack:
```
Deque<Integer> stack = new ArrayQueue<Integer>();
```

(2)优先队列（Priority Queue），

## 二、做题时使用的解题技巧
 
1. 双指针法
2. 两端夹逼法
3. 递归
3. 位运算 + 二进制代替 bool 数组

## 三、Queue 和 Priority Queue 源码解析
1、Queue
