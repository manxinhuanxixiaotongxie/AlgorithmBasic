package leetcode.classic150;

public class Code067 {

    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        if ("0".equals(a)) {
            return b;
        }
        if ("0".equals(b)) {
            return a;
        }
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();

        int a1Index = a1.length - 1;
        int b1Index = b1.length - 1;
        boolean add = false;
        while (a1Index >= 0 && b1Index >= 0) {
            // 两个都是1
            if (a1[a1Index] == '1' && b1[b1Index] == '1') {
                if (add) {
                    sb.append("1");
                } else {
                    sb.append("0");
                    add = true;
                }
            } else if (a1[a1Index] == '1' || b1[b1Index] == '1') {
                if (add) {
                    sb.append("0");
                } else {
                    sb.append("1");
                    add = false;
                }
            }else {
                if (add) {
                    sb.append("1");

                }else  {
                    sb.append("0");
                }
                add = false;
            }
            a1Index--;
            b1Index--;
        }
        while (a1Index >= 0) {
            if (a1[a1Index] == '1') {
                if (add) {
                    sb.append("0");
                } else {
                    sb.append("1");
                    add = false;
                }
            } else {
                if (add) {
                    sb.append("1");
                    add = false;
                } else {
                    sb.append("0");
                }
            }
            a1Index--;
        }
        while (b1Index >= 0) {
            if (b1[b1Index] == '1') {
                if (add) {
                    sb.append("0");
                } else {
                    sb.append("1");
                    add = false;
                }
            } else {
                if (add) {
                    sb.append("1");
                    add = false;
                } else {
                    sb.append("0");
                }
            }
            b1Index--;
        }
        if (add) {
            sb.append("1");
        }
        sb.reverse();

        return sb.toString();
    }
}
