package systemimprove.code06;

public class Code01_Heap {

    private int[] heap;
    private int limit;
    private int heapSize;

    public Code01_Heap(int limit) {
        heap = new int[limit];
        this.limit = limit;
        heapSize = 0;
    }

    public void heapInsert(int value) {
        if (heapSize == limit) {
            throw new RuntimeException("heap is full");
        }
        heap[heapSize] = value;
        heapSize++;
        int index = heapSize - 1;
        // 向上调整
        while (heap[index] > heap[(index - 1) / 2]) {
            swap(heap, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public int pop() {
        int ans = heap[0];
        swap(heap, 0, --heapSize);
        heapify(heap, 0, heapSize);
        return ans;
    }

    public void heapify(int[] arr, int index, int heapSize) {
        int leftIndex = index * 2 + 1;
        while (leftIndex < heapSize) {
            int largestIndex = leftIndex + 1 < heapSize && arr[leftIndex + 1] > arr[leftIndex] ? leftIndex + 1 : leftIndex;
            largestIndex = arr[largestIndex] > arr[index] ? largestIndex : index;
            if (largestIndex == index) {
                break;
            }
            swap(arr, largestIndex, index);
            index = largestIndex;
            leftIndex = index * 2 + 1;
        }
    }

    public void swap(int[] arr, int i, int j) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }
}
