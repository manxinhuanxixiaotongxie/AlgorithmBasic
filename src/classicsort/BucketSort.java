package classicsort;

/**
 * @Description 经典排序算法桶排序
 * @Author Scurry
 * @Date 2023-05-09 16:16
 */
public class BucketSort {
    /**
     * 对数据有很大的要求  1.正数  2.在一定范围内
     * 以人的年龄为例子
     * 人的年龄很难超过200
     * 准备一个长度为200的数组
     * 我们将不同的年龄放入不同的桶中
     * 自然完成了对用户年龄的排序
     */

    public void bucketSort(int[] arr) {

        int[] help = new int[200];
        for (int i = 0; i < arr.length; i++) {
            help[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i < help.length; i++) {
            int temp = help[i];
            while (temp-- > 0) {
                arr[index++] = i;
            }
        }
    }
}
