package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class Code560 {

    public static void main(String[] args) {
        Code560 code560 = new Code560();
        int[] nums = {1, 1, 1};
        int k = 2;
        System.out.println(code560.subarraySum(nums, k));
    }

    /**
     * 使用SBT实现：
     * 思路：
     * 求实和为K的子数组的数量
     * 就是求以i位置结尾的满足条件的有多少
     * 那么进而转化成从0开始到i位置 有多少个子数组的和为sum[i]-k
     * @param nums
     * @param k
     * @return
     */
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        SBTTree tree = new SBTTree();
        tree.put(0);
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += tree.fineNums(sum - k);
            tree.put(sum);
        }
        return ans;
    }

    public int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 缓存前缀和
        /**
         * 为什么要缓存呢？我们在这里做一下转化
         * 以i结尾的前缀和为sum[i]，如果要找到有多少个子数组的和为K 那么可以转化成前面有多少个前缀和为sum[i]-k
         */
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 1);
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumMap.containsKey(sum - k)) {
                ans += sumMap.get(sum - k);
            }
            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
        }
        return ans;

    }
}




class SBTTree {
    private SBTNode root;
    private HashSet<Integer> set = new HashSet();

    public void put(int value) {
        root = add(root, value, set.contains(value));
        set.add(value);
    }

    public SBTNode add(SBTNode cur, int value, boolean contains) {
        if (cur == null) {
            return new SBTNode(value);
        } else {
            cur.all++;
            if (value == cur.value) {
                return cur;
            }
            if (!contains) {
                cur.size++;
            }
            if (value < cur.value) {
                cur.left = add(cur.left, value, contains);
            } else if (value > cur.value) {
                cur.right = add(cur.right, value, contains);
            }
        }
        return maintain(cur);
    }

    public SBTNode maintain(SBTNode cur) {
        if (cur == null) {
            return null;
        }
        int leftSize = cur.left == null ? 0 : cur.left.size;
        int rightSize = cur.right == null ? 0 : cur.right.size;
        int leftLeftSize = cur.left == null || cur.left.left == null ? 0 : cur.left.left.size;
        int leftRightSize = cur.left == null || cur.left.right == null ? 0 : cur.left.right.size;
        int rightRightSize = cur.right == null || cur.right.right == null ? 0 : cur.right.right.size;
        int rightLeftSize = cur.right == null || cur.right.left == null ? 0 : cur.right.left.size;
        if (leftLeftSize > rightSize) {
            cur = rightRotata(cur);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (leftRightSize > rightSize) {
            cur.left = leftRotata(cur.left);
            cur = rightRotata(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (rightRightSize > leftSize) {
            cur = leftRotata(cur);
            cur.left = maintain(cur.left);
            cur = maintain(cur);
        } else if (rightLeftSize > leftSize) {
            cur.right = rightRotata(cur.right);
            cur = leftRotata(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);

        }
        return cur;
    }

    public SBTNode leftRotata(SBTNode cur) {
        int same = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
        SBTNode right = cur.right;
        cur.right = right.left;
        right.left = cur;
        right.all = cur.all;
        cur.all = same + (cur.left == null ? 0 : cur.left.all) + (cur.right == null ? 0 : cur.right.all);
        right.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        return right;

    }

    public SBTNode rightRotata(SBTNode cur) {
        int same = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
        SBTNode left = cur.left;
        cur.left = left.right;
        left.right = cur;
        left.all = cur.all;
        cur.all = same + (cur.left == null ? 0 : cur.left.all) + (cur.right == null ? 0 : cur.right.all);
        left.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        return left;
    }

    public int fineNums(int value) {
        SBTNode cur = root;
        while (cur != null) {
            if (value == cur.value) {
                return cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
            } else if (value < cur.value) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return 0;

    }
}

class SBTNode {
    public int value;
    public int size;
    public int all;
    SBTNode left;
    SBTNode right;

    public SBTNode(int value) {
        this.value = value;
        this.size = 1;
        this.all = 1;
    }
}
