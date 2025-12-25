package leetcode.classic150;

import java.util.Arrays;
import java.util.TreeSet;

public class Code274 {
    /**
     * 引用次数
     *
     * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，
     * 一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，并且 至少 有 h 篇论文被引用次数大于等于 h 。如果 h 有多种可能的值，h 指数 是其中最大的那个。
     *
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        // 发表h篇论文 至少h篇引用超过了h次
        int n = citations.length;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = n - 1; i >= 0; i--) {
            set.add(citations[i]);
        }
        int ans = n;
        while (ans > 0) {
            // set.ceiling(ans)这个不是个数
            if (set.ceiling(ans) >= ans) {
                break;
            }
            ans --;

        }
        return ans;
    }

    /**
     * 排序 + 二分
     *
     * @param citations
     * @return
     */
    public int hIndex2(int[] citations) {
        Arrays.sort(citations);
        int ans = 0;
        int index = citations.length - 1;
        while (index >= 0 && ans <= citations.length - 1 && ans < citations[index]) {
            index--;
            ans++;
        }
        return ans;
    }

    static void main() {
        int[] citations = new int[] {3,0,6,1,5};
        int hIndex = new Code274().hIndex(citations);
        System.out.println(hIndex);
    }
}
