package leetcode.practice;

import java.util.PriorityQueue;

public class Code3266 {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        long[] newNums = new long[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newNums[i] = nums[i];
        }
        PriorityQueue<Info> queue = new PriorityQueue<>((o1, o2) -> o1.val == o2.val ? Long.compare(o1.index, o2.index) : Long.compare(o1.val, o2.val));
        for (int i = 0; i < nums.length; i++) {
            Info info = new Info(newNums[i], i);
            queue.add(info);
        }
        while (k > 0) {
            if (queue.isEmpty()) {
                break;
            }
            Info poll = queue.poll();
            newNums[poll.index] = (poll.val * multiplier) % 1000000007;
            queue.add(new Info(newNums[poll.index], poll.index));
            k--;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (newNums[i]);
        }
        return nums;
    }

    private static final int MOD = 1_000_000_007;

    /**
     * [3,4,5,6] k =2 multiplier = 2
     *
     * @param nums
     * @param k
     * @param multiplier
     * @return
     */
    public int[] getFinalState2(int[] nums, int k, int multiplier) {
        if (multiplier == 1) { // 数组不变
            return nums;
        }

        int n = nums.length;
        int mx = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? Long.compare(a[0], b[0]) : Long.compare(a[1], b[1]));
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            pq.offer(new long[]{nums[i], i});
        }

        // 模拟，直到堆顶是 mx
        for (; k > 0 && pq.peek()[0] < mx; k--) {
            long[] p = pq.poll();
            p[0] *= multiplier;
            pq.offer(p);
        }

        // 剩余的操作可以直接用公式计算
        // 这是在遍历堆 不是在遍历数组
        for (int i = 0; i < n; i++) {
            long[] p = pq.poll();
            // 堆的size一定与数组长度是一样的
            // 我们在对堆处理的过程中 已经保证了堆顶一定是最小的 也就是原始的最大的mx
            nums[(int) p[1]] = (int) (p[0] % MOD * pow(multiplier, k / n + (i < k % n ? 1 : 0)) % MOD);
        }
        return nums;
    }

    public int[] getFinalState3(int[] nums, int k, int multiplier) {
        if (multiplier == 1) { // 数组不变
            return nums;
        }

        int n = nums.length;
        int mx = 0;
        PriorityQueue<Info> queue = new PriorityQueue<>((o1, o2) -> o1.val == o2.val ? Long.compare(o1.index, o2.index) : Long.compare(o1.val, o2.val));
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, nums[i]);
            Info info = new Info(nums[i], i);
            queue.add(info);
        }
        while (k > 0 && queue.peek().val < mx) {
            Info poll = queue.poll();
            poll.val = poll.val * multiplier;
            queue.add(poll);
            k--;
        }

        // 假设数组的长度是5  k=10
        // 经历过上述步骤之后总共可以被消费的次数还剩下10-5-1=6次
        // mx位置还可以消费restK/n  +1次
        // 剩下的位置还可以消费restK/n次
        // 如果k比较小  经过上述步骤之后k已经来到了0位置
        // 那么剩下的数都不能被消费
        // 对堆进行遍历
        for (int i = 0; i < n; i++) {
            Info info = queue.poll();
            // 弹出一个数
            // 按照刚刚的讨论
            // 堆中k/n之后的前k%n个数可以获得k/n +1的幂
            // 其余的都是k/n次
            nums[info.index] = (int) (info.val % MOD * pow2(multiplier, k / n + (i < k % n ? 1 : 0)) % MOD);
        }


        return nums;
    }

    private long pow2(int x, int n) {
        int temp = x;
        int pow = n;
        long ans = 1;
        while (pow != 0) {
            if ((pow & 1) == 1) {
                ans = ans * temp % MOD;
            }
            temp = temp * temp % MOD;
            pow >>= 1;
        }
        return ans;
    }

    private long pow(long x, int n) {
        long res = 1;
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = res * x % MOD;
            }
            x = x * x % MOD;
        }
        return res;
    }


    class Info {
        long val;
        int index;

        public Info(long val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Code3266 code3266 = new Code3266();
        int[] finalState = code3266.getFinalState3(new int[]{1}, 3, 10);
        for (int i : finalState) {
            System.out.println(i);
        }
    }
}
