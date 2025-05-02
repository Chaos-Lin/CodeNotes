# 输入输出
int n = sc.nextInt(); 
String s = sc.next();
double t = sc.nextDouble();
String s = sc.nextLine();
sc.hasNext()
# 栈
`Stack<Integer> stack = new Stack<>();`
`stack.push()`
`stack.pop()`
# 队列
`Queue<String> queue = new LinkedList<>();`
`queue.push()`
`queue.pop()`

# String
转为字符数组 `char[] array =  str.toCharArray();`
字符数组转String `String key = new String(array);`
`s.charAt(0)` 获取字符串的第一个字符
`s.charAt(i)`
取子串`String.substring()`
`String[] parts = timeStr.split(":");`
```java
String timeStr = String.format("%02d%02d", hour, minute);
String reversedStr = new StringBuilder(timeStr).reverse().toString();
return timeStr.equals(reversedStr);
```

# List
`size`
添加元素 `list.add(str);`
数组排序 `Arrays.sort(array);`
取子数组`Arrays.copyOfRange()`

数组转list `Arrays.asList(数组);`
list转数组 list.toArray(new String[list.size]);
数组转String `Arrays.toString(数组)`

# Set
不想重复就用这个类型
存在 `set.contains()`
添加 `set.add()`
去除 `set.remove(某个字符)`

# Map

获取Map的value，如果不存在就采用默认方法 `List<String> list = hashMap.getOrDefault(key, new ArrayList<String>());`
添加键值对 `map.put(key,value);`
获取所有的值 `map.values()`

# Math
`Math.max`

# 默认数组的
`length`

最小值 `int result = Arrays.stream(vessel).min().orElse(Integer.MAX_VALUE);`
最大值 `int result = Arrays.stream(vessel).max().orElse(Integer.MIN_VALUE);`

初始化数组 `int[] intArray = {1, 2, 3, 4, 5};`

## 二维数组
行数`arr.length`
列数`arr[0].length`
排序与双指针


# 滑动窗口

```java
//外层循环扩展右边界，内层循环扩展左边界
for (int l = 0, r = 0 ; r < n ; r++) {
	//当前考虑的元素
	while (l <= r && check()) {//区间[left,right]不符合题意
        //扩展左边界
    }
    //区间[left,right]符合题意，统计相关信息
}
```

# 前缀和
```
pre[i]-> [0,i] = pre[i-1] +nums[i]
pre[j]-> [0;j]
[j,i]  pre[i]- pre[j-1] //因为要包含nums[j]
pre[j-1] == pre[i] - k;
//也就是说for循环到i时，只需要将pre[i]-k，查看有没有存在前缀和为这个的，就可以判断了
```

# 回溯算法
```java
vector<elemType> res = {} 
void backtrace(可选列表, 已选列表){
    if(到达结束条件){
        res.push_back(已选列表);
        return;
    }

    for(选择 : 选择列表){
    	排除无效的选择（剪枝）
        做选择;
        backtrace(可选列表, 已选列表);
        撤销选择;
    }
}

```




# 树

## 中序遍历
递归
```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        inorder(root, res);
        return res;
    }

    public void inorder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
        //这里的顺序可以随便改 根据算法的不同
    }
}
```
迭代
```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Deque<TreeNode> stk = new LinkedList<TreeNode>();
        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.pop();
            res.add(root.val);
            root = root.right;
        }
        return res;
    }
}
```

## DFS
```java
void traverse(TreeNode root) {
    // 判断 base case
    if (root == null) {
        return;
    }
    // 访问两个相邻结点：左子结点、右子结点
    traverse(root.left);
    traverse(root.right);
}
```

## BFS
```java
public List<Integer> levelOrder(TreeNode root) {
    List<Integer> result = new LinkedList<>();
    if (root == null) {
        return result;
    }
    Queue<TreeNode> queue = new LinkedList<>();
    queue.offer(root);
    while (!queue.isEmpty()) {
        TreeNode p = queue.pop();
        result.add(p);
        if (p.left != null) {
            queue.add(p.left);
            numList.add(p.left.val);
        }
        if (p.right != null) {
            queue.add(p.right);
            numList.add(p.right.val);
        }
    }
    return result
}
```

## 分治算法
```java
public ResultType traversal(TreeNode root) {
    // null or leaf
    if (root == null) {
        // do something and return
    }
    // Divide
    ResultType left = traversal(root.Left)
    ResultType right = traversal(root.Right)
    // Conquer
    ResultType result = Merge from left and right
    return result
}
```
# 图
可以利用DFS来进行搜索，将其视为四叉树，如果遍历过或者不在网格内则返回

# 排序

## 快速排序
```java
class Solution {
    int quickselect(int[] nums, int l, int r, int k) {
        if (l == r) return nums[k];
        int x = nums[l], i = l - 1, j = r + 1;
        while (i < j) {
            do i++; while (nums[i] < x);
            do j--; while (nums[j] > x);
            if (i < j){
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
            }
        }
        if (k <= j) return quickselect(nums, l, j, k);
        else return quickselect(nums, j + 1, r, k);
    }
    public int findKthLargest(int[] _nums, int k) {
        int n = _nums.length;
        return quickselect(_nums, 0, n - 1, n - k);
    }
}
```

# 搜索

## 二分搜索

# 贪心算法

# 动态规划
```java
// 1.通用初始化
vector<int> dp(容量 + 1, base case1);
// 2.边界初始化
dp[0][0][...] = base case2
// 3.状态转移
for 状态1的个数
	for 状态2的个数
		for ...
			dp[状态1][状态2][...] = 求最值 or 求和(选择1, 选择2，...)
```