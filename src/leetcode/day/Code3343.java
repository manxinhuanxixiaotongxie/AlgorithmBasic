package leetcode.day;

/**
 * 给你一个由字符 'N'、'S'、'E' 和 'W' 组成的字符串 s，其中 s[i] 表示在无限网格中的移动操作：
 * <p>
 * 'N'：向北移动 1 个单位。
 * 'S'：向南移动 1 个单位。
 * 'E'：向东移动 1 个单位。
 * 'W'：向西移动 1 个单位。
 * 初始时，你位于原点 (0, 0)。你 最多 可以修改 k 个字符为任意四个方向之一。
 * <p>
 * 请找出在 按顺序 执行所有移动操作过程中的 任意时刻 ，所能达到的离原点的 最大曼哈顿距离 。
 * <p>
 * 曼哈顿距离 定义为两个坐标点 (xi, yi) 和 (xj, yj) 的横向距离绝对值与纵向距离绝对值之和，即 |xi - xj| + |yi - yj|。
 */
public class Code3343 {
    private int left;

    /**
     * 分析规律：
     * <p>
     * 对于任意给定的一个字符串 我们可以求出字符串的最大哈曼顿距离。
     * * |sum(N) - sum(S)| + |sum(E) - sum(W)|
     * 其中 sum(N) 表示 N 的个数，sum(S) 表示 S 的个数，sum(E) 表示 E 的个数，sum(W) 表示 W 的个数。
     * <p>
     * 当我们尝试修改 字符串：
     * 1.第一种情况 增加横向或者纵向的字符较大的字符 该字符对应的曼哈顿距离会增加2
     * 2.第二种情况 减少横向或者纵向的字符较小的字符 该字符对应的曼哈顿距离会减少2
     * 3.不修改字符的情况下，曼哈顿距离不会发生变化。
     *
     * @param s
     * @param k
     * @return
     */
    public int maxDistance(String s, int k) {
        int ans = 0;
        // 四个方向 N S W E 最大的就是W 定一个数组 长度是W+1
        int[] cnt = new int['X']; // 'W' + 1 = 'X'
        for (char ch : s.toCharArray()) {
            cnt[ch]++;
            left = k;
            ans = Math.max(ans, f(cnt['N'], cnt['S']) + f(cnt['E'], cnt['W']));
        }
        return ans;
    }

    private int f(int a, int b) {
        /**
         * d指的是操作次数
         * 操作次数的去取值 只能是东西向 或者南北向的最小值进行处理 某个方向字符不能变成小于0
         * 也不能超过K
         */
        int d = Math.min(Math.min(a, b), left);
        left -= d;
        // 每次操作 对应的距离就会增加2
        return Math.abs(a - b) + d * 2;
    }

    public int maxDistance2(String s, int k) {
        int ans = 0;
        char[] str = s.toCharArray();
        int west = 0;
        int east = 0;
        int north = 0;
        int south = 0;
        for (char ch: str) {
            switch (ch)  {
                case 'N':
                    north++;
                    break;

                case 'S':
                    south++;
                    break;
                case 'E':
                    east++;
                    break;
                case 'W':
                    west++;
                    break;
            }
            // 每一步
            // 南北的交换步数
            int ns = Math.min(Math.min(north, south),k);
            int ne = Math.min(Math.min(east, west), k - ns);
            ans = Math.max(ans, Math.abs(north - south) + ns * 2 + Math.abs(east - west) + ne * 2);
        }
        return ans;
    }

}
