package systemimprove.code11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 给定一个由字符串组成的数组strs，
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果
 */
public class Code01_MinLetter {

    public String minLetter(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return process(strs, new TreeSet<>()).first();
    }

    public  String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        TreeSet<String> ans = process(strs);
        return ans.size() == 0 ? "" : ans.first();
    }

    /**
     * 暴力解法
     * 暴力遍历所有组合情况 返回字典序最小的拼接字符串
     *
     *
     * @param strs
     * @return
     */
    public TreeSet<String> process(String[] strs,TreeSet<String> strings) {
        if (strs.length == 0) {
            strings.add("");
            return strings;
        }
        for (int i = 0;i < strs.length;i++) {
            String first = strs[i];
            String[] nexts = except(strs,i);
            TreeSet<String> next = process(nexts,strings);
            for (String s : next) {
                strings.add(first + s);
            }
        }
        return strings;

    }

    // strs中所有字符串全排列，返回所有可能的结果
    // String[] strs = ["ab","cd","ef"]

    /**
     * 暴力解法
     * 暴力的过程
     * "ab"  ==>
     * @param strs
     * @return
     */
    public static TreeSet<String> process(String[] strs) {
        TreeSet<String> ans = new TreeSet<>();
        if (strs.length == 0) {
            ans.add("");
            return ans;
        }
        for (int i = 0; i < strs.length; i++) {
            String first = strs[i];
            String[] nexts = removeIndexString(strs, i);
            TreeSet<String> next = process(nexts);
            for (String cur : next) {
                ans.add(first + cur);
            }
        }
        return ans;
    }

    // {"abc", "cks", "bct"}
    // 0 1 2
    // removeIndexString(arr , 1) -> {"abc", "bct"}
    public static String[] removeIndexString(String[] arr, int index) {
        int N = arr.length;
        String[] ans = new String[N - 1];
        int ansIndex = 0;
        for (int i = 0; i < N; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }


    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static class MyComparator implements Comparator<String> {
        @Override
        public int compare(String a, String b) {
            return (a + b).compareTo(b + a);
        }
    }

    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, new MyComparator());
        String res = "";
        for (int i = 0; i < strs.length; i++) {
            res += strs[i];
        }
        return res;
    }


    public String[] except(String[] strings,int index) {
        String[] ans = new String[strings.length - 1];
        int ansIndex = 0;
        for (int i = 0;i < strings.length;i++) {
            if (i != index) {
                ans[ansIndex++] = strings[i];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int len = 5;
        int strLen = 5;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(len, strLen);
            String[] arr1 = copyStringArray(arr);
            String[] arr2 = copyStringArray(arr);
            Code01_MinLetter test = new Code01_MinLetter();
            if (!test.lowestString1(arr1).equals(lowestString2(arr2))) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
