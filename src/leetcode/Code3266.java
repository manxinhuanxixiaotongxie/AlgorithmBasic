package leetcode;

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
            newNums[poll.index] = (poll.val * multiplier)% 1000000007;
            queue.add(new Info(newNums[poll.index], poll.index));
            k--;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = (int) (newNums[i]);
        }
        return nums;
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
        int[] finalState = code3266.getFinalState(new int[]{161209470}, 56851412, 39846);
        for (int i : finalState) {
            System.out.println(i);
        }
    }
}
