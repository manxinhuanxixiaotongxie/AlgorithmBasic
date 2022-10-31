package book_01stackandlist;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-10-25 20:15
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 长度为N的数组   长度为W的窗口
 * 输出一个新的数组 包含最大值
 * <p>
 * 要求时间复杂度O(N)
 */
public class GenerateMaxWindow {

    public int[] getMaxWindow(int[] arr, int w) {

        /**
         * 。。。。。。。。。。。。。。
         * 使用双循环，在内循环找每个内循环的最大值放进去返回数组里
         *
         */
//    for (int i = 0; i < arr.length; i++) {
//        int max ;
//        for (int j = i; j < i + w; j++) {
//
//        }
//
//    }

        /**
         * 。。。。。。。。。。。。。。
         * 使用双端队列
         * 时间复杂度O（N）
         *
         * 思路:
         * 第一个数放进去队列里
         * 第二个数和第一个数比较，如果比第一个数大，就把第一个数弹出，把第二个数放进去
         * 第w个数和第w-1个数比较，如果比第w-1个数大，就把第w-1个数弹出，把第w个数放进去
         *如果第w个数比w-1小的话加到队列的尾巴
         *
         */
        LinkedList<Integer> qmax = new LinkedList<>();
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        for (int i = 0; i < arr.length; i++) {
            // 队列的最后一位数比当前数小 从尾部弹出队列
            // 经过这步之后，队列一定是单调的
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }
            // 加入当前值
            qmax.addLast(i);
            // 什么时候从头弹出？相等说明队头的下标已过期
            // 如果被移出窗口的元素是当前窗口最大元素，则移出队头元素
            // 先前放入的数如果还存在于结构中，那么该数一定比后放入的数都大
            // 最大数的下标与窗口的下一个数相等，意味着最大数已经不在窗口中了
            // 最大值是这样的，如果一个窗口的最大值被确定 意味着这个最大值只能作为这个窗口的最大值   当最大值的下标加上一个窗口大小等于当前的下标时，说明这个最大值已经不在这个窗口里了
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }
            // 5 4 3 2 1
            // 4 5 3 2 1
            // 0 1 2 3 4
            // w 3

            // 4 3 5 4 3 3 6 7
            // 数组的长度一定是i-w+1
            if (i >= w - 1) {
                res[index++] = arr[qmax.peekFirst()];
            }
        }

        return res;
    }

    public void run(int[] values, int windowCount) {
        int counter = 0;
        LinkedList<Integer> lst = new LinkedList<Integer>();
        List<Integer> maxValues = new ArrayList<Integer>();
        for (int value : values) {
            // 队列中没有元素直接放入list中
            if (lst.size() == 0) {
                lst.add(value);
            } else {
                while (lst.size() > 0) {
                    int tail = lst.peekLast();
                    if (tail <= value) {
                        lst.removeLast();
                    } else {
                        break;
                    }
                }
                // 满足上述条件之后假如的队列一定是单调递减的

                lst.add(value);

                String elements = "";
                for (int i = 0; i < lst.size(); i++) {
                    elements = elements + lst.get(i) + "; ";
                }

            }

            counter++;
            // 只要索引大于了窗口，每个加入的元素都会产生一个窗口最大值
            if (counter >= windowCount) {
                int head = lst.get(0);
                maxValues.add(head);
            }
            // 如果队列个数满足了三个，头元素删除
            if (lst.size() == 3) {
                lst.remove(0);
            }
        }

        int index = 1;
        for (int maxValue : maxValues) {
            System.out.println(index++ + ". value: " + maxValue);
        }
    }


}
