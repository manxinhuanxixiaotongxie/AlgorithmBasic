package systemimprove.code02;

public class Code02_FindNums {
    /**
     * 有两种数出现了奇数次
     * 其余出现了偶数次
     * 找到并打印这两个数
     */

    public void printNums(int[] nums) {
        int eor = 0;
        for (int num : nums) {
            eor ^= num;
        }
        // eor的结果就是要求的两个奇数的异或结果
        // 异或  --》 相同为0 不同为1
        // 假设异或的结果是010 那么最后一1 必然存在于其中一个奇数中
        // 通过这个1 将数组分为两部分
        int rightOne = eor & (~eor + 1);
        int eor2 = 0;
        for (int num : nums) {
            if ((num & rightOne) != 0) {
                eor2 ^= num;
            }
        }
        System.out.println(eor + " " + (eor ^ eor2));
    }
}
