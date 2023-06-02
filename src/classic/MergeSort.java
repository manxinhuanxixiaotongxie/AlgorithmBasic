package classic;

import book_02linkedlist.ListNode;

/**
 * @Description 经典排序算法归并排序
 * @Author Scurry
 * @Date 2023-05-09 15:53
 */
public class MergeSort {

    public static void mergeSort(int[] arr, int L, int R, int mid) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        // 要么p1越界，要么p2越界
        // 不可能出现：共同越界
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

    public static void merge1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void insertSort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }


        for (int i = 0; i < arr.length; i++) {
            int minNumIndex = i;
            while (minNumIndex >= 1 && arr[minNumIndex] < arr[minNumIndex - 1]) {
                swapNormal(arr, minNumIndex, --minNumIndex);
            }
        }

    }

    /**
     * 交换常规做法
     *
     * @param arr
     * @param i
     * @param j
     */
    public static void swapNormal(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    //    [-1,42,58,37]
    //    f(0,2) f(0,1) f(0,0) f(0,1) f(1,1) f(0,1) f(0,2) f(1,2) f(1,1) f(1,2) f(2,2) f(1,2) f(0,2)
    public static void process(int[] arr, int L, int R) {
        if (R == -1) {
            System.out.println("L==" + L + "R=" + R);
            printArray(arr);
            return;
        }
        /**
         * 存在空数组
         */
        if (L == R) {
            return;
        }
        int mid = L + ((R - L) >> 1);

        process(arr, L, mid);

        process(arr, mid + 1, R);

        mergeSort(arr, L, R, mid);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
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

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
//        int testTime = 500000;
//        int maxSize = 100;
//        int maxValue = 100;
//        System.out.println("测试开始");
//        for (int i = 0; i < testTime; i++) {
//            int[] arr1 = generateRandomArray(maxSize, maxValue);
//            int[] arr2 = copyArray(arr1);
//            merge1(arr1);
//            insertSort1(arr2);
//            if (!isEqual(arr1, arr2)) {
//                System.out.println("出错了！");
//                printArray(arr1);
//                printArray(arr2);
//                break;
//            }
//        }

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
//        n2.next = n3;
//        n3.next = n4;
//        n4.next = n5;

//        System.out.println("测试结束");
        removeNthFromEnd(n1, 2);

    }

    /**
     * https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/
     *
     * 删除链表的倒数第 N 个结点
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null || n <=0) {
            return head;
        }
        int length = 0;
        ListNode cur = head;
        while(cur != null) {
            length++;
            cur= cur.next;
        }
        if(n > length) {
            return head;
        }
        int index = length - n +1;
        length = 0;
        ListNode pre = null;
        cur = head;
        ListNode next;
        while(cur != null) {
            length++;
            next = cur.next;

            if(length == index) {
                if(pre == null) {
                    head = head.next;
                }else {
                    pre.next = next == null?null:next;
                    return head;

                }
                break;

            }
            pre = cur;
            cur = next;




        }

        return head;


    }
//    public static void main(String[] args) {
//        int[] arr = {5, 3, 12, 2, 1, 6, 5};
//        merge1(arr);
//        printArray(arr);
//
//    }

}
