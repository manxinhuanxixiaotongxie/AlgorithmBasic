package classic;

/**
 * @Description 堆的相关操作
 * @Author Scurry
 * @Date 2023-06-05 11:13
 */
public class MyHeap {

    int heapSize;
    int limit;
    int[] arr;

    MyHeap(int limit) {
        this.limit = limit;
        arr = new int[limit];
        heapSize = 0;
    }

    /**
     * 大根堆,建堆过程
     * <p>
     * 把数组想象成都完全二叉树
     * <p>
     * 左子节点：2i+1
     * 右子节点:2i+2
     * <p>
     * 当前节点父节点：（index-1）/2
     * <p>
     * 假设建造的堆是大跟堆
     * 1.将当前数加入数组
     * 2.当前节点比父节点大，进行交换
     * 3.当前节点变成父节点继续向前
     * 4.一直找到根节点为止
     *
     * @param val
     */
    public void push(int val) {
        if (heapSize == limit) {
            System.out.println("堆满了");
            return;
        }
        arr[heapSize] = val;
        heapInsert(arr, heapSize++);
    }

    private void heapInsert(int[] arr, int index) {
        // [index] [index-1]/2
        // index == 0
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /**
     * 大根堆,弹出过程，依然保持大跟堆
     * <p>
     * 大根堆弹出一个数，剩下的数依然要保持大根堆
     * 1.将数组的最后一个数与根节点进行交换
     * 2.堆长度减1
     * 3.找到当前节点的左右两个子节点 定义成left left+1
     * 4.比较左右两个节点，找到最大值
     * 5.最大值的数与当前数进行比较
     * 6.如果当前数比左右两个子节点都大的话，意味着当前数就是最大值 直接跳出循环
     * 7.当前数比左右两个子节点最大数要小，将当前数与最大值进行交换，当前数来到最大值位置
     * 8.此时，当前数的左右两个节点就是新的下标的左右两个节点
     * 9.跳出循环条件，左边已经没有节点了结束循环，注意完全二叉树的特性与满二叉树不同，完全二叉树可能有左节点没有右节点
     *
     * @param val
     */
    public int pop(int val) {

        int ans = arr[0];
        swap(arr, --heapSize, 0);
        heapify(arr, 0, heapSize);
        return ans;
    }

    private void heapify(int[] arr, int index, int heapSize) {

        int left = index * 2 + 1;

        while (left < heapSize) {

            int maxVlaueIndex = left + 1 < heapSize && arr[left] < arr[left + 1] ? left : left + 1;

            maxVlaueIndex = arr[index] < arr[maxVlaueIndex] ? maxVlaueIndex : index;

//            if (arr[index] < arr[maxVlaueIndex]) {
//                swap(arr, index, maxVlaueIndex);
//                index = maxVlaueIndex;
//            } else {
//                break;
//            }

            if (maxVlaueIndex == index) {
                break;
            }
            swap(arr, maxVlaueIndex, index);
            index = maxVlaueIndex;
            left = index * 2 + 1;
        }
    }

    private void swap(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

}
