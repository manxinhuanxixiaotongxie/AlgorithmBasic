package code13;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-08-17 17:11
 */
public class Code07_Queen {

    public static void main(String[] args) {
        System.out.println(new Code07_Queen().solveNQueens(4));
        System.out.println(new Code07_Queen().solveNQueens2(4));
    }

    public int solveNQueens(int n) {
        return process(n, new int[n], 0);
    }

    public int process(int n, int[] record, int index) {
        if (index == n) {
            return 1;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (isValid(record, index, i)) {
                record[index] = i;
                ans += process(n, record, index + 1);
            }
        }
        return ans;
    }

    public boolean isValid(int[] record, int i, int j) {
//        for (int k = 0; k < i; k++) {
        //对角线  行号之差等于列 数 之差
//            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(i - k)) {
//                return false;
//            }
//        }
        for (int k = 0; k < i; k++) {
            if (record[i] == j) {
                return false;
            }
        }

        // 左对角线
        for (int k = 0; k < i; k++) {
            // record[k]-k 列向前推
            // record[i]-i 列向后推
            // record[i]第i行放在了多少列
            // record[k]第i行放在了多少列

            if (record[k] - k == record[i] - i) {
                return false;
            }
        }

        // 右对角线
        for (int k = 0; k < i; k++) {
            // record[k]+k 列向后推
            // record[i]+i 行向前推
            if (record[k] + k == record[i] + i) {
                return false;
            }
        }

        return true;
    }


    //使用位运算进行尝试

    public int solveNQueens2(int n) {

        if (n > 32) {
            return -1;
        }

        // 列限制
        int colLim = n == 32 ? -1 : (1 << n) - 1;

        return proess2(colLim, 0, 0, 0);

    }


    // 刚开始colLim全部是1，表示所有列都可以放皇后
    public int proess2(int limit, int colLim, int leftDiaLim, int rightDiaLim) {

        if (colLim == limit) {
            return 1;
        }

        // 将所有列限制、左对角线限制、右对角线限制进行或运算，得到所有不能放皇后的位置
        int allLim = limit & ~(colLim | leftDiaLim | rightDiaLim);

        int mostRightOne = 0;
        int ans = 0;
        while (allLim != 0) {
            // 取出最右边的1
            mostRightOne = allLim & (~allLim + 1);
            allLim = allLim - mostRightOne;
            ans += proess2(limit, colLim | mostRightOne, (leftDiaLim | mostRightOne) << 1, (rightDiaLim | mostRightOne) >>> 1);

        }

        return ans;

    }

}


