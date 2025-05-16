package systemimprove.code11;


import java.util.HashSet;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Code02_Light {

    public static int getLights(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        return process(str, 0, new HashSet<>());

    }

    public static int process(char[] str, int index, HashSet<Integer> set) {
        if (index == str.length) {
            for (int i = 0; i < str.length; i++) {
                if (str[i] == '.') {
                    if (!set.contains(i - 1) && !set.contains(i) && !set.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return set.size();
        } else {
            int no = process(str, index + 1, set);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                set.add(index);
                yes = process(str, index + 1, set);
                // 恢复现场
                set.remove(index);
            }
            return Math.min(yes, no);
        }
    }

    public static String randomString(int len) {
        char[] res = new char[(int) (Math.random() * len) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static int minLight2(String road) {
        char[] str = road.toCharArray();
        int i = 0;
        int light = 0;
        while (i < str.length) {
            if (str[i] == 'X') {
                i++;
            } else {
                light++;
                if (i + 1 == str.length) {
                    break;
                } else { // 有i位置 i+ 1 X .
                    if (str[i + 1] == 'X') {
                        i = i + 2;
                    } else {
                        i = i + 3;
                    }
                }
            }
        }
        return light;
    }

    // 贪心解法
    public static int getLights2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int lights = 0;
        int i = 0;
        for (; i < str.length; ) {
            if (str[i] == '.') {
                if (i + 1 == str.length) {
                    lights++;
                    break;
                } else {
                    if (str[i + 1] == 'X') {
                        lights++;
                        i = i + 2;
                    } else {
                        lights++;
                        i = i + 3;
                    }
                }

            } else {
                i++;
            }
        }
        return lights;
    }

    public static void main(String[] args) {
        int len = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            String test = randomString(len);
            int ans1 = getLights(test);
            int ans2 = minLight2(test);
            int ans3 = getLights2(test);
//            int ans3 = minLight3(test);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("oops!");
            }
        }
        System.out.println("finish!");
    }
}
