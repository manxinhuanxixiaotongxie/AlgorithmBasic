package leetcode.practice;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 接雨水问题2
 */
public class Code0407 {
    public int trapRainWater(int[][] heightMap) {
        /**
         *
         *
         * [[1,4,3,1,3,2],
         *  [3,2,1,3,2,4],
         *  [2,3,3,2,3,1]]
         *
         * 流程设计成什么样呢？
         * 使用小跟堆 先将周围一圈的数据放入小跟堆中
         *
         * 找到最小的一个数
         *
         * 怎么进行结算呢？
         *
         * 过程：
         * 小根堆维护的是高度最小的点
         * 当节点弹出的时候 意味着当前的点一定是瓶颈
         *
         */
        if (heightMap == null || heightMap.length == 0
                || heightMap[0].length == 0) {
            return 0;
        }

        PriorityQueue<Info> queue = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info o1, Info o2) {
                return o1.val - o2.val;
            }
        });

        // 将四周数加入到小跟堆中
        boolean[][] isEntered = new boolean[heightMap.length][heightMap[0].length];
        // 第0行
        for (int c = 0; c < heightMap[0].length - 1; c++) {
            isEntered[0][c] = true;
            queue.add(new Info(0, c, heightMap[0][c]));
        }
        // 最后一列
        for (int r = 0; r < heightMap.length - 1; r++) {
            isEntered[r][heightMap[0].length - 1] = true;
            queue.add(new Info(r, heightMap[0].length - 1, heightMap[r][heightMap[0].length - 1]));
        }
        // 最后一行
        for (int c = heightMap[0].length - 1; c > 0; c--) {
            isEntered[heightMap.length - 1][c] = true;
            queue.add(new Info(heightMap.length - 1, c, heightMap[heightMap.length - 1][c]));
        }
        // 第一列
        for (int r = heightMap.length - 1; r > 0; r--) {
            isEntered[r][0] = true;
            queue.add(new Info(r, 0, heightMap[r][0]));
        }

        int max = 0;
        int ans = 0;
        while (!queue.isEmpty()) {
            Info poll = queue.poll();
            max = Math.max(max, poll.val);
            // 最大值
            // 将当前节点的上下左右加入到小根堆中
            int curRow = poll.row;
            int curCol = poll.col;
            // 左边
            if (curCol > 0 && !isEntered[curRow][curCol - 1]) {
                isEntered[curRow][curCol - 1] = true;
                // 当前节点结算
                ans += Math.max(0, max - heightMap[curRow][curCol - 1]);
                queue.add(new Info(curRow, curCol - 1, heightMap[curRow][curCol - 1]));
            }
            // 右边
            if (curCol < heightMap[0].length - 1 && !isEntered[curRow][curCol + 1]) {
                isEntered[curRow][curCol + 1] = true;
                ans += Math.max(0, max - heightMap[curRow][curCol + 1]);

                queue.add(new Info(curRow, curCol + 1, heightMap[curRow][curCol + 1]));
            }
            // 下面
            if (curRow < heightMap.length - 1 && !isEntered[curRow + 1][curCol]) {
                isEntered[curRow + 1][curCol] = true;
                ans += Math.max(0, max - heightMap[curRow + 1][curCol]);
                queue.add(new Info(curRow + 1, curCol, heightMap[curRow + 1][curCol]));
            }
            // 上面
            if (curRow > 0 && !isEntered[curRow - 1][curCol]) {
                isEntered[curRow - 1][curCol] = true;
                ans += Math.max(0, max - heightMap[curRow - 1][curCol]);
                queue.add(new Info(curRow - 1, curCol, heightMap[curRow - 1][curCol]));
            }
        }
        // 维护一个小跟堆
        return ans;
    }

    class Info {
        int row;
        int col;
        int val;

        Info(int row, int col, int val) {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        Code0407 code0407 = new Code0407();
        int[][] arr = new int[][]{
                {1, 4, 3, 1, 3, 2},
                {3, 2, 1, 3, 2, 4},
                {2, 3, 3, 2, 3, 1}
        };
        System.out.println(code0407.trapRainWater(arr));
    }
}
