package systemimprove.code11;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
 * 给你每一个项目开始的时间和结束的时间
 * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
 * 返回最多的宣讲场次。
 */
public class Code04_Meeting {

    public static int bestArrange(int[][] arr, int start) {
        return process(arr, start, 0);
    }

    public static int process(int[][] arr, int start, int done) {
        if (arr.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] >= start) {
                int[][] next = copyAndExcept(arr, i);
                max = Math.max(max, process(next, arr[i][1], done + 1));
            }
        }
        return max;
    }

    public static int[][] copyAndExcept(int[][] arr, int i) {
        int[][] ans = new int[arr.length - 1][2];
        int ansi = 0;
        for (int j = 0; j < arr.length; j++) {
            if (j != i) {
                ans[ansi++] = arr[j];
            }
        }
        return ans;
    }

    public static class Program {
        public int start;
        public int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    //贪心解法
    // 会议的开始时间和结束时间，都是数值，不会 < 0
    public static int bestArrange2(Program[] programs) {
        Arrays.sort(programs, new ProgramComparator());
        int timeLine = 0;
        int result = 0;
        // 依次遍历每一个会议，结束时间早的会议先遍历
        for (int i = 0; i < programs.length; i++) {
            if (timeLine <= programs[i].start) {
                result++;
                timeLine = programs[i].end;
            }
        }
        return result;
    }

    public static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.end - o2.end;
        }

    }

    public static void main(String[] args) {
        int[][] arr = {{1, 3}, {2, 5}, {3, 8}, {4, 6}};
        System.out.println(bestArrange(arr, 0));
    }

}
