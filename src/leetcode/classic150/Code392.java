package leetcode.classic150;

public class Code392 {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (s.length() > t.length()) {
            return false;
        }

        char[] sStr = s.toCharArray();
        char[] tStr = t.toCharArray();
        int l1 = 0;
        int l2 = 0;
        int n1 = sStr.length;
        int n2 = tStr.length;

        while (l1 < n1 && l2 < n2) {
            while (l2 < n2 && sStr[l1] != tStr[l2]) {
                l2++;
            }
            if (l2 == n2) {
                return false;
            } else {
                l1++;
                l2++;
            }
        }
        return l1 == n1 ;
    }

    static void main() {
        Code392 code392 = new Code392();
        boolean res = code392.isSubsequence("b", "abc");
        System.out.println(res);
    }

}
