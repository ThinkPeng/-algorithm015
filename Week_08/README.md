## 一、学习内容

1. 位运算

2. 布隆过滤器（Bloom Filter）

作用：布隆过滤器可以用于判断一个元素是否在一个集合中。

优点：空间和时间效率都远超一般算法。

缺点：有一定的误识别，当查询成功的时候，不代表该元素一定存在。但查询失败的时候，则该元素一定不存在。

3. LRU Cache（Least Recently Use ：最近最少使用）

   Cache 实现方式： HashTable + DoubleLinkedList

   除了 LRU，其他的替换策略，如：LFU（Least Frequently Use ：最近最少频次使用）

4. 排序算法

重点三个 NlogN 的排序：快速排序、归并排序、堆排序



## 二、常用位运算技巧

1. x = x & (x - 1)，清零最低一位的1

2. (x >> n) & 1，获取 x 的第 n 位的值

3.x % 2 == 1(或0) ==> (x & 1) == 1(或0)，判断最后一位的奇偶性
