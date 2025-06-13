package leetcode.day;

import java.util.PriorityQueue;

/**
 * 班里有 m 位学生，共计划组织 n 场考试。给你一个下标从 0 开始、大小为 m x n 的整数矩阵 score ，
 * 其中每一行对应一位学生，而 score[i][j] 表示第 i 位学生在第 j 场考试取得的分数。矩阵 score 包含的整数 互不相同 。
 * <p>
 * 另给你一个整数 k 。请你按第 k 场考试分数从高到低完成对这些学生（矩阵中的行）的排序。
 * <p>
 * 返回排序后的矩阵。
 */
public class Code2545 {

    /**
     * 尽管AC了   但是时间复杂度不好
     *
     * @param score
     * @param k
     * @return
     */
    public int[][] sortTheStudents(int[][] score, int k) {
        int[][] ans = new int[score.length][score[0].length];
        PriorityQueue<Info> queue = new PriorityQueue<>((o1, o2) -> score[o2.r][k] - score[o1.r][k]);
        for (int r = 0; r < score.length; r++) {
            queue.add(new Info(r, k, score[r][k]));
        }
        int index = 0;
        while (!queue.isEmpty()) {
            Info poll = queue.poll();
            // 行
            int r = poll.r;
            for (int c = 0; c < score[0].length; c++) {
                ans[index][c] = score[r][c];
            }
            index++;
        }
        // 根据i行k列的位置进行排序
        return ans;
    }


    class Info {
        int r;
        int c;
        int val;

        Info(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
    }
}
