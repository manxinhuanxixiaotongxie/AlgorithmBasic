package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 与code560相同
 */
public class Code010 {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = 0;
        int sum = 0;
        Map<Integer, Integer> numMap = new HashMap<>();
        numMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            // 这个题目的求解思路 使用map记录前面出现过的前缀和等于num[i]-k的次数
            // 题目求的是有多少个子数组的和为K 意味着前面出现的前缀和为sum[i]-k的次数
            sum += nums[i];
            if (numMap.containsKey(sum - k)) {
                ans += numMap.get(sum - k);
            }
            numMap.put(sum, numMap.getOrDefault(sum, 0) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Code010 code010 = new Code010();
        int[] nums = {1, 2, 3};
        int k = 3;
        System.out.println(code010.subarraySum2(nums, k));
    }

    public int subarraySum2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = 0;
        int sum = 0;
        AVLGreater avlGreater = new AVLGreater();
        avlGreater.put(0);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += avlGreater.findNum(sum - k);
            avlGreater.put(sum);
        }
        return ans;
    }

    public int subarraySum3(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int ans = 0;
        int sum = 0;
        SbtTree sbtTree = new SbtTree();
        sbtTree.put(0);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            ans += sbtTree.findNum(sum - k);
            sbtTree.put(sum);
        }
        return ans;
    }

}

class AVLNode {
    int val;
    int height;
    int count;
    AVLNode left;
    AVLNode right;

    public AVLNode(int val) {
        this.val = val;
        this.height = 1;
        this.count = 1;
    }

}

class AVLGreater {
    AVLNode root;

    public void put(int val) {
        root = add(root, val, containsKey(val));
    }

    public AVLNode add(AVLNode cur, int val, boolean contains) {
        if (cur == null) {
            return new AVLNode(val);
        }
        cur.count++;
        if (cur.val == val) {
            return cur;
        }
        if (!contains) {
            cur.height++;
        }
        if (val < cur.val) {
            cur.left = add(cur.left, val, contains);
        } else {
            cur.right = add(cur.right, val, contains);
        }
        return maintain(cur);

    }

    public AVLNode maintain(AVLNode cur) {
        if (cur == null) {
            return cur;
        }
        int leftHeight = cur.left == null ? 0 : cur.left.height;
        int rightHeight = cur.right == null ? 0 : cur.right.height;
        int leftLeftHeight = cur.left != null && cur.left.left != null ? cur.left.left.height : 0;
        int leftRightHeight = cur.left != null && cur.left.right != null ? cur.left.right.height : 0;
        int rightLeftHeight = cur.right != null && cur.right.left != null ? cur.right.left.height : 0;
        int rightRightHeight = cur.right != null && cur.right.right != null ? cur.right.right.height : 0;
        if (leftHeight > rightHeight + 1) {
            if (leftLeftHeight >= leftRightHeight) {
                cur = rightRotate(cur);
            } else {
                cur.left = leftRotate(cur.left);
                cur = rightRotate(cur);
            }
        } else if (rightHeight > leftHeight + 1) {
            if (rightRightHeight > rightLeftHeight) {
                cur = leftRotate(cur);
            } else {
                cur.right = rightRotate(cur.right);
                cur = leftRotate(cur);
            }
        }
        return cur;
    }

