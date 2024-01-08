package systemimprove.code02;

public class Code01_FindRightOneNum {
    /**
     * 找到最右侧1
     */
    public int find(int num) {
        int rightOne = 0;
        while (num != 0) {
            rightOne = num & (~num + 1);
            num ^= rightOne;
        }
        return rightOne;
    }
}
