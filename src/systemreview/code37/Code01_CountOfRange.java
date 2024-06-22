package systemreview.code37;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个数组arr，和两个整数a和b（a<=b）
 * 求arr中有多少个子数组，累加和在[a,b]这个范围上
 * 返回达标的子数组数量
 */
public class Code01_CountOfRange {

    public int counOfRange1(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 暴力解法
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                if (sum >= a && sum <= b) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int countOfRange2(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sumArr = new int[arr.length];
        sumArr[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sumArr[i] = sumArr[i - 1] + arr[i];
        }
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                // 子数组
                // 在子数组上面进行
                int sum = sumArr[j] - sumArr[i] + arr[i];
                if (sum >= a && sum <= b) {
                    ans++;
                }
            }
        }
        return ans;
    }

    public int countOfRange3(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        return getCount(sum, a, b);
    }

    private int getCount(int[] arr, int a, int b) {

        return process(arr, 0, arr.length - 1, a, b);
    }

    private int process(int[] arr, int L, int R, int a, int b) {
        if (L == R) {
            return arr[L] >= a && arr[L] <= b ? 1 : 0;
        }
        int mid = L + ((R - L) >> 1);
        return process(arr, L, mid, a, b) + process(arr, mid + 1, R, a, b) + merge(arr, L, mid, R, a, b);
    }

    private int merge(int[] arr, int L, int mid, int R, int a, int b) {
        int[] help = new int[R - L + 1];


        /**
         * 窗口法  如果要求有多少数在的[a,b]之间 意味着
         * 转化成以i结尾的子数组中有多少个子数组满足条件
         *
         * 100 [30,50] [50,70]
         * 110 [30,50] [60,80]
         */

        int windowL = L;
        int windowR = L;

        int right = mid + 1;
        int ans = 0;
        while (right <= R) {
            int min = arr[right] - b;
            int max = arr[right] - a;
            while (windowL <= mid && arr[windowL] < min) {
                windowL++;
            }
            while (windowR <= mid && arr[windowR] <= max) {
                windowR++;
            }
            ans += windowR - windowL;
            right++;
        }


        int leftIndex = L;
        int rightIndex = mid + 1;
        int index = 0;

        while (leftIndex <= mid && rightIndex <= R) {
            if (arr[leftIndex] < arr[rightIndex]) {
                help[index++] = arr[leftIndex++];
            } else {
                help[index++] = arr[rightIndex++];
            }
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

    private int[] generateArr(int maxValue, int maxLength) {
        int[] arr = new int[(int) (Math.random() * maxLength)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public int countOfRange4(int[] arr, int a, int b) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = sum[i - 1] + arr[i];
        }
        CountSBTTree countSBTTree = new CountSBTTree();
        countSBTTree.put(0);
        int ans = 0;
        for (int j : sum) {
            int lower = j - b;
            int upper = j - a;
            int less = countSBTTree.findLessNums(upper + 1);
            int more = countSBTTree.findLessNums(lower);
            countSBTTree.put(j);
            ans += less - more;
        }
        return ans;
    }

    /**
     * 经过SBT优化过的二叉搜索树
     * 思路：
     * 1.题目要求的是子数组的和在[a,b]之间
     * 2.转化一下 如果以i位置结尾的子数组满足条件有多少个 其实就是求 之前有多少前缀和在arr[i]-b和arr[i]-a之间
     * <p>
     * 改造一下二叉搜索树 以SBT为例 每个节点新增一个all字段 用于标记该节点下总共有多少个节点
     */
    static class CountSBTTree {

        CountSBTNode root;

        Set<Integer> nodeSet = new HashSet<>();

        public void put(int sum) {
            root = add(root, sum);
        }

        public boolean containsKey(long num) {
            CountSBTNode cur = root;
            boolean ans = false;
            while (cur != null) {
                if (cur.key == num) {
                    ans = true;
                    break;
                } else if (cur.key < num) {
                    cur = cur.right;
                } else {
                    cur = cur.left;
                }
            }
            return ans;
        }


        /**
         * 在搜索二叉树中 查找小于某一个数有多少个
         *
         * @return
         */
        public int findLessNums(int value) {
            int ans = 0;
            CountSBTNode cur = root;
            while (cur != null) {
                if (cur.key < value) {
                    ans += cur.all - (cur.right != null ? cur.right.all : 0);
                    cur = cur.right;
                } else if (cur.key > value) {
                    cur = cur.left;
                } else {
                    // 相等的话要把左树节点算上
                    ans += cur.left != null ? cur.left.all : 0;
                    break;

                }
            }
            return ans;
        }

        private CountSBTNode add(CountSBTNode cur, int value) {
            if (cur == null) {
                nodeSet.add(value);
                return new CountSBTNode(value);
            }
            cur.all++;
            if (!nodeSet.contains(value)) {
                cur.size++;
            }
            if (value < cur.key) {
                cur.left = add(cur.left, value);
            } else if (value > cur.key) {
                cur.right = add(cur.right, value);
            } else {
                nodeSet.add(value);
                return cur;
            }
            return maintain(cur);
        }

        private CountSBTNode maintain(CountSBTNode cur) {
            if (cur == null) {
                return null;
            }
            int leftsize = cur.left == null ? 0 : cur.left.size;
            int leftleftsize = cur.left == null || cur.left.left == null ? 0 : cur.left.left.size;
            int leftrightsize = cur.left == null || cur.left.right == null ? 0 : cur.left.right.size;
            int rightsize = cur.right == null ? 0 : cur.right.size;
            int rightrightsize = cur.right == null || cur.right.right == null ? 0 : cur.right.right.size;
            int rightleftsize = cur.right == null || cur.right.left == null ? 0 : cur.right.left.size;
            if (leftleftsize > rightsize) {
                // ll 违规
                cur = rightRotate(cur);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (leftrightsize > rightsize) {
                cur.left = leftRoatate(cur.left);
                cur = rightRotate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            } else if (rightrightsize > leftsize) {
                cur = leftRoatate(cur);
                cur.left = maintain(cur.left);
                cur = maintain(cur);
            } else if (rightleftsize > leftsize) {
                cur.right = rightRotate(cur.right);
                cur = leftRoatate(cur);
                cur.left = maintain(cur.left);
                cur.right = maintain(cur.right);
                cur = maintain(cur);
            }
            return cur;
        }

        private CountSBTNode leftRoatate(CountSBTNode cur) {
            if (cur == null) {
                return cur;
            }
            int same = cur.all - (cur.left != null ? cur.left.all : 0) - (cur.right != null ? cur.right.all : 0);
            CountSBTNode right = cur.right;
            cur.right = right.left;
            right.left = cur;
            right.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            // all进行调整
            right.all = cur.all;
            cur.all = (cur.left != null ? cur.left.all : 0) + (cur.right != null ? cur.right.all : 0) + same;
            return right;
        }

        private CountSBTNode rightRotate(CountSBTNode cur) {
            if (cur == null) {
                return cur;
            }
            int same = cur.all - (cur.left != null ? cur.left.all : 0) - (cur.right != null ? cur.right.all : 0);
            CountSBTNode left = cur.left;
            cur.left = left.right;
            left.right = cur;
            left.size = cur.size;
            cur.size = (cur.left != null ? cur.left.size : 0) + (cur.right != null ? cur.right.size : 0) + 1;
            left.all = cur.all;
            cur.all = (cur.left != null ? cur.left.all : 0) + (cur.right != null ? cur.right.all : 0) + same;
            return left;
        }

    }


    static class CountSBTNode {
        // 平衡因子
        int size;

        // 包含自身节点的个数
        int all;

        int key;

        CountSBTNode left;

        CountSBTNode right;

        public CountSBTNode(int key) {
            this.key = key;
            this.size = 1;
            this.all = 1;
        }
    }

    public static void main(String[] args) {
        Code01_CountOfRange test = new Code01_CountOfRange();
        int maxValue = 100;
        int maxLength = 10;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            int[] arr = test.generateArr(maxValue, maxLength);
            int a = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
            int b = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * (maxValue + 1));
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            int ans1 = test.counOfRange1(arr, a, b);
            int ans2 = test.countOfRange2(arr, a, b);
            int ans3 = test.countOfRange3(arr, a, b);
            int ans4 = test.countOfRange4(arr, a, b);
            if (ans1 != ans2 || ans2 != ans3 || ans3 != ans4) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
