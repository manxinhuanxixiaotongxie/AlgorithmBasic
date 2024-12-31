package traingcamp.camp003.code04;

import java.util.HashMap;

/**
 * 已知一棵二叉树中没有重复节点，并且给定了这棵树的中序遍历数组和先序遍历 数组，返回后序遍历数组。
 * 比如给定:
 * int[] pre = { 1, 2, 4, 5, 3, 6, 7 };
 * int[] in = { 4, 2, 5, 1, 6, 3, 7 }; 返回:
 * {4,5,2,6,7,3,1}
 */
public class Code03 {

    /**
     * 给定一个先序遍历、中序遍历的数组
     * 求对应的二叉树的后续遍历
     * <p>
     * 思路：
     * 先序 头左右 { 1, 2, 4, 5, 3, 6, 7 };
     * 中序 左中右 { 4, 2, 5, 1, 6, 3, 7 };
     * <p>
     * 后序遍历：左右头
     * 我们用上面的两个数组来说明整个流程
     * 第一个位置是1 在中序遍历的数组是在3位置  那么0-3就是整棵树的左树   4-6就是整棵树的右树
     *
     * @param pre
     * @param in
     * @return
     */
    public int[] getPos(int[] pre, int[] in) {
        int[] ans = new int[pre.length];
        process1(pre, in, 0, pre.length - 1, 0, in.length - 1, ans, 0, pre.length - 1);
        return ans;
    }

    /**
     * 在l1 r1  l2 r2范围上计算后续遍历对应的位置
     *
     * @param pre
     * @param in
     * @param l1
     * @param r1
     * @param l2
     * @param r2
     * @param ans
     */
    private void process1(int[] pre, int[] in, int l1, int r1, int l2, int r2, int[] ans, int l3, int r3) {
        // 在这个递归的过程中 l1与l2的位置是一样
        // r1与r2的位置是一样
        if (l1 > r1) {
            return;
        }
        if (l1 == r1) {
            // 直接填入
            ans[l3] = pre[l1];
            return;
        }
        // 在l2-r2上找到l1对应的位置
        // 作为头节点的后续遍历所在的位置
        // 先序 1 2 3    中序   2 1 3
        // 0-0就是左树所在的位置
        // 2-2就是右树坐在的位置
        // 在l2-r2找到l1所在位置
        int index = l2;
        while (index <= r2) {
            if (in[index] == pre[l1]) {
                break;
            }
            index++;
        }
        ans[r3] = pre[l1];
        // 那么左树所在的位置就是l2-index-1
        // 右树所在位置就是index+1-r2
        // 对于先序数组来说
        // 头节点的位置是l1
        // 左树的位置是l1 +1  - (l1 + index - l2)
        // 右树的位置是l1 + index - l2 + 1 - r1
        // 左树的个数
        int leftSize = index - l2;
        // leftSize代表是左树的个数
        // 那么对于先序遍历来说 l1+1 到l1+leftSize就是左树的位置
        // 对于中序遍历来说 左树的的区间就是l2 到的l2+leftSize-1
        // 对于后序遍历来说l3已经是确定的了  左树就是l3到l3+leftSize-1
        process1(pre, in, l1 + 1, l1 + leftSize, l2, l2 + leftSize - 1, ans, l3, l3 + index - 1);
        // 对于l3后序遍历来说  前面左树的位置左树的位置加上l3 + 左树的个数
        // 右树的位置是index +右树的个数
        // 对于后续遍历的右树来说 l3+leftSize到r3-1
        process1(pre, in, l1 + leftSize + 1, r1, leftSize + 1, r2, ans, l3 + leftSize, r3 - 1);
    }

    public static int[] preInToPos2(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length) {
            return null;
        }
        int N = pre.length;
        HashMap<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            inMap.put(in[i], i);
        }
        int[] pos = new int[N];
        process2(pre, 0, N - 1, in, 0, N - 1, pos, 0, N - 1, inMap);
        return pos;
    }

    public static void process2(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3,
                                HashMap<Integer, Integer> inMap) {
        if (L1 > R1) {
            return;
        }
        if (L1 == R1) {
            pos[L3] = pre[L1];
            return;
        }
        pos[R3] = pre[L1];
        int mid = inMap.get(pre[L1]);
        int leftSize = mid - L2;
        process2(pre, L1 + 1, L1 + leftSize, in, L2, mid - 1, pos, L3, L3 + leftSize - 1, inMap);
        process2(pre, L1 + leftSize + 1, R1, in, mid + 1, R2, pos, L3 + leftSize, R3 - 1, inMap);
    }

    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 5, 3, 6, 7};
        int[] in = {4, 2, 5, 1, 6, 3, 7};
        Code03 code03 = new Code03();
        int[] pos = code03.getPos(pre, in);
        for (int i : pos) {
            System.out.print(i + " ");
        }
    }
}
