package codeforgreat.code01;

/**
 * 给定一个非负整数num，
 * 如何不用循环语句，
 * 返回>=num，并且离num最近的，2的某次方
 */
public class Code03_FindNearestNum {

    /**
     * 使用循环的方式
     *
     * @return
     */
    public int findNearestNum(int n) {
        int count = 0;
        int cur = n;
        int ans = 0;
        while (cur != 0) {
            int rightOne = cur & (~cur + 1);
            count++;
            int temp = rightOne;
            cur ^= rightOne;
            if (cur == 0) {
                ans = temp;
                break;
            }
        }
        if (count == 1) {
            // 只有一个 1 那么 意味着这个数刚好是2的某次方
            return ans;
        } else {
            // 不止一个1
            return ans << 1;
        }
    }

    public static void main(String[] args) {
        Code03_FindNearestNum code03_findNearestNum = new Code03_FindNearestNum();
        System.out.println(code03_findNearestNum.findNearestNum(17));
    }
}
