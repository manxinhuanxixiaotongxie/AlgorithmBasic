package systemimprove.code28;

import java.util.HashSet;

/**
 * 给定一个数组 给定一个范围 返回数组中有多少个数在这个范围上
 *
 * 给定一个数组arr，和两个整数a和b（a<=b）
 * 求arr中有多少个子数组，累加和在[a,b]这个范围上
 * 返回达标的子数组数量
 *
 */
public class Code01_CountOfRange {

    public int countOfRange(int[] arr, int k1, int k2) {
        if (arr == null || arr.length == 0 || k1 > k2) {
            return 0;
        }
        int ans = 0;
        // 求出所有子数组的数量
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int p = i; p <= j; p++) {
                    sum += arr[p];
                }
                if (sum >= k1 && sum <= k2) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int countOfRange2(int[] arr, int k1, int k2) {
        if (arr == null || arr.length == 0 || k1 > k2) {
            return 0;
        }
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                // 已经有了前缀和数组 那么如果要求子数组是否在范围
                // i 到j的前缀和数组的和就是sum[j] - sum[i-1]
                int cur = sum[j] - (i - 1 >= 0 ? sum[i - 1] : 0);
                if (cur >= k1 && cur <= k2) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int countRangeSum2(int[] nums, int lower, int upper) {
        // 使用归并排序的方式进行处理
        if (nums == null || nums.length == 0 || lower > upper) {
            return 0;
        }
        long[] sum = new long[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return process(sum, 0, nums.length - 1, lower, upper);
    }


    public int process(long[] arr, int l, int r, int k1, int k2) {
        if (l == r) {
            return arr[l] >= k1 && arr[l] <= k2 ? 1 : 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid, k1, k2) + process(arr, mid + 1, r, k1, k2) + merge(arr, l, mid, r, k1, k2);

    }

    public int merge(long[] arr, int l, int mid, int r, int k1, int k2) {
        long[] help = new long[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int ans = 0;
        int windowL = l;
        int windowR = l;
        for (int i = mid + 1; i <= r; i++) {
            long max = arr[i] - k1;
            long min = arr[i] - k2;
            while (windowL <= mid && arr[windowL] < min) {
                windowL++;
            }
            while (windowR <= mid && arr[windowR] <= max) {
                windowR++;
            }
            ans += windowR - windowL;
        }
        int index = 0;

        while (p1 <= mid && p2 <= r) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[index++] = arr[p1++];
        }
        while (p2 <= r) {
            help[index++] = arr[p2++];
        }
        for (int j = 0; j < help.length; j++) {
            arr[l + j] = help[j];
        }
        return ans;
    }

    /**
     * 第四种方法 使用平衡二叉搜素树实现
     * 分析一下：
     * 必须要以i位置结尾 有多少个子数组满足条件的子数组
     * 转化成前面有多少个前缀和在sun[i] - k1 到 sum[i] - k2之间
     * 为什么可以做这种转化呢？
     * 必须以I位置结尾的子数组在范围内   那么意味着 从0到x位置的的前缀和必须要满足在sum[i] - k1 到 sum[i] - k2之间
     * 为什么呢？
     * 因为 X-j  满足范围内 那么 sum[j]-sum[x]在范围内  等价于 k1 < sum[j] - sum[x] < k2
     * 那么 sum[j] - k2 < sum[x] < sum[j] - k1  因此  我们可以得出一个结论
     * 如果要求有多少个子数组在范围内 那么就是求 从0开始的的前缀和有多少个在sum[i] - k1 到 sum[i] - k2之间
     * <p>
     * 这个题目之所以可以使用平衡二叉搜索树实现  就是因为 平衡二叉搜索树可以在O(logN)的时间复杂度内找到一个数的前缀和
     * <p>
     * <p>
     * 这个二叉树需要满足什么条件呢？
     * 1.按照前缀和的大小进行排序
     * 2.需要接受重复值
     * 3.需要支持给定一个前缀和查询有多少数小于这个前缀和
     * 节点需要哪些信息：
     * 1.size 用于记录有多少个节点
     * 2.value 用于记录前缀和
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    public int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0 || lower > upper) {
            return 0;
        }
        SBTGreater sbtGreater = new SBTGreater();
        sbtGreater.put(0);
        long sum = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            // 要找到以i结尾的前缀和有多少范围内 按照之前的分析
            // 需要找到有多少个前缀和在sum[i] - k1 到 sum[i] - k2之间
            long min = sum - upper;
            long max = sum - lower;
            int less = sbtGreater.findLessNum(min);
            int more = sbtGreater.findLessNum(max + 1);
            sbtGreater.put(sum);
            ans += more - less;
        }
        return ans;

    }


    public class SBTGreater {

        private SBTNode root;
        private HashSet<Long> set = new HashSet<>();

        public void put(long sum) {

//            boolean contains = set.contains(sum);
            root = add(root, sum, containsKey(sum));
//            set.add((long) sum);

        }

        public boolean containsKey(long num) {
            SBTNode cur = root;
            boolean ans = false;
            while (cur != null) {
                if (cur.value == num) {
                    ans = true;
                    break;
                } else if (cur.value < num) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return ans;
        }

        public SBTNode add(SBTNode cur, long value, boolean contains) {
            if (cur == null) {
                return new SBTNode(value);
            } else {
                cur.all++;
                if (cur.value == value) {
                    return cur;
                } else {
                    if (!contains) {
                        cur.size++;
                    }
                    if (value < cur.value) {
                        cur.left = add(cur.left, value, contains);

                    }
                    if (value > cur.value) {
                        cur.right = add(cur.right, value, contains);
                    }
                    return maintain(cur);
                }
            }
        }

        public int findLessNum(long num) {
            SBTNode cur = root;
            int ans = 0;
            while (cur != null) {
                if (cur.value == num) {
                    ans += (cur.left != null ? cur.left.all : 0);
                    break;
                } else if (cur.value < num) {
                    ans += cur.all - (cur.right != null ? cur.right.all : 0);
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return ans;

        }

        public SBTNode maintain(SBTNode cur) {
            if (cur == null) {
                return null;
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

        public SBTNode leftRotate(SBTNode cur) {
            long same = cur.all - (cur.left !=null?cur.left.all:0) - (cur.right != null ? cur.right.all : 0);
            SBTNode right = cur.right;
            cur.right = right.left;
            right.left = cur;
            right.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            right.all = cur.all;
            cur.all = (cur.left != null ? cur.left.all : 0) + (cur.right != null ? cur.right.all : 0) + same;
            return right;
        }

        public SBTNode rightRotate(SBTNode cur) {
            long same = cur.all - (cur.left !=null?cur.left.all:0) - (cur.right != null ? cur.right.all : 0);
            SBTNode left = cur.left;
            cur.left = left.right;
            left.right = cur;
            left.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            left.all = cur.all;
            cur.all = (cur.left != null ? cur.left.all : 0) + (cur.right != null ? cur.right.all : 0) + same;
            return left;
        }


    }

    class SBTNode {
        long value;
        long size;
        long all;
        SBTNode left;
        SBTNode right;

        public SBTNode(long value) {
            this.value = value;
            this.size = 1;
            this.all = 1;
        }
    }

    /**
     * 给定一个最大值  最大长度  随机生成一个随机数组
     *
     * @return
     */
    public int[] generateArr(int maxValue, int maxLength) {
        int length = (int) (Math.random() * maxLength + 1);
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            int value = (int) (Math.random() * maxValue + 1) - (int) (Math.random() * maxValue);
            arr[i] = value;
        }
        return arr;
    }

    public int[] copyArray(int[] arr) {
        int[] copy = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            copy[i] = arr[i];
        }
        return copy;
    }

    public void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int maxValue = 100;
        int maxLength = 10;
        int testTime = 100000;
        Code01_CountOfRange countOfRange = new Code01_CountOfRange();
        System.out.println("功能测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = countOfRange.generateArr(maxValue, maxLength);
            int k1 = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
            int k2 = (int) (Math.random() * maxValue) - (int) (Math.random() * maxValue);
//            arr = new int[3];
//            arr[0] = 1;
//            arr[1] = 2;
//            arr[2] = 3;
//            k1 = 4;
//            k2 = 10;
            int[] copy = countOfRange.copyArray(arr);
            int ans1 = countOfRange.countOfRange(arr, k1, k2);
            int ans2 = countOfRange.countOfRange2(copy, k1, k2);
            int ans3 = countOfRange.countRangeSum(copy, k1, k2);
            int ans4 = countOfRange.countRangeSum2(copy, k1, k2);
            if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                System.out.println("Oops!");
                countOfRange.printArray(arr);
                System.out.println(k1);
                System.out.println(k2);
//                break;
            }
        }
        System.out.println("功能测试结束");
    }
}
