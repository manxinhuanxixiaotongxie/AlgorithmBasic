package systemimprove.code16;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Code01_MonStack {

    /***
     * 单调栈
     * 提供两种实现方式
     * @param arr
     * @return
     */
    public int[][] monStack(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] ans = new int[arr.length][2];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int popIndex = stack.pop();
                ans[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
                ans[popIndex][1] = i;
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int popIndex = stack.pop();
            ans[popIndex][0] = stack.isEmpty() ? -1 : stack.peek();
            ans[popIndex][1] = -1;
        }

        return ans;
    }

    public int[][] monStack2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] ans = new int[arr.length][2];
        // 使用数组模拟一个栈
        int[] stack = new int[arr.length];
        int stackSize = 0;
        for (int i = 0; i < arr.length; i++) {
            while (stackSize > 0 && arr[stack[stackSize - 1]] > arr[i]) {
                int popIndex = stack[--stackSize];
                ans[popIndex][0] = stackSize == 0 ? -1 : stack[stackSize - 1];
                ans[popIndex][1] = i;
            }
            stack[stackSize++] = i;
        }
        while (stackSize > 0) {
            int popIndex = stack[--stackSize];
            ans[popIndex][0] = stackSize == 0 ? -1 : stack[stackSize - 1];
            ans[popIndex][1] = -1;
        }
        return ans;
    }

    public int[] generateArr(int maxvalue,int maxLength) {
        int[] ans = new int[(int) ((maxLength+1)*Math.random())];
        Set<Integer> set = new HashSet<>();
        for (int i= 0;i < ans.length;i++) {
            int value = (int) ((maxvalue+1)*Math.random()) - (int) (maxvalue*Math.random());
            while (set.contains(value)) {
                value = (int) ((maxvalue+1)*Math.random()) - (int) (maxvalue*Math.random());
            }
            ans[i] = value;
        }
        return ans;
    }

    public boolean isEqual(int[][] a,int[][] b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i][0] != b[i][0] || a[i][1] != b[i][1]) {
                return false;
            }
        }
        return true;
    }

    public void printArr(int[][] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }

    public void printArr(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Code01_MonStack monStack = new Code01_MonStack();
        int maxvalue = 100;
        int maxLength = 20;
        int testTime = 100000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = monStack.generateArr(maxvalue,maxLength);
            int[][] ans1 = monStack.monStack(arr);
            int[][] ans2 = monStack.monStack2(arr);
            if (!monStack.isEqual(ans1,ans2)) {
                System.out.println("Oops!");
                monStack.printArr(arr);
                System.out.println("=========");
                monStack.printArr(ans1);
                System.out.println("=========");
                monStack.printArr(ans2);
                break;
            }
        }
        System.out.println("finish!");
    }

}
