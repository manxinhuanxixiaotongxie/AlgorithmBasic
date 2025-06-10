package systemimprove.code25;

/**
 * 给定一个无序数组arr中，长度为N，给定一个正数k，返回top k个最大的数
 *
 * 不同时间复杂度三个方法：
 *
 * 1）O(N*logN)
 * 对整个数组进行排序
 *
 * 2）O(N + K*logN)
 *
 * 采用由底向上的建堆方式 参考heapSort的建堆方式
 *
 * 3）O(n + k*logk)
 *
 * 先求第nums.length - k + 1小的数
 * 再遍历一次数组 只有比这个数大的数才放入到一个小根堆中
 * 再针对这个K个数进行排序
 *
 *
 *
 */
public class Code03_FindMostKNum {
}
