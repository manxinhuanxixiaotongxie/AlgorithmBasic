package leetcode.practice;

public class Code0287 {
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while(slow != fast) {
            fast = nums[nums[fast]];
            slow = nums[slow];
        }
        // 两者相等之后
        fast = 0;
        while(slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        Code0287 code0287 = new Code0287();
        int[] nums = {1,3,4,2,2};
        System.out.println(code0287.findDuplicate(nums));
    }
}
