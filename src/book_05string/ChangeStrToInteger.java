package book_05string;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-05-04 17:11
 */
public class ChangeStrToInteger {

    public int changeStrToInteger(String str) {

        if (str == null || str.equals("")) {
            return 0;
        }
        char[] chars = str.toCharArray();

        if (!isValid(chars)) {
            return 0;
        }

        boolean posi = chars[0] == '-' ? false : true;

        int max = Integer.MIN_VALUE;
        int min = Integer.MIN_VALUE;
        int cur = 0;
        int res = 0;
        for (int i = posi ? 0 : 1; i < chars.length; i++) {
            cur = '0' - chars[i];
            if ((res < min) || (res == min && cur < min)) {
                return 0;
            }
            res = res * 10 + cur;
        }

        if (posi && res == min) {
            return 0;
        }
        return posi ? -res : res;
    }

    public boolean isValid(char[] chars) {
        if (chars[0] != '-' && (chars[0] < '0') || chars[0] > '9') {
            return false;
        }
        if (chars[0] == '-' && (chars.length == 1 || chars[1] == '0')) {
            return false;
        }
        if (chars[0] == '0' && chars.length > 1) {
            return false;
        }
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] < '0' || chars[i] > '9') {
                return false;
            }
        }
        return true;
    }
}
