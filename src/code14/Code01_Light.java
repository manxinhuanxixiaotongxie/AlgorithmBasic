package code14;

import java.util.HashSet;
import java.util.Map;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-09-08 19:44
 */
public class Code01_Light {

    public static void main(String[] args) {
        Code01_Light code01_light = new Code01_Light();
        System.out.println(code01_light.minLight1("XX.X"));
    }

    public int minLight1(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] chars = str.toCharArray();

        return process1(0,chars,new HashSet<>());

    }

    public int process1(int index, char[] chars, HashSet<Integer> lights) {
        /**
         * 到达字符串结束位置
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
            int no = process1(index+1,chars,lights);
            //假设长度是XX.X
            // process1   <------ process2 <------    process3 <----     process4返回

            int yes = Integer.MAX_VALUE;
            if (chars[index] == '.') {
                lights.add(index);
                // 当前位置放灯 所有可能性
                yes = process1(index + 1, chars, lights);
                lights.remove(index);
            }

            return Math.min(no,yes);
        }
    }
}
