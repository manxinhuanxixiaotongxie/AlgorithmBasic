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
        permutation("aab");

        System.out.println("=====================");
//        zixulie("abcd");
    }

    /**
     * 字符串全排列
     */
    public static String[] permutation(String str) {
        if (str == null) {
            return null;
        }
        HashSet<String> list = new HashSet<>();
        process1Test(str.toCharArray(), 0, "", list);
        String[] strings = new String[list.size()];
        Object[] objects = list.toArray();
        for (int i = 0; i < objects.length; i++) {
            strings[i] = (String) objects[i];
        }
        return strings;

    }

    public static List<String> zixulie(String str) {
        List<String> ans = new ArrayList<>();
        process2(str.toCharArray(), 0, ans, "");
        return ans;
    }

    /**
     * 求子序列
     *
     * @param chars
     * @param index
     * @param ans
     * @param path
     */
    public static void process2(char[] chars, int index, List<String> ans, String path) {
        if (index == chars.length) {
            ans.add(path);
            System.out.println("子序列：" + path);
            return;
        }
        // no
        process2(chars, index + 1, ans, path);

        // yes
        process2(chars, index + 1, ans, path + chars[index]);

    }

    /**
     * 整个递归过程如下：
     * 以abcd为例
     * <p>
     * abcd 整个过程结束以后 弹出  再次循环  直到C弹出 继续递归选出D 再继续递归弹出B 继续递归   这样一个循环之后得到 abcd abdc acdb ...以a开头的全排列
     * 继续弹出  以b开头   然后以C开头  再以C开头等等
     * <p>
     * 整个递归过如下：
     * abcd
     * abdc
     * acbd
     * acdb
     * adbc
     * adcb
     * <p>
     * bacd
     * badc
     * bcad
     * bcda
     * bdac
     * bdca
     * <p>
     * cabd
     * cadb
     * cbad
     * cbda
     * cdab
     * cdba
     * <p>
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
    public static void process1(char[] chars, int index, String path, HashSet<String> list, HashSet<String> set) {

        if (index == chars.length) {
            list.add(path);
        }

        /**
         *以abcd为例
         *process1(chars, index + 1, path + chars[i], list, set);
         *
         * 递归过程：
         *
         *
         *
         */
        for (int i = 0; i < chars.length; i++) {
            if (!set.contains(i)) {
                set.add(String.valueOf(i));
                process1(chars, index + 1, path + chars[i], list, set);
                set.remove(i);
            }
        }
    }


    /**
     * 这段代码是用于求字符串全排列的两个递归函数，分别是 process1 和 process1Test。它们的参数含义如下：
     * <p>
     * chars：表示待排列的字符数组。
     * index：表示当前已经排列到的位置。
     * path：表示已经排列好的路径。
     * list：表示存储所有排列结果的 HashSet 集合。
     * set：表示已经使用过的字符下标的集合。
     * 其中，process1 函数和之前的 process1Test 函数相比，多了一个参数 set，用于存储已经使用过的字符下标。在函数的开头，同样进行了一个判断，如果当前已经排列到了字符数组的末尾，说明已经排列完毕，将路径添加到结果集合中，并直接返回。
     * <p>
     * 如果还没有排列完毕，就需要进行递归调用。在递归调用之前，需要遍历所有的字符，对于每个字符，如果它没有被使用过，就将它加入路径中，然后进行递归调用，将 index 加 1。
     * 递归调用结束后，需要将当前字符从路径中删除，并从已经使用过的字符下标集合中删除，以便进行下一次排列。
     * <p>
     * 在 process1Test 函数中，注释中的 aab 是一个示例，它展示了在字符数组中有重复字符时，如果不加判断会产生重复排列的情况。在这个例子中，如果不加判断，会产生如下的重复排列：
     * <p>
     * aab
     * aba
     * aba
     * aab
     * baa
     * baa
     * 因此，在 process1 函数中，加入了一个判断，只有当当前字符没有被使用过时，才进行递归调用。这样可以避免重复排列的情况。
     *
     * @param chars
     * @param index
     * @param path
     * @param list
     */
    public static void process1Test(char[] chars, int index, String path, HashSet<String> list) {

        if (index == chars.length) {
            list.add(path);
            System.out.println(path);
        }

        /**
         *
         *                  以aab为例
         *
         *
         * process1Test(chars, index + 1, path + chars[i], list, set);
         *
         * 递归过程：
         *
         *process1Test(chars, 0, "", list);
         *   process1Test(chars, 1, "a", list);
         *   process1Test(chars, 2, "aa", list);
         *   process1Test(chars, 3, "aab", list);
         *
         *
         *    list.add("aab");
         *    System.out.println("aab");
         *
         *
         *    process1Test(chars, 2, "aa", list);
         *    process1Test(chars, 3, "aba", list);
         *    list.add("aba");
         *    System.out.println("aba");
         *    process1Test(chars, 2, "aa", list);
         *    process1Test(chars, 3, "baa", list);
         *    list.add("baa");
         *    System.out.println("baa");
         *    process1Test(chars, 1, "a", list);
         *    ........
         *
         *
         *
         *
         */


        for (int i = 0; i < chars.length; i++) {
            System.out.println("执行1");
            process1Test(chars, index + 1, path + chars[i], list);
            System.out.println("执行2");

        }
    }

    /**
     * 整个递归过程如下：
     * 以abcd为例
     * <p>
     * abcd 整个过程结束以后 弹出  再次循环  直到C弹出 继续递归选出D 再继续递归弹出B 继续递归   这样一个循环之后得到 abcd abdc acdb ...以a开头的全排列
     * 继续弹出  以b开头   然后以C开头  再以C开头等等
     * <p>
     * 整个递归过如下：
     * abcd
     * abdc
     * acbd
     * acdb
     * adbc
     * adcb
     * <p>
     * bacd
     * badc
     * bcad
     * bcda
     * bdac
     * bdca
     * <p>
     * cabd
     * cadb
     * cbad
     * cbda
     * cdab
     * cdba
     * <p>
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
     * @param set
     * @param set
     */
    public static void process2(char[] chars, int index, String path, HashSet<String> set) {

        if (index == chars.length) {
            set.add(path);
        }

        /**
         *
         */
        for (int i = index; i < chars.length; i++) {
            swap(chars, i, index);
            process2(chars, index + 1, path + chars[i], set);
            swap(chars, i, index);
        }
    }


    public static void process3(char[] chars, int index, String path, HashSet<String> set) {

        if (index == chars.length) {
            set.add(path);
        }

        /**
         *去重：两种思路：
         * 1.全量递归 根据set进行去重
         * 2.在递归过程中，出现了已经出现的就进行结束
         *
         * 解释一下这段代码，以aba为例
         *                          f(0)
         *
         *
         *
         *                 f(aba)   f(baa)    f(aba)
         *
         * 哪个字符来到第一个位置，全部都是有可能的，所以需要遍历一遍，然后将当前字符和第一个位置的字符交换
         * 去重是个什么思路：
         * 这个visited这样理解：每一步递归都新生成一个，当递归到当前步的时候，就是当前步的visited，当递归结束的时候，就是上一步的visited
         * 不共享
         *
         *
         *
         */
        boolean[] visited = new boolean[256];
        for (int i = index; i < chars.length; i++) {
            if (!visited[chars[i]]) {
                visited[chars[i]] = true;
                swap(chars, i, index);
                process3(chars, index + 1, path + chars[i], set);
                swap(chars, i, index);
            }
        }
    }

    public static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;

    }
}
