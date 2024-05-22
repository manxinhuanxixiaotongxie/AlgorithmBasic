package systemimprove.code23;

/**
 * 给定两个字符串str1和str2，判断两个字符串是否互为旋转词。
 * <p>
 * 旋转词定义：
 * 1. 字符串str1和str2长度一样
 * 2. str1的所有字符依次往后移动任意位置之后组成str2
 */
public class Code02_IsRotation {

    public boolean isRotation(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        // 将字符串加工成str1+str1的形式
        String str = str1 + str1;
        // 加工之后运用kmp进行判断
        int[] next = getNext(str);
        // 有了next数组之后 进行kmp
        // kmp的过程
        char[] chars = str.toCharArray();
        int x = 0;
        int y = 0;
        while (x < chars.length && y < str2.length()) {
            if (chars[x] == str2.charAt(y)) {
                x++;
                y++;
            } else if (next[y] == -1) {
                x++;
            } else {
                y = next[y];
            }
        }
        int ans = y == str2.length() ? x - y : -1;
        return ans != -1;
    }

    public int[] getNext(String string) {
        if (string.length() == 1) {
            return new int[]{-1};
        }
        char[] chars = string.toCharArray();
        int[] next = new int[chars.length];
        // 计算next数组
        next[0] = -1;
        next[1] = 0;
        for (int i = 2; i < chars.length; i++) {
            // 利用前面已经做过进行加速
            // 第一种情况 如果i-1位置字符next[i-1]位置的值相等的话 那么next[i] = next[i-1] + 1
            if (chars[i - 1] == next[i - 1]) {
                next[i] = next[i - 1] + 1;
            }
            // 第二种情况 如果不相等的话
            // 将y指向next[y]的位置 一直往前找 直到找到相等的位置
            else {
                int y = next[i - 1];
                while (y != -1) {
                    if (chars[i - 1] == chars[y]) {
                        next[i] = y + 1;
                        break;
                    }
                    y = next[y];
                }
            }
        }
        return next;
    }

    //for test
    public boolean right(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        String str = str1 + str1;
        return str.contains(str2);
    }

    public static void main(String[] args) {
        String str1 = "abcd";
        String str2 = "cdab";
        Code02_IsRotation isRotation = new Code02_IsRotation();
        System.out.println(isRotation.isRotation(str1, str2));
        System.out.println(isRotation.right(str1, str2));
    }
}
