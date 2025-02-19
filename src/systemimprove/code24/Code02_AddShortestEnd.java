package systemimprove.code24;

/**
 * manacher练习题
 *
 * 给定一个字符串str1，只能往str1的后面添加字符变成str2，要求str2整体都是回文串且最短
 */
public class Code02_AddShortestEnd {

    public String addShortTestEnd(String s) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return "";
        }
        int R = -1;
        int c = 0;
        char[] str = getDouble(s);
        int[] pArr = new int[str.length];
        int maxLength = -1;
        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * c - i], R - i) : 1;
            while (i + pArr[i] < str.length && i - pArr[i] > -1) {
                if (str[i + pArr[i]] == str[i - pArr[i]]) {
                    pArr[i]++;
                } else {
                    break;
                }
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                c = i;
            }
            if (R == str.length) {
                maxLength = pArr[i];
                break;
            }

        }
        // 已经求出来了最长回文半径的长度（目前求的是加工之后的）
        // 那么需要补充的字符的长度就是原始字符串的长度减去-（最长回文半径的长度-1）
        int[] res = new int[s.length() - (maxLength - 1)];
        StringBuilder builder = new StringBuilder();
        // 从最后一个位置开始填充
        for (int i = res.length-1;i>=0;i--) {
            builder.append(s.charAt(i));
        }
        return builder.toString();

    }

    public char[] getDouble(String s) {
        if (s == null) {
            return null;
        }
        char[] newStr = new char[s.length() * 2 + 1];
        for (int i = 0; i < newStr.length; i++) {
            newStr[i] = (i & 1) == 0 ? '#' : s.charAt(i >> 1);
        }
        return newStr;
    }

    public static void main(String[] args) {
        Code02_AddShortestEnd code02_addShortestEnd = new Code02_AddShortestEnd();
        String str1 = "abcd123321";
        System.out.println(code02_addShortestEnd.addShortTestEnd(str1));
    }
}
