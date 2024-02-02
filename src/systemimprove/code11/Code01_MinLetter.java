package systemimprove.code11;

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

    /**
     * 暴力解法
     * 暴力遍历所有组合情况 返回字典序最小的拼接字符串
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

    public String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * 26) + 'a');
        }
        return String.valueOf(ans);
    }
}
