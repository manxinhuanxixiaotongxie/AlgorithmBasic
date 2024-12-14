package systemimprove.code32;

/**
 * indextree
 * <p>
 * <p>
 * indextree解决的问题是单点更新
 * <p>
 * 假设现在有一个数组 给你一个需求
 * <p>
 * 1.能够快速求出某一个区间范围的累加和
 * 2.当某一个节点的数值发生了改变 增加了某个数或者减少了某个数 能够正确的更新
 * <p>
 * <p>
 * 要求：
 * 求解L-R的累加和以及更新之后的累加和的时间复杂度为O(logN)
 * <p>
 * <p>
 * indextree就是为了解决这个问题
 * <p>
 * indextree的下标从1开始
 * <p>
 * <p>
 * [3 2 1 3 6 5 2 4 5 5 4]
 * [1 2 3 4 5 6 7 8 9 10]
 * <p>
 * 我们现在要求[3,9]的累加和
 * 先求0-9的累加和 然后减去0-2的累加和
 * 对应的indextree的下标是4-10  先求1-10  再求1-3  两者相减
 * <p>
 * <p>
 * [3 2 1 3 6 5 2 4 5 5 4]
 * [1 2 3 4 5 6 7 8 9 10]
 * 我们现在对这个数组进行加工
 * <p>
 * 其中1位置代表是1-1的和
 * 2位置是1-2的和
 * 3位置是3-3
 * 4位置是1-4
 * 5位置是5-5
 * 6位置是5-6
 * 7位置是7-7
 * 8位置是1-8
 * 9位置是9-9
 * 10位置是9-10
 * <p>
 * 加工出来的数组变成了；
 * [3 5 1 9 6 11 2 26 5 10 4]
 * <p>
 * 代表含义就是：
 * 1位置代表是原数组中1-1的值nums[1]
 * 2位置代表是原数组中1-2的值nums[1]+nums[2]
 * 3位置代表是原数组中3-3的值nums[3]
 * 4位置代表是原数组中1-4的值nums[1]+nums[2]+nums[3]+nums[4]
 * 5位置代表是原数组中5-5的值nums[5]
 * 6位置代表是原数组中5-6的值nums[5]+nums[6]
 * 7位置代表是原数组中7-7的值nums[7]
 * 8位置代表是原数组中1-8的值nums[1]+nums[2]+nums[3]+nums[4]+nums[5]+nums[6]+nums[7]+nums[8]
 * 9位置代表是原数组中9-9的值nums[9]
 * 10位置代表是原数组中9-10的值nums[9]+nums[10]
 * <p>
 * 规律是：
 * <p>
 * 对于indextree来说的代表位置就是对应下标的最后一个1右移到0位置之后的值 到当前位置的值的累加和
 * <p>
 * 比如5：二进制数是101 最后一个1就在0位置 因为代表位置就是5-5的值
 * 比如6 二进制是的1010 最后一个1在1位置因为代表位置就是1001-1010的位置就是indextree的数组中5-6的位置
 * 比如 4或者8   就是将第三位的1或者第四位的1移动到0位置 代表的位置就是1-4或者1-8的位置
 * <p>
 * <p>
 * 那么  我们现在要求的是3-9的累加和
 * 先算1-9的累加和：
 * 9位置数代表是9-9的值
 * 还差1-8没有参与计算
 * 8位置就是代表的1-8
 * <p>
 * <p>
 * 因此1-9的累加和就是9位置的值+8位置的值
 * <p>
 * <p>
 * <p>
 * 再算1-2的累加和
 * <p>
 * 2位置代表的数就是1-2的值
 * 因此1-2的累加和就是2位置的值
 * <p>
 * <p>
 * <p>
 * 再举个大一点的例子：
 * <p>
 * 15-21的累加和
 * <p>
 * 先求1-21的累加和
 * <p>
 * 21代表的就是21-21的值
 * 还差1-20的值
 * 20的二进制是16+4 10100  代表的是10001-10100的值 17到20的值
 * 还有16的值
 * 16的二进制是10000 代表的是1-16的值
 * 因此1-21的累加和就是21位置的值+20位置的值+16位置的值
 * <p>
 * <p>
 * 再求1-14的累加和
 * <p>
 * 14的二进制是1110 代表的是10001-1110的值 11到14的值
 * 还有10的值
 * 10的二进制是1010 代表的是10001-1010的值 9到10的值
 * 还有8的值
 * 8的二进制是1000 代表的是10001-1000的值 1到8的值
 * 因此1-14的累加和就是14位置的值+10位置的值+8位置的值
 * <p>
 * 区间累加和的问题 就是上述的解法：
 * 代码就是
 * 当前位置的值减去最后一个1右移到0位置的值 然后将所有的值相加
 * <p>
 * 累加和的问题就解决了
 * <p>
 * 单点更新的问题：
 * 依然是刚刚的那个的数组
 * <p>
 * 加工前：
 * <p>
 * [3 2 1 3 6 5 2 4 5 5 4]
 * [1 2 3 4 5 6 7 8 9 10]
 * <p>
 * 加工后：
 * [3 5 1 9 6 11 2 26 5 10 4]
 * <p>
 * 假设我们现在要在5位置上的数值加上3
 * 那么原始数组就会变成
 * [3 2 1 3 9 5 2 4 5 5 4]
 * <p>
 * 对应的加工数组的变化是：
 * <p>
 * 加工数组的5位置代表的是原有数组的5位置
 * <p>
 * 后续的加工数组包含5位置的位置是 6 8 16 32 64 128 256 512 1024
 * <p>
 * 5位置的二进制是0101
 * 6位置的二进制是0110
 * 8位置的二进制是1000
 * <p>
 * 假设我们现在要修改的位置是7位置
 * 那么加工数组影响的位置是：
 * 7位置 0111
 * 8位置 1000
 * 16位置
 * 32位置
 * <p>
 * 规律是：
 * <p>
 * 修改的点当前值加上提取出最后的一个1相加
 */
public class Code05_IndexTree {


    static class IndexTree {
        private int[] help;

        IndexTree(int n) {
            // 0位置弃而不用
            help = new int[n + 1];
        }


        public int sum(int index) {
            int ans = 0;
            while (index > 0) {
                ans += help[index];
                index -= index & -index;
            }
            return ans;
        }

        public void add(int inedx, int num) {
            while (inedx <= help.length) {
                help[inedx] += num;
                inedx += inedx & -inedx;
            }
        }

    }

    static class Right {
        private int[] nums;
        private int N;

        public Right(int size) {
            N = size + 1;
            nums = new int[N + 1];
        }

        public int sum(int index) {
            int ret = 0;
            for (int i = 1; i <= index; i++) {
                ret += nums[i];
            }
            return ret;
        }

        public void add(int index, int d) {
            nums[index] += d;
        }
    }

    public static void main(String[] args) {
        int N = 100;
        int V = 100;
        int testTime = 2000000;
        IndexTree tree = new IndexTree(N);
        Right test = new Right(N);
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int index = (int) (Math.random() * N) + 1;
            if (Math.random() <= 0.5) {
                int add = (int) (Math.random() * V);
                tree.add(index, add);
                test.add(index, add);
            } else {
                if (tree.sum(index) != test.sum(index)) {
                    System.out.println("Oops!");
                }
            }
        }
        System.out.println("test finish");
    }
}
