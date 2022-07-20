package code09;

/**
 * @Description
 * @Author Scurry
 * @Date 2022-07-20 19:28
 */
/**
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 * 1。把链表放入数组里，在数组上做partition 笔试用
 * 2。分成小、中、大三部分，再把各个部分之间串起来 面试用
 */
public class Code03_SmallerEuqalBigger {

    static class Node {
        private int value;
        private Node next;
        Node(int value) {
            this.value = value;
        }
    }

    public static void useArray(int[] arr){
        int left = -1;
        int right = arr.length-1;
        int R = arr.length-1;
        int i = 0;
        while (i<right) {
            if (arr[i] < arr[R]) {
                swap(arr,++left,i++);
            }
            if (arr[i] == arr[R]) {
                i++;
            }
            if (arr[i] > arr[R]) {
                swap(arr,i,--right);
            }
        }
        swap(arr,R,right);
    }

    public static Node useNoArray(Node head,int value) {

        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;

        Node next = null;
        while (next != null) {
            next = head.next;
            if (head.value < value) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            }

            if (head.value == value) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                }else {
                    eT.next = head;
                    eT = head;
                }
            }

            if (head.value > value) {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }

            head = next;
        }

        /**
         * 这样写有问题
         * 可能没有==区域
         */
//        if (sH != null) {
//            sT.next = eH;
//
//        }
//
//        if (eT != null) {
//            eT.next = bH;
//        }
//        return sH;

        if (sH != null) {
//            sT.next = eH == null?bH:eH;
            sT.next = eH;
            eT = eT==null?sT:eT;
        }

        if (eT != null) {
            eT.next = bH;

        }
        return sH != null ? sH : (eH != null ? eH : bH);


    }

    private static void swap(int[] arr,int a,int b) {

        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;

    }
}
