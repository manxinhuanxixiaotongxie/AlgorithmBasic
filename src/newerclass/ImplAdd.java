package newerclass;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-05-23 18:44
 */
public class ImplAdd {

    public static void main(String[] args) {
        System.out.println(add(1, 7));
    }

    /**
     * 不使用+号实现加法
     * 前置知识：
     * 两位数异或 就是无进位相加
     * 两位数进行与操作就是两个数相加的进位信息
     *
     * @param a
     * @param b
     * @return
     */
    public static int add(int a, int b) {

        int ans = a;

        while (b != 0) {
            // 无进位相加
            ans = a ^ b;
            // 进位信息
            b = (a & b) << 1;
            a = ans;
        }

        return ans;
    }
}
