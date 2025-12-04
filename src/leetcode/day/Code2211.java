package leetcode.day;

/**
 * 在一条无限长的公路上有 n 辆汽车正在行驶。汽车按从左到右的顺序按从 0 到 n - 1 编号，每辆车都在一个 独特的 位置。
 * <p>
 * 给你一个下标从 0 开始的字符串 directions ，长度为 n 。directions[i] 可以是 'L'、'R' 或 'S' 分别表示第 i 辆车是向 左 、向 右 或者 停留 在当前位置。每辆车移动时 速度相同 。
 * <p>
 * 碰撞次数可以按下述方式计算：
 * <p>
 * 当两辆移动方向 相反 的车相撞时，碰撞次数加 2 。
 * 当一辆移动的车和一辆静止的车相撞时，碰撞次数加 1 。
 * 碰撞发生后，涉及的车辆将无法继续移动并停留在碰撞位置。除此之外，汽车不能改变它们的状态或移动方向。
 * <p>
 * 返回在这条道路上发生的 碰撞总次数 。
 *
 */
public class Code2211 {

    public int countCollisions(String s) {
        int ans = 0;
        char[] str = s.toCharArray();
        char last = str[0];
        int countR = last == 'R' ? 1 : 0;
        for (int i = 1; i < str.length; i++) {
            // 讨论情况
            // 当前位置向左
            char cur = str[i];
            if (cur == 'L') {
                if (last == 'S') {
                    ans += 1;
                    last = 'S';
                }else if (last == 'R') {
                    ans += countR+ 1;
                    last = 'S';
                    countR = 0;
                }else {
                    // 上一个位置是L
                    last = cur;
                }
            }else if (cur == 'R') {
                countR += 1;
                last = cur;
            }else {
                // 当前位置是S
                if (countR > 0) {
                    ans += countR;
                    countR = 0;
                }
                last = cur;
            }
        }

        return ans;
    }


    /**
     * 去掉前缀向左开、后缀向右开的车后，剩下的车必然会碰撞。
     *
     * 根据题意，两辆移动的车相撞，算碰撞两次；移动的车撞上静止的车，只算一次，那就算在移动的车上。
     *
     * 所以碰撞次数等于移动的车辆数，即剩余车辆数减去静止的车辆数。

     * @param s
     * @return
     */

    public int countCollisions2(String s) {
        int n = s.length();

        // 前缀向左的车不会发生碰撞
        int l = 0;
        while (l < n && s.charAt(l) == 'L') {
            l++;
        }

        // 后缀向右的车不会发生碰撞
        int r = n;
        while (r > l && s.charAt(r - 1) == 'R') {
            r--;
        }

        // 剩下非静止的车必然会碰撞
        int cnt = 0;
        for (int i = l; i < r; i++) {
            if (s.charAt(i) != 'S') {
                cnt++;
            }
        }
        return cnt;
    }
    static void main() {
        Code2211 code2211 = new Code2211();
        System.out.println(code2211.countCollisions("LLRLRLLSLRLLSLSSSS"));
        System.out.println(code2211.countCollisions2("LLRLRLLSLRLLSLSSSS"));
    }
}
