package leetcode.practice;

public class Code0027 {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] partition = partition(nums, val);
        int L = partition[0];
        int R = partition[1];
        int L1 = R + 1;
        while (L1 < nums.length) {
            nums[L++] = nums[L1++];
        }
        return nums.length - (partition[1] - partition[0] + 1);

    }

    private int[] partition(int[] nums, int val) {
        int L = -1, i = 0;
        int R = nums.length;
        while (i < R) {
            if (nums[i] < val) {
                swap(nums, ++L, i++);
            } else if (nums[i] > val) {
                swap(nums, i, --R);
            } else {
                i++;
            }
        }
        return new int[]{L + 1, R - 1};
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        Code0027 solution = new Code0027();
        int[] nums = new int[]{3, 2, 2, 3};
        // [0,1,2,2,3,0,4,2]
//        int[] nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        int val = 3;
        System.out.println(solution.removeElement(nums, val));
    }

}
