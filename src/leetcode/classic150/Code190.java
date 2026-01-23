package leetcode.classic150;

public class Code190 {
    public int reverseBits(int n) {
        String binaryString = Integer.toBinaryString(n);
        char[] charArray = binaryString.toCharArray();
        char[] help = new char[32];
        for (int i = 31; i >= 0; i--) {
            int idx = charArray.length - 32 + i;
            help[31 - i] = idx >= 0 ? charArray[idx] : '0';
        }
        System.out.println(new String(help));
        return Integer.parseInt(new String(help), 2);
    }

    static void main() {
        Code190 code190 = new Code190();
        int i = code190.reverseBits(43261596);
        System.out.println(i);
    }
}
