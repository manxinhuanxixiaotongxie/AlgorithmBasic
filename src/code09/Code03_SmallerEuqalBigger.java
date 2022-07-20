package code09;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-20 19:28
 */
/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * 1。把链表放入数组里，在数组上做partition 笔试用
 * 2。分成小、中、大三部分，再把各个部分之间串起来 面试用
 */
public class Code03_SmallerEuqalBigger {

    public static void userArray(int[] arr){
        int left = -1;
        int right = arr.length-1;
        for (int i=0;i<arr.length-1;i++) {
            if (arr[i] < arr[right])
        }
    }
}
