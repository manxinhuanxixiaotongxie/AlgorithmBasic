package leetcode.foundation;

public class Code1281 {

    public int subtractProductAndSum(int n) {
        int mul = 1;
        int sum = 0;
        int bit = getBit(n);
        while (n > 0) {
            mul *= (int) (n / Math.pow(10, bit - 1));
            sum += (int) (n / Math.pow(10, bit - 1));
            n %= Math.pow(10, bit - 1);
            bit--;
        }
        if (bit != 0) {
            return -sum;
        }
        return mul - sum;
    }

    private int getBit(int n) {
        int bit = 0;
        while (n > 0) {
            n /= 10;
            bit++;
        }
        return bit;
    }

    public static void main(String[] args) {
        Code1281 code1281 = new Code1281();
        System.out.println(code1281.subtractProductAndSum(690));
    }
}
