package leetcode.week.code507;

/**
 * 给你一个由字符 'U'、'D'、'L'、'R' 和 '_' 组成的字符串 moves。
 * <p>
 * 从原点 (0, 0) 出发，每个字符表示二维平面上的一次移动：
 * <p>
 * 'U'：向上移动 1 个单位。
 * 'D'：向下移动 1 个单位。
 * 'L'：向左移动 1 个单位。
 * 'R'：向右移动 1 个单位。
 * '_'：可以独立地替换为 'U'、'D'、'L' 或 'R' 中的任意一个字符。
 * 返回执行完所有移动后，能够达到的距离原点的 最大曼哈顿距离 。
 * <p>
 * 两点 (x1, y1) 和 (x2, y2) 之间的 曼哈顿距离 为 |x1 - x2| + |y1 - y2|。
 */
public class Code01 {
    boolean init = false;
    int[] help1;
    int[] help2;

    // 定义四个方向进行计算
    public int[] help() {
        help1 = new int[]{-1, +1, 0, 0};
        help2 = new int[]{0, 0, -1, +1};

        init = true;
        return help1;
    }

    /**
     * 超时
     *
     * @param moves
     * @return
     */
    public int maxDistance(String moves) {
        if (!init) {
            help();
        }
        return process(moves.toCharArray(), moves.length(), 0, 0, 0);
    }

    public int process(char[] movesChar, int n, int index, int x, int y) {
        if (index == n) {
            return Math.abs(x) + Math.abs(y);
        } else {
            // 四个方向进行替换
            int max = 0;
            if (movesChar[index] == '_') {
                for (int i = 0; i < help1.length; i++) {
                    max = Math.max(max, process(movesChar, n, index + 1, x + help1[i], y + help2[i]));
                }
            } else {
                if (movesChar[index] == 'L') {
                    max = Math.max(max, process(movesChar, n, index + 1, x - 1, y));
                } else if (movesChar[index] == 'R') {
                    max = Math.max(max, process(movesChar, n, index + 1, x + 1, y));
                } else if (movesChar[index] == 'D') {
                    max = Math.max(max, process(movesChar, n, index + 1, x, y - 1));
                } else if (movesChar[index] == 'U') {
                    max = Math.max(max, process(movesChar, n, index + 1, x, y + 1));
                }
            }
            return max;
        }
    }

    public int maxDistance2(String moves) {
        int cntL = 0, cntR = 0, cntU = 0, cntD = 0, cntBlank = 0;
        for (char c : moves.toCharArray()) {
            if (c == 'L') cntL++;
            else if (c == 'R') cntR++;
            else if (c == 'U') cntU++;
            else if (c == 'D') cntD++;
            else cntBlank++;
        }
        return Math.abs(cntR - cntL) + Math.abs(cntU - cntD) + cntBlank;
    }

}
