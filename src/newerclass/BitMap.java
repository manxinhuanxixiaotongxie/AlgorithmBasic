package newerclass;

/**
 * @Description手动实现一个位图
 * @Author Scurry
 * @Date 2023-05-23 20:16
 */
public class BitMap {

    private long[] bits;

    public BitMap(int max) {
        bits = new long[(max + 64) >> 6];
    }

    /**
     * 加入
     * 取整：找到位于数组的index是多少
     * 取余：找到对应的index之后，剩下的数放进index的位置的数值 对应的数字置为1
     *
     * @param num
     */
    public void add(int num) {
        bits[num >> 6] |= 1 << (num & 63);
    }

    /**
     * 删除
     * 就是将对应的index的数值位数置为0
     * 找到对应的数值之后将对应的位数取反
     * 与进去原有的数组的值就可以置为0
     *
     * @param num
     */
    public void delete(int num) {
        bits[num >> 6] &= ~(1L << (num & 63));
    }

    /**
     * 包含某个数其实就是那个数位数是不是1
     * 与上index的下标，是1就意味着数存在否则就不存在
     *
     * @param num
     * @return
     */
    public boolean contains(int num) {
        return (bits[num >> 6] & (1L << (num & 63))) != 0;
    }


}
