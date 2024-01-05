package systemlearning.code25;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-10-23 16:56
 */
public class Code01_MonotonicStack {

    /**
     * 没有重复值
     *
     * @param arr
     * @return
     */
    public int[][] getMonotonicStackNoRepeat(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] ans = new int[arr.length][2];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                Integer pop = stack.pop();
                ans[pop][0] = stack.isEmpty() ? -1 : stack.peek();
                ans[pop][1] = i;
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            Integer pop = stack.pop();
            ans[pop][0] = stack.isEmpty() ? -1 : stack.peek();
            ans[pop][1] = -1;
        }

        return ans;
    }

    /**
     * 有重复值
     *
     * @param arr
     * @return
     */
    public int[][] getMonotonicStackRepeat(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        int[][] ans = new int[arr.length][2];

        Stack<List<Integer>> stack = new Stack<>();
        stack.push(new ArrayList<>(arr[0]));

        for (int i = 1; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek().get(0)] > arr[i]) {
                List<Integer> pop = stack.pop();
                ans[pop.get(0)][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                ans[pop.get(0)][1] = i;
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                stack.push(list);
            }
        }

        while (!stack.isEmpty()) {
            List<Integer> pop = stack.pop();
            for (Integer index : pop) {
                ans[index][0] = stack.isEmpty() ? -1 : stack.peek().get(stack.peek().size() - 1);
                ans[index][1] = -1;
            }
        }

        return ans;

    }


}
