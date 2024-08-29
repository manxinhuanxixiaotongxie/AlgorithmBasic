package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Code015 {

    /**
     * 二元组问题怎么解决？
     * 1.将原始数组进行排序
     * 2.指定双指针L和R，分别指向数组的头和尾
     * 3.如果nums[L] + nums[R] > target，则R--
     * 4.如果nums[L] + nums[R] < target，则L++
     * 5.如果nums[L] + nums[R] == target，则L++，R--，并且将结果加入到结果集中
     * <p>
     * 这个过程其实还有一个隐式转换：
     * 如果nums[L]+num[R]=target的话 那么nums[L]+nums[R+1]一定是不满足条件的
     * 如果nums[L]+num[R]=target的话 那么nums[L-1]+nums[R]一定是不满足条件的
     * <p>
     * <p>
     * 三元组问题怎么解决？
     * <p>
     * 1.将原始数组进行排序
     * 2.指定一个指针i，从数组的最后一个元素开始遍历
     * 3.对于每一个i，我们都可以将问题转化为一个二元组问题
     * 4.对于每一个i，我们都可以在i的左边找到一个L和R，使得nums[L]+nums[R]=-nums[i]
     * 5.这个过程和二元组问题是一样的，只不过我们需要在i的左边找到一个L和R
     * 6.我们可以将问题转化为一个二元组问题，然后在i的左边找到一个L和R
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        int N = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = N - 1; i > 1; i--) {
            if (i == N - 1 || nums[i] != nums[i + 1]) {
                List<List<Integer>> nexts = twoSum2(nums, i - 1, -nums[i]);
                for (List<Integer> cur : nexts) {
                    cur.add(nums[i]);
                    ans.add(cur);
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> twoSum2(int[] nums, int end, int target) {
        int L = 0;
        int R = end;
        List<List<Integer>> ans = new ArrayList<>();
        while (L < R) {
            if (nums[L] + nums[R] > target) {
                R--;
            } else if (nums[L] + nums[R] < target) {
                L++;
            } else {
                if (L == 0 || nums[L - 1] != nums[L]) {
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[L]);
                    cur.add(nums[R]);
                    ans.add(cur);
                }
                L++;
            }
        }
        return ans;
    }


    public static List<List<Integer>> threeSum3(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // 先将数组进行排序
        Arrays.sort(nums);
        // 针对每个位置进行选择
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                // 选定当前数之后 在后面的数进行选择
                int L = i + 1;
                int R = nums.length - 1;
                // 定义剩余的数
                int rest = -nums[i];
                while (L < R) {
                    if (nums[L] + nums[R] > rest) {
                        R--;
                    } else if (nums[L] + nums[R] < rest) {
                        L++;
                    } else {
                        // 相等的话 进行结算
                        if (L == i + 1 || nums[L] != nums[L - 1]) {
                            List<Integer> cur = new ArrayList<>();
                            cur.add(nums[i]);
                            cur.add(nums[L]);
                            cur.add(nums[R]);
                            ans.add(cur);
                        }
                        L++;
                    }
                }
            }
        }
        return ans;
    }

    public static List<List<Integer>> threeSum4(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // 先将数组进行排序
        Arrays.sort(nums);
        // 针对每个位置进行选择
        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                // 选定当前数之后 在后面的数进行选择
                int L = i + 1;
                int R = nums.length - 1;
                // 定义剩余的数
                int rest = -nums[i];
                while (L < R) {
                    if (nums[L] + nums[R] > rest) {
                        R--;
                    } else if (nums[L] + nums[R] < rest) {
                        L++;
                    } else {
                        // 相等的话 进行结算
                        if (R == nums.length - 1 || nums[R] != nums[R + 1]) {

                            List<Integer> cur = new ArrayList<>();
                            cur.add(nums[i]);
                            cur.add(nums[L]);
                            cur.add(nums[R]);
                            ans.add(cur);
                        }
                        R--;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 0, 0};
        List<List<Integer>> ans = threeSum3(nums);
        for (List<Integer> cur : ans) {
            System.out.println(cur);
        }
    }
}
