package leetcode.foundation;

public class Code0258 {

    public int addDigits(int num) {
        String str = String.valueOf(num);
        while (Integer.valueOf(str) >= 10) {
            Integer temp = 0;
            for (int i = 0; i < str.length(); i++) {
                temp += Integer.valueOf(str.charAt(i) - '0');
            }
            str = String.valueOf(temp);
        }
        return Integer.valueOf(str);
    }

    public static void main(String[] args) {
        Code0258 code0258 = new Code0258();
        System.out.println(code0258.addDigits(38));
    }

}
