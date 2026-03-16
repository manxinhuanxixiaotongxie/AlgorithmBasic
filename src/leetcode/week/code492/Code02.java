package leetcode.week.code492;

/**
 * 给你一个整数数组 nums。
 * <p>
 * 如果某个下标 i 满足以下条件，则称其为 平衡下标： i 左侧所有元素的总和等于 i 右侧所有元素的乘积。
 * <p>
 * 如果左侧没有元素，则总和视为 0。同样，如果右侧没有元素，则乘积视为 1。
 * <p>
 * 要求返回最小的 平衡下标，如果不存在平衡下标，则返回 -1。
 *
 */
public class Code02 {


    /**
     * 注意超出long最大值
     *
     * @param nums
     * @return
     */
    public int smallestBalancedIndex(int[] nums) {
        int N = nums.length;
        if (N == 0) return 0;
        if (N == 1) return -1;

        long[] helpSum = new long[N];
        long[] helpMul = new long[N];
        helpSum[0] = 0;
        helpMul[N - 1] = 1;
        // 先求和
        for (int i = 1; i < N - 1; i++) {
            helpSum[i] = nums[i-1] + helpSum[i - 1];
            // 转换下标
//            helpMul[N - i - 1] = nums[N - i] * helpMul[N - i];
            // 为什么要这么处理 是为了防止乘积超过long的最大值 直接标记为-1
            helpMul[N - i - 1] = nums[N-i] > Long.MAX_VALUE / Math.abs(helpMul[N - i]) ? -1 : nums[N - i] * helpMul[N - i];
        }

        helpSum[N - 1] = nums[N - 2] + helpSum[N - 2];
        helpMul[0] = nums[1] * helpMul[1];
        // 再求平衡下标
        for (int i = 0; i < N; i++) {
            if (helpSum[i] == helpMul[i]) {
                return i;
            }
        }
        return -1;
    }

    public int smallestBalancedIndex2(int[] nums) {
        int N = nums.length;
        if (N == 0) return 0;
        if (N == 1) return -1;
        long[] helpSum = new long[N];
        helpSum[0] = 0;
        for (int i = 1; i < N; i++) {
            helpSum[i] = nums[i - 1] + helpSum[i - 1];
        }
        // 统计后缀0的个数和后缀乘积
        int[] zeroCnt = new int[N + 1]; // zeroCnt[i]表示i及其右侧0的个数
        long[] mul = new long[N + 1];   // mul[i]表示i及其右侧所有非0数的乘积
        mul[N] = 1;
        zeroCnt[N] = 0;
        for (int i = N - 1; i >= 0; i--) {
            zeroCnt[i] = zeroCnt[i + 1] + (nums[i] == 0 ? 1 : 0);
            if (nums[i] == 0) {
                mul[i] = mul[i + 1];
            } else {
                if (mul[i + 1] == 0) {
                    mul[i] = nums[i];
                } else if (mul[i + 1] > Long.MAX_VALUE / Math.abs(nums[i])) {
                    mul[i] = -1; // 标记溢出
                } else {
                    mul[i] = mul[i + 1] * nums[i];
                }
            }
        }
        for (int i = 0; i < N; i++) {
            long leftSum = helpSum[i];
            // 右侧没有元素
            if (i == N - 1) {
                if (leftSum == 1) return i;
                continue;
            }
            // 右侧全是0
            if (zeroCnt[i + 1] == N - i - 1) {
                if (leftSum == 0) return i;
                continue;
            }
            // 右侧有0但不全是0
            if (zeroCnt[i + 1] > 0) continue;
            // 右侧无0，且乘积未溢出
            if (mul[i + 1] != -1 && leftSum == mul[i + 1]) return i;
        }
        return -1;
    }

    static void main() {
        Code02 code = new Code02();
        int[] nums = {813,974,946,966,915,924,812,1000,891,875,989,656,991,806,818,999,971,276,923,997,992,943,983,811,909,990,924,991,726,818,969,690,996,784,992,949,915,931,932,821,699,688,712,805,849,489,406,482,777,974,479,237,963,903,957,995,814,864,832,889,936,467,831,970,757,646,962,987,885,924,918,710,763,839,860,888,971,994,339,253,564,759,68,747,797,716,939,987,68,953,1000,298,10,1,1,1,1,1,48,1,77,2};
        System.out.println(code.smallestBalancedIndex(nums));
    }
}