    public int findNum(int val) {
        AVLNode cur = root;
        while (cur != null) {
            if (cur.val == val) {
                return cur.count - (cur.left == null ? 0 : cur.left.count) - (cur.right == null ? 0 : cur.right.count);
            } else if (cur.val < val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return 0;
    }

    public AVLNode leftRotate(AVLNode cur) {
        if (cur == null) {
            return cur;
        }
        int same = cur.count - (cur.left == null ? 0 : cur.left.count) - (cur.right == null ? 0 : cur.right.count);
        AVLNode right = cur.right;
        cur.right = right.left;
        right.left = cur;
        cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
        right.height = Math.max(right.left != null ? right.left.height : 0, right.right != null ? right.right.height : 0) + 1;
        right.count = cur.count;
        cur.count = same + (cur.left == null ? 0 : cur.left.count) + (cur.right == null ? 0 : cur.right.count);
        return right;
    }

    public AVLNode rightRotate(AVLNode cur) {
        if (cur == null) {
            return cur;
        }
        int same = cur.count - (cur.left == null ? 0 : cur.left.count) - (cur.right == null ? 0 : cur.right.count);
        AVLNode left = cur.left;
        cur.left = left.right;
        left.right = cur;
        cur.height = Math.max(cur.left != null ? cur.left.height : 0, cur.right != null ? cur.right.height : 0) + 1;
        left.height = Math.max(left.left != null ? left.left.height : 0, left.right != null ? left.right.height : 0) + 1;
        left.count = cur.count;
        cur.count = same + (cur.left == null ? 0 : cur.left.count) + (cur.right == null ? 0 : cur.right.count);
        return left;
    }

    public boolean containsKey(int val) {
        AVLNode cur = root;
        while (cur != null) {
            if (cur.val == val) {
                return true;
            } else if (cur.val < val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }

        return false;
    }
}


class SbtNode {
    public int value;
    public int size;
    public int all;
    SbtNode left;
    SbtNode right;

    public SbtNode(int value) {
        this.value = value;
        this.size = 1;
        this.all = 1;
    }
}

class SbtTree {
    private SbtNode root;

    public void put(int value) {
        root = add(root, value, containsKey(value));
    }

    public boolean containsKey(int value) {
        SbtNode cur = root;
        while (cur != null) {
            if (cur.value == value) {
                return true;
            } else if (cur.value < value) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return false;
    }

    public SbtNode add(SbtNode cur, int value, boolean contains) {
        if (cur == null) {
            return new SbtNode(value);
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

    public SbtNode maintain(SbtNode cur) {
        if (cur == null) {
            return cur;
        }
        int leftSize = cur.left == null ? 0 : cur.left.size;
        int rightSize = cur.right == null ? 0 : cur.right.size;
        int leftLeftSize = cur.left == null || cur.left.left == null ? 0 : cur.left.left.size;
        int leftRightSize = cur.left == null || cur.left.right == null ? 0 : cur.left.right.size;
        int rightRightSize = cur.right == null || cur.right.right == null ? 0 : cur.right.right.size;
        int rightLeftSize = cur.right == null || cur.right.left == null ? 0 : cur.right.left.size;
        // ll型违规 只需要进行右旋 右旋之后 新的cur节点以及cur的孩子发生变化 因为原来左树的右孩子会被新的cur的左树接管
        // 因此需要将cur的右树以及cur进行平衡性的调整 RR型的违规同理
        if (leftLeftSize > rightSize) {
            cur = rightRotata(cur);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (leftRightSize > rightSize) {
            // lr型的违规  需要将当前树的左树进行左旋 左旋之后将当前数进行右旋
            // 进行了这个过程之后 新cur的左右两个孩子都会发生变化 需要进行调整 SBT树的调整是孩子节点发生了变化  那么整个树都需要进行平衡性的调整
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
            cur.right = maintain(cur.right);
            cur.left = maintain(cur.left);
            cur = maintain(cur);
        }
        return cur;
    }

    public SbtNode leftRotata(SbtNode cur) {
        if (cur == null) {
            return cur;
        }
        int same = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
        SbtNode right = cur.right;
        cur.right = right.left;
        right.left = cur;
        cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
        right.size = (right.left == null ? 0 : right.left.size) + (right.right == null ? 0 : right.right.size) + 1;
        right.all = cur.all;
        cur.all = same + (cur.left == null ? 0 : cur.left.all) + (cur.right == null ? 0 : cur.right.all);
        return right;
    }

    public SbtNode rightRotata(SbtNode cur) {
        if (cur == null) {
            return cur;
        }
        int same = cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
        SbtNode left = cur.left;
        cur.left = left.right;
        left.right = cur;
        cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
        left.size = (left.left == null ? 0 : left.left.size) + (left.right == null ? 0 : left.right.size) + 1;
        left.all = cur.all;
        cur.all = same + (cur.left == null ? 0 : cur.left.all) + (cur.right == null ? 0 : cur.right.all);
        return left;
    }

    public int findNum(int val) {
        SbtNode cur = root;
        while (cur != null) {
            if (cur.value == val) {
                return cur.all - (cur.left == null ? 0 : cur.left.all) - (cur.right == null ? 0 : cur.right.all);
            } else if (cur.value < val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return 0;
    }

}
