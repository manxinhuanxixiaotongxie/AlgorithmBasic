package leetcode.day;

import java.util.*;

/**
 * 给你一个正整数 n，表示一个 n x n 的城市，同时给定一个二维数组 buildings，其中 buildings[i] = [x, y] 表示位于坐标 [x, y] 的一个 唯一 建筑。
 *
 * 如果一个建筑在四个方向（左、右、上、下）中每个方向上都至少存在一个建筑，则称该建筑 被覆盖 。
 *
 * 返回 被覆盖 的建筑数量。
 *
 */
public class Code3531 {
    public int countCoveredBuildings(int n, int[][] buildings) {
        Map<Integer, List<Integer>> xMap = new HashMap<>();
        Map<Integer, List<Integer>> yMap = new HashMap<>();
        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];
            xMap.computeIfAbsent(x,_ -> new ArrayList<>()).add(y);
            yMap.computeIfAbsent(y,_ -> new ArrayList<>()).add(x);
        }
        // 对map中的list进行排序
        for (List<Integer> yList : xMap.values()) {
            yList.sort(Integer::compareTo);
        }
        for (List<Integer> xList : yMap.values()) {
            xList.sort(Integer::compareTo);
        }
        int ans = 0;
        // 再次遍历
        for (int[] building : buildings) {
            int x = building[0];
            int y = building[1];
            // x方向获得列表
            // y方向获得列表
            if(xMap.get(x) != null && yMap.get(y) != null) {
                int xSize = xMap.get(x).size();
                int ySize = yMap.get(y).size();
                if (xMap.get(x).get(xSize -1) > y && xMap.get(x).get(0) < y &&
                        yMap.get(y).get(ySize -1) > x && yMap.get(y).get(0) < x) {
                    ans++;
                }
            }
        }
        return ans;
    }

    /**
     * 只需要统计行列的最大值与最小值即可 不需要排序
     *
     * @param n
     * @param buildings
     * @return
     */
    public int countCoveredBuildings2(int n, int[][] buildings) {
        // n从1开始
        int[] rowMin = new int[n +1];
        int[] rowMax = new int[n +1];
        int[] colMin = new int[n +1];
        int[] colMax = new int[n +1];
        Arrays.fill(rowMin, Integer.MAX_VALUE);
        Arrays.fill(colMin, Integer.MAX_VALUE);
        // 只是只是需要维护当前行列的最大值与最小值 不需要维护一个列表 放置进去之后再循环遍历进行排序
        // 遍历
        for (int i = 0; i < buildings.length; i++) {
            int x = buildings[i][0];
            int y = buildings[i][1];
            rowMin[y] = Math.min(rowMin[y], x);
            rowMax[y] = Math.max(rowMax[y], x);
            colMin[x] = Math.min(colMin[x], y);
            colMax[x] = Math.max(colMax[x], y);
        }
        // 再次遍历
        int ans = 0;
        for (int i = 0; i < buildings.length; i++) {
            int x = buildings[i][0];
            int y = buildings[i][1];
            if (rowMin[y] < x && rowMax[y] > x && colMin[x] < y && colMax[x] > y) {
                ans++;
            }
        }
        return ans;
    }
}
