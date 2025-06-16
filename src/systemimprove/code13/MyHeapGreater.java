package systemimprove.code13;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 加强堆
 * 使用泛型实现
 * 基于比较对象
 *
 * @param <T>
 */

public class MyHeapGreater<T extends Comparator<T>> {
    // 堆数据
    private T[] heap;
    // 堆大小
    private int heapSize;
    // 堆索引
    private Map<T, Integer> indexMap;
    // 堆大小的限制
    private final int limit;

    MyHeapGreater(int limit) {
        indexMap = new HashMap<>();
        this.limit = limit;
        heap = (T[]) new Object[limit];
        heapSize = 0;
    }

    /**
     * push element into heap
     *
     * @param t
     */
    public void push(T t) {
        if (heapSize == limit) {
            throw new RuntimeException("heap is full");
        }
        heap[heapSize] = t;
        indexMap.put(t, heapSize);
        // 调整堆 使得堆符合性质
        heapInsert(heapSize++);
    }

    /**
     * 先放入最后一个位置 然后从下到上进行调整
     *
     * @param index
     */
    public void heapInsert(int index) {

        while (heap[index].compare(heap[index], heap[(index - 1) / 2]) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }

    }

    public void resign(T t) {
        int index = indexMap.get(t);
        // 堆的调整过程 下面的两个方法只会进入一个分支

        // 向下
        heapify(heap, index, heapSize);
        // 向上
        heapInsert(index);
    }

    /**
     * 弹出堆内的某一个元素
     *
     * @param t
     */
    public void remove(T t) {
        int index = indexMap.get(t);
        // 将该位置与最后一个位置进行交换
        swap(index, --heapSize);
        // 重新调整堆 使得堆依然满足要求
        resign(t);
    }

    /**
     * 堆向下调整的过程
     *
     * @param heap
     * @param index
     * @param heapSize
     */
    public void heapify(T[] heap, int index, int heapSize) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < heapSize) {
            int largeIndex = leftIndex + 1 < heapSize && heap[leftIndex + 1].compare(heap[leftIndex + 1],
                    heap[leftIndex]) < 0 ? leftIndex + 1 : leftIndex;
            largeIndex = heap[largeIndex].compare(heap[largeIndex], heap[index]) < 0 ? largeIndex : index;
            if (largeIndex == index) {
                break;
            }
            swap(index, largeIndex);
            index = largeIndex;
            leftIndex = index * 2 + 1;
        }
    }

    /**
     * 交换堆中i位置与j位置的元素
     *
     * @param i
     * @param j
     */
    public void swap(int i, int j) {
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;

        indexMap.put(heap[i], i);
        indexMap.put(heap[j], j);
    }

}
