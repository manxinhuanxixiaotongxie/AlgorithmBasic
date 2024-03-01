package systemimprove.code15;

/**
 * arr是货币数组，其中的值都是正数。再给定一个正数aim。
 * 每个值都认为是一张货币，
 * 返回组成aim的最少货币数
 * 注意：
 * 因为是求最少货币数，所以每一张货币认为是相同或者不同就不重要了
 *
 */
public class Code05_MinCoinsOnePaper {

    public int proces(int[] arr,int index,int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == arr.length) {
            return rest == 0 ? 0 : -1;
        }
        int p1 = proces(arr,index+1,rest);
        int p2 = proces(arr,index+1,rest-arr[index]);
        if (p2 != -1) {
            p2++;
        }
        return Math.min(p1,p2);

    }

}


