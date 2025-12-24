package leetcode.day;

import java.util.*;

/**
 * 给你一个整数 n ，表示有 n 个专家从 0 到 n - 1 编号。另外给你一个下标从 0 开始的二维整数数组 meetings ，其中 meetings[i] = [xi, yi, timei] 表示专家 xi 和
 * 专家 yi 在时间 timei 要开一场会。一个专家可以同时参加 多场会议 。最后，给你一个整数 firstPerson 。
 *
 * 专家 0 有一个 秘密 ，最初，他在时间 0 将这个秘密分享给了专家 firstPerson 。接着，这个秘密会在每次有知晓这个秘密的专家参加会议时进行传播。更正式的表达是，每次会议，如果专家 xi
 * 在时间 timei 时知晓这个秘密，那么他将会与专家 yi 分享这个秘密，反之亦然。
 *
 * 秘密共享是 瞬时发生 的。也就是说，在同一时间，一个专家不光可以接收到秘密，还能在其他会议上与其他专家分享。
 *
 * 在所有会议都结束之后，返回所有知晓这个秘密的专家列表。你可以按 任何顺序 返回答案。
 *
 */
public class Code2092 {
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        // 按照 time 从小到大排序
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);

        UnionFind uf = new UnionFind(n);
        // 一开始 0 和 firstPerson 都知道秘密
        uf.merge(firstPerson, 0);

        // 分组循环
        int m = meetings.length;
        for (int i = 0; i < m; ) {
            int start = i;
            int time = meetings[i][2];
            // 合并在同一时间发生的会议
            for (; i < m && meetings[i][2] == time; i++) {
                uf.merge(meetings[i][0], meetings[i][1]);
            }

            // 如果节点不和 0 在同一个集合，那么撤销合并，恢复成初始值
            for (int j = start; j < i; j++) {
                int x = meetings[j][0];
                int y = meetings[j][1];
                if (!uf.isSame(x, 0)) {
                    uf.reset(x);
                }
                if (!uf.isSame(y, 0)) {
                    uf.reset(y);
                }
            }
        }

        // 和 0 在同一个集合的专家都知道秘密
        List<Integer> ans = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            if (uf.isSame(k, 0)) {
                ans.add(k);
            }
        }
        return ans;
    }

    class UnionFind {
        private final int[] fa; // 代表元

        UnionFind(int n) {
            // 一开始有 n 个集合 {0}, {1}, ..., {n-1}
            // 集合 i 的代表元是自己
            fa = new int[n];
            for (int i = 0; i < n; i++) {
                fa[i] = i;
            }
        }

        // 返回 x 所在集合的代表元
        // 同时做路径压缩，也就是把 x 所在集合中的所有元素的 fa 都改成代表元
        public int find(int x) {
            // 如果 fa[x] == x，则表示 x 是代表元
            if (fa[x] != x) {
                fa[x] = find(fa[x]); // fa 改成代表元
            }
            return fa[x];
        }

        // 判断 x 和 y 是否在同一个集合
        public boolean isSame(int x, int y) {
            // 如果 x 的代表元和 y 的代表元相同，那么 x 和 y 就在同一个集合
            // 这就是代表元的作用：用来快速判断两个元素是否在同一个集合
            return find(x) == find(y);
        }

        // 把 from 所在集合合并到 to 所在集合中
        public void merge(int from, int to) {
            int x = find(from);
            int y = find(to);
            fa[x] = y; // 合并集合
        }

        public void reset(int x) {
            fa[x] = x;
        }
    }


    static void main() {
        Code2092 code2092 = new Code2092();
        int n = 5;
        int[][] meetings = {{1,4,3}, {0,4,3}};
        int firstPerson = 3;
        List<Integer> res = code2092.findAllPeople(n, meetings, firstPerson);
        System.out.println(res);
    }
}
