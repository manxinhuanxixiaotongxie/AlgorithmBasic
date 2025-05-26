package code13;

import java.util.HashSet;

/**
 * @Description
 * @Author Scurry
 *
 * @Date 2023-06-29 19:34
 */
public class Code01_Light {

    /**
     * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
     * ‘X’表示墙，不能放灯，也不需要点亮
     * ‘.’表示居民点，可以放灯，需要点亮
     * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
     * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
     */
    public int light(String str) {
        if (str == null) {
            return 0;
        }
        return process(str.toCharArray(), 0, new HashSet<>());
    }

    /**
     * 递归过程拆解：
     * 【X . . . X】
     *
     * 这个题目有贪心解法
     *
     *
     *
     * @param chars
     * @param index
     * @param lights
     * @return
     */
    public int process(char[] chars, int index, HashSet<Integer> lights) {
        if (index == chars.length) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == '.') {
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {

            int no = process(chars, index + 1, lights);
            int yes = Integer.MAX_VALUE;

            for (int i = 0; i < chars.length; i++) {
                // 当前位置是.可以考虑放灯、不放灯总共有多少种可能
                if (chars[i] == '.') {
                    // 当前位置放灯
                    lights.add(i);
                    yes = process(chars, index + 1, lights);
                    lights.remove(i);

                }
            }
            return Math.max(no, yes);
        }
    }

    public int minLight2(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        int lights = 0;
        while (index < chars.length) {
            if (chars[index] == 'X') {
                index++;
            } else {
                lights++;
                if (index + 1 == chars.length) {
                    break;
                } else {
                    if (chars[index + 1] == 'X') {
                        index += 2;
                    } else {
                        index += 3;
                    }
                }
            }
        }

        return lights;
    }
}
