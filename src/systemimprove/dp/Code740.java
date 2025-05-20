package systemimprove.dp;

import java.util.HashSet;

/**
 * 给你一个整数数组 nums ，你可以对它进行一些操作。
 * <p>
 * 每次操作中，选择任意一个 nums[i] ，删除它并获得 nums[i] 的点数。之后，你必须删除 所有 等于 nums[i] - 1 和 nums[i] + 1 的元素。
 * <p>
 * 开始你拥有 0 个点数。返回你能通过这些操作获得的最大点数。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,2]
 * 0 1 2
 * 输出：6
 * 解释：
 * 删除 4 获得 4 个点数，因此 3 也被删除。
 * 之后，删除 2 获得 2 个点数。总共获得 6 个点数。
 * <p>
 * 输入：nums = [2,2,3,3,3,4]
 * 0 1 2 3 4 5
 * 输出：9
 * 解释：
 * 删除 3 获得 3 个点数，接着要删除两个 2 和 4 。
 * 之后，再次删除 3 获得 3 个点数，再次删除 3 获得 3 个点数。
 * 总共获得 9 个点数。
 */
public class Code740 {

    public static void main(String[] args) {
//        int[] nums = {2, 2, 3, 3, 3, 4};
        int[] nums = {3, 4, 2};
        Code740 code740 = new Code740();
        System.out.println(code740.deleteAndEarn(nums));
        System.out.printf("deleteAndEarn2:%d", code740.deleteAndEarn2(nums));
    }

    public int deleteAndEarn(int[] nums) {
        return process(nums);
    }

    /**
     * 这个暴力递归 会超时
     * <p>
     * 因为每次递归都会产生一个新的数组
     * 增加了时间复杂度
     *
     * @param nums
     * @return
     */
    private int process(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            // 先删除当前位置
            int[] delete = delete(nums, i);
            int cur = nums[i] + process(delete);
            ans = Math.max(ans, cur);
        }
        return ans;
    }

    public int deleteAndEarn2(int[] nums) {
        return process2(nums, new HashSet<>());
    }

/*    private int process2(int[] nums, HashSet<Integer> set) {
        // set存放下标
        if (set.size() >= nums.length) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            // 选出当前数
            int num = nums[i];
            // 3 4 2
            // 0 1 2
            // 选出当前数之后 再数组中找到所有等于num-1和num+1的数

            if (set.contains(i)) {
                continue;
            }

            set.add(i);
            for (int j = 0; j < nums.length; j++) {
                if (set.contains(j)) {
                    continue;
                }
                if (nums[j] == num - 1 || nums[j] == num + 1) {
                    set.add(j);
                }
            }
            // 这个递归跑不完 为什么会这样
            // 3 4 2
            // 0 1 2

            跑不完的根本原因是因为清空set之后
            无法回溯

            举个例子

            3 4 2  当递归2位置时  此时set已经被清空
            会从0位置开始递归  与最原始的递归重复了

            ans = Math.max(ans, num + process2(nums, set));
            set.clear();
        }
        // 3 4 2
        // 0 1 2
        //
        return ans;
    }*/

    private int process2(int[] nums, HashSet<Integer> set) {
        if (set.size() >= nums.length) {
            return 0;
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(i)) {
                continue;
            }

            int num = nums[i];
            HashSet<Integer> tempSet = new HashSet<>(set);
            tempSet.add(i);
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == num - 1 || nums[j] == num + 1) {
                    tempSet.add(j);
                }
            }

            ans = Math.max(ans, num + process2(nums, tempSet));
        }
        return ans;
    }

    private int[] delete(int[] nums, int index) {
        // 删除当前位置之后的数组
        int n = nums.length;
        int num = nums[index];
        // 要删除的第一个数
        int deleteLeft = num - 1;
        // 要删除的第二个数
        int deleteRight = num + 1;
        int[] res = new int[n];
        int j = 0;
        for (int i = index + 1; i < n; i++) {
            if (nums[i] == deleteLeft || nums[i] == deleteRight || i == index) {
                // 删除
            } else {
                res[j++] = nums[i];
            }
        }
        int[] ans = new int[j];
        System.arraycopy(res, 0, ans, 0, j);
        return ans;

    }
}
