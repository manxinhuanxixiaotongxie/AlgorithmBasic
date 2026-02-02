一、二分查找
设nums为递增（非递减）数组，长为n
需求：
需求 写法 如果不存在
≥x 的第一个元素的下标 lowerBound(nums,x)        结果为 n
> x 的第一个元素的下标 lowerBound(nums,x+1)    结果为 n
<x 的最后一个元素的下标 lowerBound(nums,x)−1 结果为 −1
> ≤x 的最后一个元素的下标 lowerBound(nums,x+1)−1 结果为 −1



二分的写法：
闭区间
开区间

二、二分答案
花费一个log的时间，增加了一个条件 ------二分答案

2.1 求最小
题目求什么 就二分什么

疑问：
问题1：如何把二分答案与数组上的二分查找联系起来？
假设答案在区间[2,5]中 我们相当于在一个虚拟数组[check(2),check(3),check(4),check(5)]
中二分找第一个（或者最后一个）值为true的check(x)
同样可以用红蓝染色法思考

问题2 有些题目 明明m可以是答案 但是确不在二分区间中 比如闭区间二分初始化right = m-1 (或者开区间right = m) 这不会算错吗？
不会算错 注意【答案所在区间】和【二分区间】是两个概念 想一想 如果 二分的while循环每次更新的都是left 那么最终的答案是什么？正好就是m
一般地
如果一开始就能确定m一定可以满足题目要求，那么m就是在二分区间中的。换句话说 二分区间是【尚未确认是否满足题目要求】的数的范围
那些在区间外面的数
都是已确定的满足（不满足）题目要求的数

问题3：什么是循环不变量？
想一想 对于求最小的题目，开区间二分的写法 为什么最终返回的是right
而不是别的数？在初始化（循环之前）、循环中、循环结束后，都时时刻刻保证check(right)为true
check(left)为false 这就叫循环不变量 根据循环不变量 循环结束时left+1=right right就是最小满足要求的数（因为-1就不满足要求了）
所以答案是right

注意：部分题目可以优化二分边界 减少二分次数 从而减少代码运行时间 但是对于初次接触二分答案的同学 无需强求自己写出最有的代码
设定一个比较大的二分上界也是可以的

开区间二分模板（求最小）：
class Solution {
// 计算满足 check(x) == true 的最小整数 x
public int binarySearchMin(int[] nums) {
int left = ; // 循环不变量：check(left) 恒为 false
int right = ; // 循环不变量：check(right) 恒为 true
while (left + 1 < right) { // 开区间不为空
int mid = left + (right - left) / 2;
if (check(mid, nums)) { // 说明 check(>= mid 的数) 均为 true
right = mid; // 接下来在 (left, mid) 中二分答案
} else { // 说明 check(<= mid 的数) 均为 false
left = mid; // 接下来在 (mid, right) 中二分答案
}
}
// 循环结束后 left+1 = right
// 此时 check(left) == false 而 check(left+1) == check(right) == true
// 所以 right 就是最小的满足 check 的值
return right;
}

    // 二分猜答案：判断 mid 是否满足题目要求
    private boolean check(int mid, int[] nums) {
        
    }

}


2.2 求最大
开区间二分模板（求最大）：
class Solution {
// 计算满足 check(x) == true 的最大整数 x
public int binarySearchMax(int[] nums) {
int left = ; // 循环不变量：check(left) 恒为 true
int right = ; // 循环不变量：check(right) 恒为 false
while (left + 1 < right) {
int mid = left + (right - left) / 2;
if (check(mid, nums)) {
left = mid; // 注意这里更新的是 left，和上面的模板反过来
} else {
right = mid;
}
}
// 循环结束后 left+1 = right
// 此时 check(left) == true 而 check(left+1) == check(right) == false
// 所以 left 就是最大的满足 check 的值
return left; // check 更新的是谁，最终就返回谁
}

    // 二分猜答案：判断 mid 是否满足题目要求
    private boolean check(int mid, int[] nums) {
        
    }
}



注意：【求最小】和【求最大】在二分写法上的区别。
【求最小】和二分查找求【排序数组中某元素的第一个位置】是类似的 按照红蓝染色法 左边是不满足要求的 红色 右边是满足要求的 蓝色
【求最大】的题目相反 左边是满足要求的 右边是不满足要求的 这会导致二分写法和上面的【求最小】有一些区别

以开区间二分为例：
1.求最小：check(mid,nums)=true时 更新right= mid 反之更新left=mid 最后返回right
2.求最大 check(mid,nums)=true时，更新left= mid 反之更新right=mid 最后返回left


对于开区间的写法，简单来说check(mid)=true时更新的是谁 最后就返回谁 相比其他二分写法 开区间写法不需要思考加减一些细节  推荐使用开区间写法


在二分查找中，left 和 right 的返回取决于具体的查找目标和边界条件的定义：
返回 right 的情况：
当查找的是 左边界 时，right 通常指向第一个满足条件的位置。
例如，当前代码中查找左边界时，right 最终会停在第一个大于等于 target 的位置，因此返回 right。
返回 left 的情况：
当查找的是 右边界 时，left 通常指向最后一个满足条件的位置。
例如，当前代码中查找右边界时，left 最终会停在最后一个小于等于 target 的位置，因此返回 left。
总结：
左边界：返回 right，因为 right 是第一个满足条件的位置。
右边界：返回 left，因为 left 是最后一个满足条件的位置。









