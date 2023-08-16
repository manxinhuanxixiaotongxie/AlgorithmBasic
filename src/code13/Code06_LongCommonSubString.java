/**
 * @desc:
 * @author: Scurry
 * @date: 2023/8/15 23:37
 */

package code13;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * @desc:最长公共子序列测试
 * @author: Scurry
 * @date: 2023/8/15 23:37
 */
public class Code06_LongCommonSubString {
    private static int index = 0;

    public static void main(String[] args) {
        System.out.println(new Code06_LongCommonSubString().longestCommonSubsequence("ABC", "ACD"));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0) {
            return 0;
        }
        char[] chars1 = text1.toCharArray();
        char[] chars2 = text2.toCharArray();
        return process2(chars1, chars2, chars1.length - 1, chars2.length - 1);
    }

    //ABCD ACDF
    //  ABC ACDF
    //  AB  ACDF
    //    A   ACDF
    //    A   ACD
    //    A   AC
    //    A   A
    //
    public int process(char[] chars1, char[] chars2, int i, int j) {


        if (i == 0 && j == 0) {
            return chars1[i] == chars2[j] ? 1 : 0;
        } else if (i == 0) {
            if (chars1[i] == chars2[j]) {
                return 1;
            } else {
                return process(chars1, chars2, i, j - 1);
            }
        } else if (j == 0) {
            if (chars1[i] == chars2[j]) {
                return 1;
            } else {
                return process(chars1, chars2, i - 1, j);
            }
        } else {
            int p1 = process(chars1, chars2, i - 1, j);
            int p2 = process(chars1, chars2, i, j - 1);
            int p3 = chars1[i] == chars2[j] ? (1 + process(chars1, chars2, i - 1, j - 1)) : 0;
            return Math.max(p1, Math.max(p2, p3));
        }

    }

    public int process2(char[] chars1, char[] chars2, int i, int j) {

        if (i < 0 || j < 0) {
            return 0;
        }

        if (chars1[i] == chars2[j]) {
            return 1 + process2(chars1, chars2, i - 1, j - 1);
        } else {
            int p1 = process2(chars1, chars2, i - 1, j);
            int p2 = process2(chars1, chars2, i, j - 1);
            return Math.max(p1, p2);
        }

    }
}
