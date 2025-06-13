package systemimprove.code25;

/**
 * 给定一个无序数组arr中，长度为N，给定一个正数k，返回top k个最大的数
 *
 * 不同时间复杂度三个方法：
 *
 * 1）O(N*logN)
 * 对整个数组进行排序
 *
 * 2）O(N + K*logN)
 *
 * 采用由底向上的建堆方式 参考heapSort的建堆方式
 *
 * 3）O(n + k*logk)
 *
 * 先求第nums.length - k + 1小的数
 * 再遍历一次数组 只有比这个数大的数才放入到一个小根堆中
 * 再针对这个K个数进行排序
 *
 *
 *
 */
public class Code03_FindMostKNum {
    /**
     * 未经过严格验证
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] findMostKNum(int[] arr, int k) {
        if (arr == null || arr.length == 0 || k <= 0 || k > arr.length) {
            return new int[0];
        }
        // 找到第nums.length - k + 1小的数
        int target = bfprt(arr, 0, arr.length - 1, arr.length - k);
        // 再遍历一次数组 只有比这个数大的数才放入到一个小根堆中
        MinHeap minHeap = new MinHeap(k);
        for (int num : arr) {
            if (num > target) {
                minHeap.add(num);
            }
        }
        // 再针对这个K个数进行排序
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = minHeap.poll();
        }
        return res;
    }

    private int bfprt(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        int pivot = partition(nums, left, right);
        if (k == pivot) {
            return nums[k];
        } else if (k < pivot) {
            return bfprt(nums, left, pivot - 1, k);
        } else {
            return bfprt(nums, pivot + 1, right, k);
        }
    }

    private int partition(int[] nums, int left, int right) {
        int pivot = nums[right];
        int i = left - 1;
        for (int j = left; j < right; j++) {
            if (nums[j] <= pivot) {
                i++;
                swap(nums, i, j);
            }
        }
        swap(nums, i + 1, right);
        return i + 1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private class MinHeap {
        private int[] heap;
        private int size;
        private int capacity;

        public MinHeap(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.heap = new int[capacity];
        }

        public void add(int num) {
            if (size < capacity) {
                heap[size++] = num;
                heapifyUp(size - 1);
            } else if (num > heap[0]) {
                heap[0] = num;
                heapifyDown(0);
            }
        }

        public int poll() {
            if (size == 0) {
                throw new IllegalStateException("Heap is empty");
            }
            int min = heap[0];
            heap[0] = heap[--size];
            heapifyDown(0);
            return min;
        }

        private void heapifyUp(int index) {
            while (index > 0) {
                int parentIndex = (index - 1) / 2;
                if (heap[index] >= heap[parentIndex]) {
                    break;
                }
                swap(heap, index, parentIndex);
                index = parentIndex;
            }
        }

        private void heapifyDown(int index) {
            while (true) {
                int leftChild = 2 * index + 1;
                int rightChild = 2 * index + 2;
                int smallest = index;

                if (leftChild < size && heap[leftChild] < heap[smallest]) {
                    smallest = leftChild;
                }
                if (rightChild < size && heap[rightChild] < heap[smallest]) {
                    smallest = rightChild;
                }
                if (smallest == index) {
                    break;
                }
                swap(heap, index, smallest);
                index = smallest;
            }
        }
    }

}
