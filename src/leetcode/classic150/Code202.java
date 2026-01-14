package leetcode.classic150;

import java.util.HashSet;
import java.util.Set;

/**
 * 快乐数
 *
 */
public class Code202 {
    // 次优解
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            if (set.contains(n)) {
                return false;
            }
            set.add(n);
            n = getNext(n);
        }
         return true;
    }

    /**
     * 最优解
     *
     * 快慢指针  环形链表
     *
     * @param n
     * @return
     */
    public boolean isHappy2(int n) {
        int slow = getNext(n);
        int fast = getNext(getNext(n));
        while (slow != fast) {
            if (fast == 1) return true;
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return slow == 1;
    }

    public int getNext(int n) {
        // 获取下一个数字
        String string = Integer.toString(n);
        char[] chars = string.toCharArray();
        int sum = 0;
        for (char aChar : chars) {
            int num = Character.getNumericValue(aChar);
            sum += num * num;
        }
        return sum;
    }
}
