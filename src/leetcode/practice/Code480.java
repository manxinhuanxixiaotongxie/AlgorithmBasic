package leetcode.practice;

import java.util.Comparator;

public class Code480 {

    /**
     * 给定K 长度为K的子数组的中位数
     *
     * @param nums
     * @param k
     * @return
     */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return new double[0];
        }
        MySBTTree mySBTTree = new MySBTTree();
        for (int i = 0; i < k - 1; i++) {
            mySBTTree.put(new Node(i, nums[i]));
        }
        double[] ans = new double[nums.length - k + 1];
        int index = 0;
        for (int i = k - 1; i < nums.length; i++) {
            mySBTTree.put(new Node(i, nums[i]));
            if (k % 2 == 0) {
                int upmid = mySBTTree.findKNum(k / 2 - 1);
                int downmid = mySBTTree.findKNum(k / 2);
                ans[index++] = ((double) upmid + (double) downmid) / 2;
            } else {
                double mid = mySBTTree.findKNum(k / 2);
                ans[index++] = mid;
            }
            mySBTTree.remove(i - k + 1, nums[i - k + 1]);
        }
        return ans;
    }
}

/**
 * 节点的属性设置很关键
 * 这个题目要求的是中位数 那么已K为长度的子数组 必须要出一个 然后进一个
 * 出的位置是数组的i位置 那么进的位置是i+k位置
 * 那么就要求根据i位置能快速找到节点的位置
 * 并且由于窗口的数组可能会重复 那么我们对于排序的要求就会变的不一样
 * 将题目转化一下 求每个窗口的中位数 我们现在维护一个SIZE为k的有序表
 * 如果k是偶数的话 那么这个中位数就转化层找到第k/2-1 和k/2个数 将这两个数相减
 * 如果K是奇数的话 那么就是找到第k/2个数
 */
class Node implements Comparator<Node> {
    int key;
    int value;
    Node left;
    Node right;
    int size;

    Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.size = 1;
    }

    /**
     * 这里比较为什么要使用compareTo方法 是因为相减的时候会超过整型最大值
     *
     * @param o1 the first object to be compared.
     * @param o2 the second object to be compared.
     * @return
     */
    @Override
    public int compare(Node o1, Node o2) {
//        return o1.value != o2.value ? Integer.valueOf(o1.value) - Integer.valueOf(o2.value) : o1.key - o2.key;
        return o1.value != o2.value ? Integer.compare(o1.value, o2.value) : Integer.compare(o1.key, o2.key);
    }
}

class MySBTTree {
    private Node root;
    private int size;

    /**
     * 左旋
     *
     * @param cur
     * @return
     */
    public Node leftRotate(Node cur) {
        if (cur == null) {
            return null;
        }
        Node right = cur.right;
        cur.right = right.left;
        right.left = cur;
        right.size = cur.size;
        cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
        return right;
    }

    /**
     * 右旋
     *
     * @param cur
     * @return
     */
    public Node rightRotate(Node cur) {
        if (cur == null) {
            return null;
        }
        Node left = cur.left;
        cur.left = left.right;
        left.right = cur;
        left.size = cur.size;
        cur.size = (cur.left == null ? 0 : cur.left.size) + (cur.right == null ? 0 : cur.right.size) + 1;
        return left;
    }

    public void put(Node node) {
        root = add(root, node);
    }

    public Node add(Node cur, Node node) {
        if (cur == null) {
            return node;
        } else {
            cur.size++;
            if (cur.compare(cur, node) == 0) {
                return cur;
            } else {
                if (cur.compare(cur, node) > 0) {
                    cur.left = add(cur.left, node);
                } else {
                    cur.right = add(cur.right, node);
                }
                return maintain(cur);
            }
        }
    }

    public Node maintain(Node cur) {
        if (cur == null) {
            return null;
        }
        int leftSize = cur.left == null ? 0 : cur.left.size;
        int rightSize = cur.right == null ? 0 : cur.right.size;
        int leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
        int leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
        int rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
        int rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;

        // 如果是LL型的违规 需要右旋
        if (leftLeftSize > rightSize) {
            cur = rightRotate(cur);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (leftRightSize > rightSize) {
            // 先左旋再右旋
            cur.left = leftRotate(cur.left);
            cur = rightRotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (rightRightSize > leftSize) {
            cur = leftRotate(cur);
            cur.left = maintain(cur.left);
            cur = maintain(cur);
        } else if (rightLeftSize > leftSize) {
            cur.right = rightRotate(cur.right);
            cur = leftRotate(cur);
            cur.left = maintain(cur.left);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        }
        return cur;
    }

    // 这个SBT需要实现一个功能删除节点的功能
    public void remove(int key, int value) {
        root = delete(root, new Node(key, value));
    }

    public Node delete(Node cur, Node node) {
        if (cur == null) {
            return null;
        }
        cur.size--;
        if (cur.compare(cur, node) < 0) {
            cur.right = delete(cur.right, node);
        } else if (cur.compare(cur, node) > 0) {
            cur.left = delete(cur.left, node);
        } else {
            if (cur.left == null && cur.right == null) {
                cur = null;
            } else if (cur.left == null && cur.right != null) {
                cur = cur.right;
            } else if (cur.left != null && cur.right == null) {
                cur = cur.left;
            } else {
                Node pre = null;
                Node des = cur.right;
                des.size--;
                while (des.left != null) {
                    pre = des;
                    des = des.left;
                    des.size--;
                }
                // 经过这步之后 cur已经来到了右树的最左侧节点 pre是上一个节点
                if (pre != null) {
                    pre.left = des.right;
                    des.right = cur.right;
                }
                des.left = cur.left;
                des.size = cur.size;
                cur = des;
            }
        }
        return cur;
    }

    /**
     * 找第K个数
     *
     * @return
     */
    public int findKNum(int k) {
        return findKNode(root, k + 1).value;
    }

    public Node findKNode(Node cur, int k) {
        int leftSize = cur.left == null ? 0 : cur.left.size;
        if (leftSize + 1 == k) {
            return cur;
        } else if (leftSize < k) {
            return findKNode(cur.right, k - leftSize - 1);
        } else {
            return findKNode(cur.left, k);
        }
    }

    public static void main(String[] args) {
//        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
//        int[] nums = {7,0,3,9,9,9,1,7,2,3};
//        int[] nums = {7,0,3,9,9,9,1,7,2,3};
        int[] nums = {-2147483648, -2147483648, 2147483647, -2147483648, 1, 3, -2147483648, -100, 8, 17, 22, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648};
        int k = 6;
        double[] ans = Code480.medianSlidingWindow(nums, k);
        for (double an : ans) {
            System.out.println(an);
        }
    }
}
