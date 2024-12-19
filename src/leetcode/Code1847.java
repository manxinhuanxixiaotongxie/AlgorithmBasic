package leetcode;

import java.util.Arrays;

public class Code1847 {
    /**
     * 不得行
     *
     * @param rooms
     * @param queries
     * @return
     */
    public int[] closestRoom(int[][] rooms, int[][] queries) {
        // 模拟整个过程
        // [1,4],[2,3],[3,5],[4,1],[5,2]
        // [4,1],[5,2],[2,3],[1,4],[3,5]
        // [2,3]
        // 面积相等 房间号小的排前面
        // 面积不相等 面积小的排面前
        Arrays.sort(rooms, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] curQuery = queries[i];
            // 获取到当前查询
            int curRoom = curQuery[0];
            int roomSize = curQuery[1];
            int near = getNear(rooms, roomSize);
            if (near == -1) {
                ans[i] = -1;
            } else {
                ans[i] = rooms[near][0];
            }
        }
        return ans;
    }

    private int getNear(int[][] rooms, int roomSize) {
        int L = 0;
        int R = rooms.length - 1;
        int ans = -1;
        while (L <= R) {
            int mid = L + (R - L) / 2;
            if (rooms[mid][1] >= roomSize) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code1847 code1847 = new Code1847();
        int[][] rooms = {{2, 2}, {1, 2}, {3, 2}};
        int[][] queries = {{3, 1}, {3, 3}, {5, 2}};
        int[] res = code1847.closestRoom(rooms, queries);
        for (int re : res) {
            System.out.println(re);
        }
    }
}
