package systemreview.code04;

/**
 * 题目描述：
 * <p>
 * https://leetcode.com/problems/count-of-range-sum/
 * <p>
 * 给定一个数组arr，两个整数lower和upper，
 * <p>
 * 返回arr中有多少个子数组的累加和在[lower,upper]范围上
 */
public class Code03_CountOfRange {

    public int countRangeSum(int[] arr, int lower, int upper) {
        if (arr == null || arr.length == 0 || upper < lower) {
            return 0;
        }
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        // 以i开头有多少个子数组满足条件
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                // i-j的累加和是多少
                int curSum = sum[j] - sum[i] + arr[i];
                if (curSum >= lower && curSum <= upper) {
                    ans++;

                }
            }
        }
        return ans;
    }

    /**
     * 这里提供两种最优解
     * 1.利用归并排序
     * 2.改写有序表
     */
    public int countRangeSum2(int[] arr, int lower, int upper) {
        if (arr == null || arr.length == 0 || upper < lower) {
            return 0;
        }
        long[] sum = new long[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        return process(sum, 0, arr.length - 1, lower, upper);
    }

    private int process(long[] arr, int L, int R, int lower, int upper) {
        if (L == R) {
            return arr[L] >= lower && arr[L] <= upper ? 1 : 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid, lower, upper) + process(arr, mid + 1, R, lower, upper) + merge(arr, L, mid, R, lower, upper);
    }

    private int merge(long[] arr, int L, int mid, int R, int lower, int upper) {
        if (L == R) {
            return 0;
        }
        long[] help = new long[R - L + 1];
        int leftIndex = L;
        int rightIndex = mid + 1;

        int l = L;
        int r = L;
        int ans = 0;
        while (rightIndex <= R) {
            // 需要满足的条件
            long min = arr[rightIndex] - upper;
            long max = arr[rightIndex] - lower;
            while (l <= mid && arr[l] < min) {
                l++;
            }
            while (r <= mid && arr[r] <= max) {
                r++;
            }
            ans += r - l;
            rightIndex++;
        }
        leftIndex = L;
        rightIndex = mid + 1;
        int index = 0;
        while (leftIndex <= mid && rightIndex <= R) {
            help[index++] = arr[leftIndex] < arr[rightIndex] ? arr[leftIndex++] : arr[rightIndex++];
        }
        while (leftIndex <= mid) {
            help[index++] = arr[leftIndex++];
        }
        while (rightIndex <= R) {
            help[index++] = arr[rightIndex++];
        }
        for (int i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;

    }

    /**
     * 使用有序表的方式实现
     *
     * @return
     */
    public int countRangeSum3(int[] arr, int lower, int upper) {
        if (arr == null || arr.length == 0 || lower > upper) {
            return 0;
        }
        // 加工原始数组
//        long[] sum = new long[arr.length];
//        sum[0] = arr[0];
//        for (int i = 1; i < arr.length; i++) {
//            sum[i] = sum[i - 1] + arr[i];
//        }
        SizeMap sizeMap = new SizeMap();
        sizeMap.put(0);
        int ans = 0;
        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            long min = sum - upper;
            long max = sum - lower;
            int leftIndex = sizeMap.getLessIndex(min);
            int rightIndex = sizeMap.getLessIndex(max + 1);
            sizeMap.put(sum);
            ans += rightIndex - leftIndex;
        }
        return ans;
    }


    public int[] generateRandomArray(int len, int value) {
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * value) - (int) (Math.random() * value);
        }
        return arr;
    }

    public int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public boolean isEqual(int[] arr1, int[] arr2) {
        if (arr1 == null && arr2 != null) {
            return false;
        }
        if (arr1 != null && arr2 == null) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int len = 30;
        int value = 10;

        Code03_CountOfRange countOfRange = new Code03_CountOfRange();
        for (int i = 0; i < testTime; i++) {
            int lower = Math.min((int) (Math.random() * value), (int) (Math.random() * value));
            int upper = Math.max((int) (Math.random() * value), (int) (Math.random() * value));

            int[] arr = countOfRange.generateRandomArray(len, value);
//            int[] arr = new int[]{-2, 5, -1};
            int[] arr1 = countOfRange.copyArray(arr);
            int[] arr2 = countOfRange.copyArray(arr);

            int ans1 = countOfRange.countRangeSum(arr, lower, upper);
            int ans2 = countOfRange.countRangeSum2(arr1, lower, upper);
            int ans3 = countOfRange.countRangeSum3(arr2, lower, upper);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}


class SizeMap {
    private SizeNode root;
    private int size;


    private SizeNode leftRotate(SizeNode cur) {
        if (cur == null) {
            return cur;
        }
        long same = cur.all - (cur.left != null ? cur.left.all : 0) - (cur.right != null ? cur.right.all : 0);
        // 左旋
        SizeNode rightNode = cur.right;
        // 这行代码写错了
        // cur.right = cur.left;
        cur.right = rightNode.left;
        rightNode.left = cur;
        rightNode.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        rightNode.all = cur.all;
        cur.all = (cur.left != null ? cur.left.all : 0) + (cur.right != null ? cur.right.all : 0) + same;
        return rightNode;
    }

    private SizeNode rightRotate(SizeNode cur) {
        if (cur == null) {
            return cur;
        }
        long same = cur.all - (cur.left != null ? cur.left.all : 0) - (cur.right != null ? cur.right.all : 0);
        SizeNode leftNode = cur.left;
        cur.left = leftNode.right;
        leftNode.right = cur;
        leftNode.size = cur.size;
        cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
        leftNode.all = cur.all;
        cur.all = same + (cur.left != null ? cur.left.all : 0) + (cur.right != null ? cur.right.all : 0);
        return leftNode;
    }

    /**
     * 调整函数
     * 调整的步骤
     *
     * @param cur
     * @return
     */
    private SizeNode maintain(SizeNode cur) {
        if (cur == null) {
            return cur;
        }
        long leftSize = cur.left != null ? cur.left.size : 0;
        long leftLeftSize = cur.left != null && cur.left.left != null ? cur.left.left.size : 0;
        long leftRightSize = cur.left != null && cur.left.right != null ? cur.left.right.size : 0;
        long rightSize = cur.right != null ? cur.right.size : 0;
        long rightLeftSize = cur.right != null && cur.right.left != null ? cur.right.left.size : 0;
        long rightRightSize = cur.right != null && cur.right.right != null ? cur.right.right.size : 0;
        if (leftLeftSize > rightSize) {
            cur = rightRotate(cur);
            cur.right = maintain(cur.right);
            cur = maintain(cur);
        } else if (leftRightSize > rightSize) {
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

    public void put(long value) {
        root = add(root, value, containsKey(value));
    }

    private SizeNode add(SizeNode cur, long value, boolean contains) {
        if (cur == null) {
            return new SizeNode(value);
        } else {
            cur.all++;
            if (cur.value == value) {
                // 如果相等的话 那么就不需要进行添加
                return cur;
            } else {
                if (!contains) {
                    cur.size++;
                }
                if (cur.value > value) {
                    cur.left = add(cur.left, value, contains);
                } else {
                    cur.right = add(cur.right, value, contains);
                }
                return maintain(cur);
            }
        }
    }

//    public void remove(int value) {
//        KeyNode keyNode = new KeyNode(value);
//        root = delete(root, keyNode);
//    }
//
//    private SizeNode delete(SizeNode cur, KeyNode keyNode) {
//        if (cur == null) {
//            return cur;
//        }
//        if (cur.keyNode.compareTo(keyNode) < 0) {
//            // 如果当前节点比需要删除的节点要小 那么需要删除的节点在右树
//            cur = delete(cur.right, keyNode);
//        } else if (cur.keyNode.compareTo(keyNode) > 0) {
//            cur = delete(cur.left, keyNode);
//        } else {
//            // 相等 意味着当前的节点就是需要删除的节点
//            if (cur.left == null && cur.right == null) {
//                cur = null;
//            } else if (cur.left == null) {
//                cur = cur.right;
//            } else if (cur.right == null) {
//                cur = cur.left;
//            } else {
//                // 左树右树都不为空  使用右树的最左侧节点替换当前节点
//                // 为什么使用右树的最左侧节点  右树的最最左侧节点一定比当前节点的左树大并且比当前节点的右树要小
//                // 调整之后能够保证整棵树依然满足二叉搜索树的性质 即当前节点的左树的所有节点比当前节点要小 右树的所有节点都比当前节点要大
//                SizeNode pre = null;
//                SizeNode mostRightLeft = cur.right;
//                while (mostRightLeft.left != null) {
//                    pre = mostRightLeft;
//                    mostRightLeft = mostRightLeft.left;
//                }
//                if (pre != null) {
//                    // pre 不为空 意味着 当前节点右树不止一个节点
//                    // 那么需要进行的调整就是删除mostRighteft节点之后
//                    // 需要pre节点的左树接管的mostRightLeft的右树
//                    pre.left = mostRightLeft.right;
//                    mostRightLeft.right = cur;
//                }
//                mostRightLeft.left = cur.left;
//                cur = mostRightLeft;
//                cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
//            }
//        }
//        return cur;
//    }

    private boolean containsKey(long value) {
        SizeNode cur = root;
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

    public int getLessIndex(long value) {
        SizeNode cur = root;
        int ans = 0;
        while (cur != null) {
//            if (cur.keyNode.compareTo(keyNode) < 0) {
//                int same = cur.all - (cur.left != null ? cur.left.all : 0) - (cur.right != null ? cur.right.all : 0);
//                ans += same + (cur.left != null ? cur.left.all : 0);
//                cur = cur.right;
//            } else if (cur.keyNode.compareTo(keyNode) > 0) {
//                cur = cur.left;
//            } else {
//                int same = cur.all - (cur.left != null ? cur.left.all : 0) - (cur.right != null ? cur.right.all : 0);
//                ans += (cur.left != null ? cur.left.all : 0) + same;
//                break;
//            }
            if (cur.value == value) {
                ans += (cur.left != null ? cur.left.all : 0);
                break;
            } else if (cur.value < value) {
                ans += cur.all - (cur.right != null ? cur.right.all : 0);
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return ans;
    }
}

class SizeNode {
    long size;
    long value;
    long all;

    SizeNode left;
    SizeNode right;

    SizeNode(long value) {
        this.size = size;
        this.all = 1;
        this.size = 1;
        this.value = value;
    }
}

//class KeyNode implements Comparable<KeyNode> {
//    private long value;
//
//    KeyNode(long value) {
//        this.value = value;
//    }
//
//    @Override
//    public int compareTo(KeyNode keyNode) {
//        return Long.compare(value, keyNode.value);
//    }
//}
