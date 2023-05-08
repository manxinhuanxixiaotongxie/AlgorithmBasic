package book_05string;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-05-04 14:53
 */
public class Anagram {

    /**
     * 判断两个字符串是否为变形词
     */
    public boolean isAnagram(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }

        int[] map = new int[256];

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        for (int i = 0; i < chars1.length; i++) {
            map[chars1[i]]++;
        }

        for (int i = 0; i < chars2.length; i++) {
            if (map[chars2[i]]-- == 0) {
                return false;
            }
        }

        return true;
    }
}
