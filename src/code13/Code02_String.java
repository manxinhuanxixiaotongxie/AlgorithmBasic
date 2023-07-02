package code13;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Description https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/description/
 * @Author Scurry
 * @Date 2023-06-30 16:45
 */
public class Code02_String {

    public static void main(String[] args) {
        permutation("abcd");
        zixulie("abcd");
    }

    /**
     * 字符串全排列
     */
    public static String[] permutation(String str) {
        if (str == null) {
            return null;
        }
        HashSet<String> list = new HashSet<>();
        process1(str.toCharArray(), 0, "", list, new HashSet<>());
        String[] strings = new String[list.size()];
        Object[] objects = list.toArray();
        for (int i = 0; i < objects.length; i++) {
            strings[i] = (String) objects[i];
        }
        return strings;

    }

    public static List<String> zixulie(String str) {
        List<String> ans = new ArrayList<>();
        process2(str.toCharArray(),0,ans,"");
        return ans;
    }

    /**
     * 求子序列
     * @param chars
     * @param index
     * @param ans
     * @param path
     */
    public static void process2(char[] chars, int index, List<String> ans, String path) {
        if (index == chars.length) {
            ans.add(path);
            System.out.println("子序列："+ path);
            return;
        }
        // no
        process2(chars,index+1,ans,path);

        // yes
        process2(chars,index+1,ans,path+chars[index]);

    }

    /**
     * 整个递归过程如下：
     * 以abcd为例
     *
     * abcd 整个过程结束以后 弹出  再次循环  直到C弹出 继续递归选出D 再继续递归弹出B 继续递归   这样一个循环之后得到 abcd abdc acdb ...以a开头的全排列
     * 继续弹出  以b开头   然后以C开头  再以C开头等等
     *
     * 整个递归过如下：
     * abcd
     * abdc
     * acbd
     * acdb
     * adbc
     * adcb
     *
     * bacd
     * badc
     * bcad
     * bcda
     * bdac
     * bdca
     *
     * cabd
     * cadb
     * cbad
     * cbda
     * cdab
     * cdba
     *
     * dabc
     * dacb
     * dbac
     * dbca
     * dcab
     * dcba
     *
     * @param chars
     * @param index
     * @param path
     * @param list
     * @param set
     */
    public static void process1(char[] chars, int index, String path, HashSet<String> list, HashSet<Integer> set) {

        if (index == chars.length) {
            list.add(path);
//            System.out.println(path);
            return;
        }


        for (int i = 0; i < chars.length; i++) {
            if (!set.contains(i)) {
                set.add(i);
                process1(chars, index + 1, path + chars[i], list, set);
                set.remove(i);
            }
        }
    }
}
