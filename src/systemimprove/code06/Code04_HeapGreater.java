package systemimprove.code06;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * 加强堆
 * <p>
 * 新增反向索引表
 *
 * @param <T>
 */
public class Code04_HeapGreater<T extends Comparator<T>> {
    private T[] heap;
    private int limit;
    private int heapSize;
    private Map<T, Integer> indexMap;
    private Map<Integer, T> reverseMap;

    public Code04_HeapGreater(int limit) {
        heap = (T[]) new Object[limit];
        this.limit = limit;
        heapSize = 0;
        indexMap = new HashMap<>();
        reverseMap = new HashMap<>();
    }

    public boolean isEmpty() {
        return heapSize == 0;
    }

    public void push(T t) {
        indexMap.put(t, heapSize);

        heapInsert(heapSize++);
    }

    public void heapInsert(int index) {
        while (heap[index].compare(heap[index], heap[(index - 1) / 2]) < 0) {
            swap(index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    /***
     * 这段代码只能新增元素，然后向上调整生成一个小根堆
     *
     * 但是在没有生成元素 只是元素的值发生了变化之后，这个方法就不适用了
     *
     * @param arr
     * @param index
     * @param heapSize
     */
//    public void heapInsert(T t) {
//
//        if (heapSize == limit) {
//            throw new RuntimeException("heap is full");
//        }
//        heap[heapSize] = t;
//        indexMap.put(t, heapSize);
//        reverseMap.put(heapSize,t);
//        int index = heapSize;
//        heapSize++;
//        // 向上调整
//        while (heap[index].compare(heap[index], heap[(index - 1) / 2]) > 0) {
//            swap( index, (index - 1) / 2);
//            index = (index - 1) / 2;
//        }
//
//    }
    public void heapify(T[] arr, int index, int heapSize) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < heapSize) {
            int largestIndex = leftIndex + 1 < heapSize && arr[leftIndex + 1].compare(arr[leftIndex + 1], arr[leftIndex]) > 0 ? leftIndex + 1 : leftIndex;
            largestIndex = arr[largestIndex].compare(arr[largestIndex], arr[index]) > 0 ? largestIndex : index;
            if (largestIndex == index) {
                break;
            }
            swap(largestIndex, index);
            index = largestIndex;
            leftIndex = index * 2 + 1;
        }
    }

    public void resign(T t) {
        int index = indexMap.get(t);
        heapify(heap, index, heapSize);
        heapInsert(index);
    }

    /**
     * @param t
     */
    public void remove(T t) {
        int index = indexMap.get(t);
        T replace = reverseMap.get(heapSize - 1);
        swap(index, --heapSize);
        indexMap.remove(t);
        indexMap.put(replace, index);
        resign(replace);
    }

    /**
     * swap除了要将堆中的元素交换，还要交换indexMap中的索引
     */
    public void swap(int l, int j) {
        T temp = heap[l];
        heap[l] = heap[j];
        heap[j] = temp;
        // 更新indexMap中的索引
        indexMap.put(heap[l], l);
        indexMap.put(heap[j], j);

        reverseMap.put(l, heap[l]);
        reverseMap.put(j, heap[j]);
    }


}
