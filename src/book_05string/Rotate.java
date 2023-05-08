package book_05string;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-05-04 15:22
 */
public class Rotate {

    /**
     * 两个字符串是否为旋转词
     *
     *
     * 要求：时间复杂度O（N）
     */

    public boolean isRotate(String s1, String s2) {

        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }

        // 1aab2 aab21 aab21

        String s21 = s2+s2;
//        return
        return true;
    }

}
