package leetcode.week.code427;

import java.util.HashMap;
import java.util.Map;

public class Code002 {
    public int getLargestOutlier(int[] nums) {
        // 假设i位置就是最大异常数
        int ans = Integer.MIN_VALUE;
        int sum = nums[0];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(nums[0], 1);
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        // 如果i位置是异常数的话   那么必然存在某一个数的两倍等于sum-nums[i]
        for (int i = 0; i < nums.length; i++) {
            int rest = sum - nums[i];
            // 假设i位置是异常数 怎么快速获取是不是满足条件
            // 2x+y == sum 如果x不是异常数的话 那么一定存在这种关系
            if (map.containsKey(rest - nums[i]) && (rest - nums[i] != nums[i] || map.get(rest - nums[i]) > 1 )) {
                ans = Math.max(ans, rest - nums[i]);
                map.put(nums[i], map.get(nums[i]) - 1);
            }

        }
        return ans;
    }

    private boolean isTrue(int[] nums, int index, int sum) {
        // index位置的数是否可以作为异常的数
        boolean ans = false;
        int tempSum = sum - nums[index];
        for (int i = 0; i < nums.length; i++) {
            if (i != index && tempSum == (nums[i] << 1)) {
                return true;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code002 solution = new Code002();
//        System.out.println(solution.getLargestOutlier(new int[]{2, 3, 5, 10}));
//        System.out.println(solution.getLargestOutlier(new int[]{-310,-702,-702}));
        System.out.println(solution.getLargestOutlier(new int[]{6, -31, 50, -35, 41, 37, -42, 13}));

        //[-108,-108,-517]
//        System.out.println(solution.getLargestOutlier(new int[]{-108, -108, -517}));
//        System.out.println(solution.getLargestOutlier(new int[]{1, 2, 3, 4, 6}));
    }
}
