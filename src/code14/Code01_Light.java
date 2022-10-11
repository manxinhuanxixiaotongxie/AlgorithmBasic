package code14;

import java.util.HashSet;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-09-08 19:44
 */
public class Code01_Light {

    public static void main(String[] args) {
        Code01_Light code01_light = new Code01_Light();
        System.out.println("最少需要几盏灯:" + code01_light.minLight1("....."));
    }

    public int minLight1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();

        return process1(0,chars,new HashSet<>());

    }

    /**
     * str[index..]位置，自由选择放灯或者不放灯
     * str[0,index-1]位置已经做好决定了，已经放好灯了，放好的灯，记录在lights里
     * 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
     * @param index
     * @param chars
     * @param lights
     * @return
     */
    public int process1(int index, char[] chars, HashSet<Integer> lights) {
        /**
         * 到达字符串结束位置basecase
         */
        /**
         * 全排列
         * 假设现在数组是.....
         * 当前位置不放灯
         * 第一个位置不方法
         * 当前位置放灯
         */
        if (index == chars.length) {
            for (int i =0;i<chars.length;i++) {
                // 检查方案有效性
                if (chars[i] == '.') {
                    if (!lights.contains(i-1) && !lights.contains(i) && !lights.contains(i+1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else {
            // 当前位置不放灯 所有可能性
            // 当前位置不管是x还是.都可以不放灯
            // 当前位置不放灯，index+1位置返回最好的方案
            int no = process1(index+1,chars,lights);
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
                System.out.println(index + " 位置放灯");
                yes = process1(index + 1, chars, lights);
                lights.remove(index);
            }

            System.out.println("放灯方案:"+Math.min(no,yes));

            return Math.min(no,yes);
        }
    }
}
