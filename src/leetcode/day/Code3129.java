package leetcode.day;

/**
 * 给你 3 个正整数 zero ，one 和 limit 。
 * <p>
 * 一个 二进制数组 arr 如果满足以下条件，那么我们称它是 稳定的 ：
 * <p>
 * 0 在 arr 中出现次数 恰好 为 zero 。
 * 1 在 arr 中出现次数 恰好 为 one 。
 * arr 中每个长度超过 limit 的 子数组 都 同时 包含 0 和 1 。
 * 请你返回 稳定 二进制数组的 总 数目。
 * <p>
 * 由于答案可能很大，将它对 109 + 7 取余 后返回。
 *
 */
public class Code3129 {

    /**
     *
     * 超时 无法改动态规划
     *
     * @param zero
     * @param one
     * @param limit
     * @return
     */
    public int numberOfStableArrays1(int zero, int one, int limit) {
        // 数组的长度是size
        int size = zero + one;
        int[] arr = new int[size];
        return process1(arr, size, 0, zero, one, limit);
    }

    public int process1(int[] arr, int size, int index, int restZero, int resetOne, int limit) {
        if (index == size) {
            // 校验
            for (int i = 0; i < size - limit; i++) {
                for (int j = i + limit + 1; j <= size; j++) {
                    boolean hasZero = false;
                    boolean hasOne = false;
                    for (int k = i; k < j; k++) {
                        if (arr[k] == 0) {
                            hasZero = true;
                        } else {
                            hasOne = true;
                        }
                    }
                    if (!hasZero || !hasOne) {
                        return 0;
                    }
                }
            }
            return 1;
        } else {
            int res = 0;
            if (restZero > 0) {
                arr[index] = 0;
                res += process1(arr, size, index + 1, restZero - 1, resetOne, limit);
            }
            if (resetOne > 0) {
                arr[index] = 1;
                res += process1(arr, size, index + 1, restZero, resetOne - 1, limit);
            }
            return res;
        }
    }

    /**
     * 占用空间
     *
     * @param zero
     * @param one
     * @param limit
     * @return
     */
    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][][] dp = new int[zero + 1][one + 1][3][limit + 2];
        for (int i = 0; i <= zero; i++)
            for (int j = 0; j <= one; j++)
                for (int k = 0; k < 3; k++)
                    for (int l = 0; l <= limit + 1; l++)
                        dp[i][j][k][l] = -1;
        return process(zero, one, -1, 0, limit, dp);
    }

    public int process(int restZero, int restOne, int lastNum, int cnt, int limit, int[][][][] dp) {
        if (cnt > limit) return 0;
        if (restZero == 0 && restOne == 0) return 1;
        int idx = lastNum + 1;
        if (dp[restZero][restOne][idx][cnt] != -1) return dp[restZero][restOne][idx][cnt];
        int res = 0;
        if (restZero > 0) {
            if (lastNum == 0) {
                res = (res + process(restZero - 1, restOne, 0, cnt + 1, limit, dp)) % 1000000007;
            } else {
                res = (res + process(restZero - 1, restOne, 0, 1, limit, dp)) % 1000000007;
            }
        }
        if (restOne > 0) {
            if (lastNum == 1) {
                res = (res + process(restZero, restOne - 1, 1, cnt + 1, limit, dp)) % 1000000007;
            } else {
                res = (res + process(restZero, restOne - 1, 1, 1, limit, dp)) % 1000000007;
            }
        }
        dp[restZero][restOne][idx][cnt] = res;
        return res;
    }

    // 方法2：用Map做记忆化，节省空间 但是在3130超时
    public int numberOfStableArraysMap(int zero, int one, int limit) {
        return processMap(zero, one, -1, 0, limit, new java.util.HashMap<>());
    }

    private int processMap(int restZero, int restOne, int lastNum, int cnt, int limit, java.util.Map<String, Integer> memo) {
        if (cnt > limit) return 0;
        if (restZero == 0 && restOne == 0) return 1;
        String key = restZero + "," + restOne + "," + lastNum + "," + cnt;
        if (memo.containsKey(key)) return memo.get(key);
        int res = 0;
        if (restZero > 0) {
            if (lastNum == 0) {
                res = (res + processMap(restZero - 1, restOne, 0, cnt + 1, limit, memo)) % 1000000007;
            } else {
                res = (res + processMap(restZero - 1, restOne, 0, 1, limit, memo)) % 1000000007;
            }
        }
        if (restOne > 0) {
            if (lastNum == 1) {
                res = (res + processMap(restZero, restOne - 1, 1, cnt + 1, limit, memo)) % 1000000007;
            } else {
                res = (res + processMap(restZero, restOne - 1, 1, 1, limit, memo)) % 1000000007;
            }
        }
        memo.put(key, res);
        return res;
    }
}
