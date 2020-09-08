## 一、学习内容

1. 递归
```
//Java 递归模版代码：
public void recur(int level, int param) { 
  // terminator(递归终止条件)
  if (level > MAX_LEVEL) { 
    // process result 
    return; 
  }
  // process current logic (处理当前层逻辑)
  process(level, param); 
  // drill down (下探到下一层)
  recur( level: level + 1, newParam); 
  // restore current status (清理当前层)
}
```

思维要点：
- 不要人肉递归；
- 找到最近最简方法，将其拆解成可重复解决的问题；
- 数学归纳法思维；

2. 分治、回溯

## 二、做题时使用的解题技巧

1. 二叉搜索树(BST)的中序遍历是递增的。
