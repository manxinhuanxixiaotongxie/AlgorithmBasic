package leetcode.week.code453;

import java.util.Arrays;

/**
 * 给你一个大小为 n 的整数数组 nums，其中只包含 1 和 -1，以及一个整数 k。
 * <p>
 * 你可以最多进行 k 次以下操作：
 * <p>
 * 选择一个下标 i（0 <= i < n - 1），然后将 nums[i] 和 nums[i + 1] 同时 乘以 -1。
 * <p>
 * 注意：你可以在 不同 的操作中多次选择相同的下标 i。
 * <p>
 * 如果在最多 k 次操作后可以使数组的所有元素相等，则返回 true；否则，返回 false。
 */
public class Code01 {

    public boolean canMakeEqual(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        return process(nums, 0, k, 1) || process(nums, 0, k, -1);
    }

    /**
     * 从index位置出发进行选择 只要有一个位置满足条件
     * 那么说明是可以进行转换的
     */
    public boolean process(int[] nums, int index, int restTimes, int target) {
        if (index == nums.length - 1) {
            if (restTimes < 0) {
                return false;
            } else {
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] != target) {
                        return false;
                    }
                }
                return true;
            }
        } else {
            boolean ans = false;
            for (int i = index; i < nums.length - 1; i++) {
                if (nums[i] == target) {
                    // 当前位置不做选择
                    ans |= process(nums, i + 1, restTimes, target);
                } else {
                    // 当前位置需要变动
                    nums[i] *= -1;
                    nums[i + 1] *= -1;
                    ans |= process(nums, i + 1, restTimes - 1, target);
                    nums[i] *= -1;
                    nums[i + 1] *= -1;
                }
            }
            return ans;
        }
    }

    /**
     * 贪心
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canMakeEqual2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return true;
        }
        // 两种情况
        // 第一种情况 数组所有元素全部都变成1
        // 第二种情况 数组所有的元素搜变成-1
        int[] copy = Arrays.copyOf(nums, nums.length);
        // 为什么要进行copy
        return check(nums, 1, k) || check(copy, -1, k);
    }

    public boolean check(int[] nums, int target, int restTimes) {
        boolean ans = true;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != target) {
                // 不想等的时候 就需要进行变化
                nums[i] *= -1;
                nums[i + 1] *= -1;
                restTimes--;
            }
        }
        if (restTimes < 0) {
            return false;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) {
                ans = false;
                break;
            }
        }
        return ans;
    }

    public boolean canMakeEqual3(int[] nums, int k) {
        return check3(nums, k, -1) || check3(nums, k, 1);
    }

    private boolean check3(int[] nums, int k, int target) {
        int n = nums.length;
        int left = k;
        int mul = 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] * mul == target) {
                mul = 1; // 不操作，下一个数不用乘 -1
                continue;
            }
            if (left == 0 || i == n - 1) {
                return false;
            }
            left--;
            mul = -1; // 操作，下一个数要乘 -1
        }
        return true;
    }

    public int[] generateArr(int maxLength) {
        int[] res = new int[(int) Math.random() * (maxLength + 1)];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 1 : -1;
        }
        return res;
    }

    public int[] copy(int[] arr) {
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }


    public static void main(String[] args) {
        Code01 code01 = new Code01();
        int testTimes = 500000;
        int maxLength = 100;
        int k = (int) Math.random() * 10 + 1;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = code01.generateArr(maxLength);
            int[] copy1 = code01.copy(arr);
            int[] copy2 = code01.copy(arr);
            int[] copy3 = code01.copy(arr);
            boolean ans1 = code01.canMakeEqual(copy1, k);
            boolean ans2 = code01.canMakeEqual2(copy2, k);
            boolean ans3 = code01.canMakeEqual3(copy3, k);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
                System.out.println("ans1 is " + ans1);
                System.out.println("ans2 is " + ans2);
                System.out.println("ans3 is " + ans3);
                break;
            }
        }
        System.out.println("test end");
    }
}
