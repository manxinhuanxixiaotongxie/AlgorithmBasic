package book_05string;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-05-09 16:21
 */
public class IsUniqueChars {
    /**
     * 给定一个字符类型的数组
     * 判断数组中的字符是否只出现了一次
     * 根据以下两种要求实现两个函数
     * 1.实现时间复杂度O（N）
     * 2.空间复杂度为O（1）前提，时间复杂度尽可能的低
     */

    /**
     * 方法1：时间复杂度O（N）
     *
     * @param chars
     * @return
     */
    public boolean isUniqueChars(char[] chars) {

        boolean[] map = new boolean[256];

        for (int i=0;i<chars.length;i++) {
            if (map[chars[i]]) {
                return false;
            }
            map[chars[i]] = true;
        }
        return true;
    }

    /**
     * 时间空间复杂度O（1）
     * 尽可能的实现时间复杂度最优
     *
     * @See classic/*
     * @Desc 经典排序算法代码及思路
     * @param chars
     * @return
     */
    public boolean isUniqueChars2(char[] chars) {

        boolean[] map = new boolean[256];

        for (int i=0;i<chars.length;i++) {
            if (map[chars[i]]) {
                return false;
            }
            map[chars[i]] = true;
        }
        return true;
    }

}
