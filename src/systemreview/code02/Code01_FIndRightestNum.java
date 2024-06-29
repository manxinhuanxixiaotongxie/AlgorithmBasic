package systemreview.code02;

public class Code01_FIndRightestNum {

    /**
     * 异或运算   无进位相加
     *
     * @param num
     * @return
     */
    public int findRightestNum(int num) {
        return num & (~num + 1);
    }
}
