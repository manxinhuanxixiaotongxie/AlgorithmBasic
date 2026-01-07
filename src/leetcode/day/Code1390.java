package leetcode.day;

/**
 * 给你一个整数数组 nums，请你返回该数组中恰有四个因数的这些整数的各因数之和。如果数组中不存在满足题意的整数，则返回 0 。
 *
 * 提示：
 *
 * 1 <= nums.length <= 10^4
 * 1 <= nums[i] <= 10^5
 *
 */
public class Code1390 {
    public int sumFourDivisors(int[] nums) {
        // 是不是非要求出每个数的因数
        // 什么样的数 才是因数 只有四个因数
        //  1-10   8  1 2 4 8  10 1 2 5 10
        //  11-20  14 1 2 7 14  15 1 3 5 15
        //  21-30  21 1 3 7 21  22 1 2 11 22 26 1 2 13 26 27 1 3 9 27
        //  31-40  33 1 3 11 33  34 1 2 17 34 35 1 5 7 35 38 1 2 19 38
        int ans = 0;
        // 判断一个数是不是因数
        for (int num : nums) {
            int count = 0;
            int sum = 0;
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    count++;
                    sum += i;
                    if (i * i != num) {
                        count++;
                        sum += num / i;
                    }
                }
                if (count > 4) {
                    break;
                }
            }
            if (count == 4) {
                ans += sum;
            }
        }


        return ans;
    }

    static void main() {
        Code1390 code1390 = new Code1390();
        int[] nums = {21,4,7};
        int ans = code1390.sumFourDivisors(nums);
        System.out.println(ans);
    }
}
