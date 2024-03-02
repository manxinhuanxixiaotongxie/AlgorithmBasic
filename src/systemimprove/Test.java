package systemimprove;

import java.util.Arrays;
import java.util.Stack;

public class Test {

    public void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public void process(int[] arr, int L, int R) {
        if (L == R) {
            return;
        }
        int mid = (L + R) >> 1;
        process(arr, L, mid);
        process(arr, mid + 1, R);
        merge(arr, L, mid, R);
    }

    /**
     * 小和问题  在归并排序的过程中，将左边比他小的数转化成右边比他大的数
     * 逆序对问题
     *
     * @param arr
     * @param L
     * @param mid
     * @param R
     */
    public void merge(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
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
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
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
//        Test test = new Test();
//        int testTime = 500000;
//        int maxSize = 100;
//        int maxValue = 100;
//        boolean succeed = true;
//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = test.generateRandomArray(maxSize, maxValue);
//            int[] arr2 = test.copyArray(arr1);
//            Arrays.sort(arr1);
//            test.mergeSort(arr2);
//            if (!test.isEqual(arr1, arr2)) {
//                System.out.println("Oops!");
//                test.printArray(arr1);
//                test.printArray(arr2);
//                break;
//            }
//        }
//        System.out.println(succeed ? "Nice!" : "Oops!");

        // 用3 5这个链表测试reverseBetween方法
        Test test = new Test();
        ListNode head = test.new ListNode(3);
        ListNode node1 = test.new ListNode(5);
        head.next = node1;
        test.reverseBetween(head, 1, 2);
    }

    public int countOfRange(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process2(arr, 0, arr.length - 1);
    }

    public int process2(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        int mid = (L + R) >> 1;
        return process2(arr, L, mid) + process2(arr, mid + 1, R) + merge2(arr, L, mid, R);
    }

    public int merge2(int[] arr, int L, int mid, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        int ans = 0;
        while (p1 <= mid && p2 <= R) {
            ans += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= mid) {
            help[i++] = arr[p1++];
        }
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return ans;
    }

    public int largestRectangleArea2(int[] height) {
        int[] stack = new int[height.length];
        int stackSize = 0;
        int ans = 0;
        for (int i = 0; i < height.length; i++) {
            // 等于要不要算
            while (stackSize > 0 && height[stack[stackSize - 1]] >= height[i]) {
                int pop = stack[--stackSize];
                int left = stackSize == 0 ? -1 : stack[stackSize - 1];
                ans = Math.max(ans, height[pop] * (i - left - 1));
            }
            stack[++stackSize] = i;
        }
        while (stackSize > 0) {
            int pop = stack[--stackSize];
            int left = stackSize == 0 ? -1 : stack[stackSize - 1];
            ans = Math.max(ans, height[pop] * (pop - left - 1));
        }
        return ans;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left < 1 || right < 1 || left >= right) {
            return head;
        }
        // 链表的的位置是从1开始的
        int index = 0;
        ListNode cur = head;
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode leftBefore = null;
        ListNode rightAfter = null;
        while (cur != null) {

            if (left != 1 && index == left - 2) {
                leftBefore = cur;
            }

            index++;
            if (index >= left && index <= right) {
                stack.push(cur);
            }
            if (index == right + 1) {
                rightAfter = cur;
            }
            cur = cur.next;

        }
        if (!stack.isEmpty() && leftBefore != null) {
            leftBefore.next = stack.peek();

        }
        while (!stack.isEmpty()) {
            cur = stack.pop();
            if (leftBefore == null) {
                head = cur;
                leftBefore = cur;
            }
            cur.next = stack.isEmpty() ? rightAfter : stack.peek();
        }


        return head;

    }


}
