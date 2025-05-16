package code14;

import java.util.HashSet;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-09-08 19:44
 */
public class Code01_Light {


    public int minLight1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();

        return process1(0, chars, new HashSet<>());

    }

    /**
     * str[index..]位置，自由选择放灯或者不放灯
     * str[0,index-1]位置已经做好决定了，已经放好灯了，放好的灯，记录在lights里
     * 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
     *
     * @param index
     * @param chars
     * @param lights
     * @return
     */
    public int process1(int index, char[] chars, HashSet<Integer> lights) {
        /**
         * 全排列
         * 假设现在数组是.....
         * 当前位置不放灯
         * 第一个位置不方法
         * 当前位置放灯
         */
        if (index == chars.length) {
            for (int i = 0; i < chars.length; i++) {
                // 检查方案有效性
                if (chars[i] == '.') {
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            // 当前位置不放灯 所有可能性
            // 当前位置不管是x还是.都可以不放灯
            // 当前位置不放灯，index+1位置返回最好的方案
            int no = process1(index + 1, chars, lights);
            //假设数组是.....
            //
            // process1    <------   ------->index0 ------->index1放灯  ------>index2 ------>index3 ------>index4
            // process2    <------  ------->index2放灯  ------>index3 ------>index4
            // process3    <------  ------->index3放灯  ----->index4
            // process4返回 index4 放灯 index5 返回

            int yes = Integer.MAX_VALUE;
            // index是.可以做出一个放灯的决定
            if (chars[index] == '.') {
                lights.add(index);
                // 当前位置放灯 所有可能性
                yes = process1(index + 1, chars, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

    /**
     * 贪心
     *
     * @param str
     * @return
     */
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

    public static String randomString(int maxLength) {
        char[] res = new char[((int) (Math.random() * maxLength)) + 1];
        for (int i = 0; i < res.length; i++) {
            res[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        int testTimes = 1000000;
        int maxLength = 20;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String randomString = randomString(maxLength);
            int ans1 = new Code01_Light().minLight1(randomString);
            int ans2 = new Code01_Light().minLight2(randomString);
            if (ans1 != ans2) {
                System.out.println("Oops");
            }
        }
        System.out.println("test end");
    }

}
