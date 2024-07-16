package systemreview.code06;

import java.util.Arrays;

public class Code02_HeapSort {

    public void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        Heap heap = new Heap(arr.length);
//        for (int i = 0; i < arr.length; i++) {
//            heap.put(arr[i]);
//        }
        for (int i = arr.length - 1; i >= 0; i--) {
            heap.heapify(arr, i);
        }

        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = heap.pop();
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ans[i];
        }
    }

    public int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    public int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] ans = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code02_HeapSort test = new Code02_HeapSort();
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = test.generateRandomArray(maxSize, maxValue);
            int[] arr2 = test.copyArray(arr1);
            test.heapSort(arr1);
            test.comparator(arr2);
            if (!test.isEqual(arr1, arr2)) {
                succeed = false;
                test.printArray(arr1);
                test.printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "finish!" : "Oops..");
    }

    private void comparator(int[] arr2) {
        Arrays.sort(arr2);
    }
}


class Heap {
    int[] heap;
    int heapSize;

    Heap(int heapSize) {
        heap = new int[heapSize];
        this.heapSize = 0;
    }

    public int pop() {
        int ans = heap[0];
        swap(0, --heapSize);
        heapify(0);
        return ans;
    }

    public void heapify(int[] arr, int index) {
        heap[index] = arr[index];
        this.heapSize = arr.length;
        int leftIndex = index * 2 + 1;
        int heapSize = arr.length;
        while (leftIndex < heapSize) {
            int smallIndex = leftIndex + 1 < heapSize && heap[leftIndex + 1] < heap[leftIndex] ? leftIndex + 1 : leftIndex;
            smallIndex = heap[smallIndex] < heap[index] ? smallIndex : index;
            if (smallIndex == index) {
                break;
            }
            swap(smallIndex, index);
            index = smallIndex;
            leftIndex = index * 2 + 1;
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

    public void put(int value) {
        heap[heapSize++] = value;
        heapInsert(heapSize - 1);
    }

    private void heapInsert(int index) {
        int fatherIndex = (index - 1) / 2;
        while (heap[fatherIndex] > heap[index]) {
            swap(fatherIndex, index);
            index = fatherIndex;
            fatherIndex = (index - 1) / 2;
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
