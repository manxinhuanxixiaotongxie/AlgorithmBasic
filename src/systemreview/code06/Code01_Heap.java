package systemreview.code06;

/**
 * 堆 堆十分重要 在java中 实现堆的是优先级队列 默认是一个小根堆
 * 要求的堆中key可比较
 * <p>
 * 使用数组的实现
 * 注意：堆是一个被火车撞了都不能忘记的结构
 * 堆有很多衍生：
 * 1.加强堆
 * 在堆中维护一个索引的位置 快速找到某一个数 实现一个加强堆
 * <p>
 * <p>
 * 2.门槛堆 使用小根堆或者大跟堆维护一个门槛
 * 比如：小根堆维护一个门槛 假设维护堆的空间为3 如果新加入的数比他大的话 使用这个数替换掉堆顶的数
 * 如果一个数比他小不做处理 经过这个步骤之后 这个堆中存在数就最大的K个数
 * <p>
 * 大跟堆维护一个门槛 假设堆的空间为k 当新加入的数的小于堆顶的数的时候 将这个数加入到堆中
 * 比他大的时候不做处理 经过这个步骤之后 这个堆中存在的数就是最小的K个数
 */
public class Code01_Heap {
    // 使用数组实现一个堆
    int[] heap;
    int heapSize;
    int limit;

    Code01_Heap(int heapSize) {
        heap = new int[heapSize];
        this.heapSize = 0;
        this.limit = heapSize;
    }

    public void put(int value) {
        heap[heapSize++] = value;
        heapInsert(heapSize - 1);
    }

    public int pop() {
        int ans = heap[0];
        swap(0, --heapSize);
        heapify(0);
        return ans;
    }

    private void heapInsert(int index) {
        int fatherIndex = (index - 1) / 2;
        while (heap[fatherIndex] > heap[index]) {
            swap(fatherIndex, index);
            index = fatherIndex;
            fatherIndex = (index - 1) / 2;
        }
    }

    private void heapify(int index) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < heapSize) {
            int smallestIndex = leftIndex + 1 < heapSize && heap[leftIndex + 1] < heap[leftIndex] ? leftIndex + 1 : leftIndex;
            smallestIndex = heap[smallestIndex] < heap[index] ? smallestIndex : index;
            if (smallestIndex == index) {
                break;
            }
            swap(smallestIndex, index);
            index = smallestIndex;
            leftIndex = index * 2 + 1;
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
