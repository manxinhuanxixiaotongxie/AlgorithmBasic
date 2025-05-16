package leetcode.practice;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 给你一个二维整数数组 tiles ，其中 tiles[i] = [li, ri] ，表示所有在 li <= j <= ri 之间的每个瓷砖位置 j 都被涂成了白色。
 * <p>
 * 同时给你一个整数 carpetLen ，表示可以放在 任何位置 的一块毯子的长度。
 * <p>
 * 请你返回使用这块毯子，最多 可以盖住多少块瓷砖。
 */
public class Code2271 {
    /**
     * 这个方法会少算一些
     * 具体原因还要排查
     *
     * @param tiles
     * @param carpetLen
     * @return
     */
    public int maximumWhiteTiles(int[][] tiles, int carpetLen) {

        Arrays.sort(tiles, Comparator.comparingInt(o -> o[0]));
        int ans = 0;
        // 滑动窗口
        int row = tiles.length;
        int r = 0;
        while (r < row) {
            int tempR = r + 1;
            while (tempR < row && tiles[tempR][0] - tiles[r][0] < carpetLen) {
                tempR++;
            }
            // 计算从r行道tempR行的瓷砖数量
            int tempAns = 0;
            int tempL = r;
            while (r < tempR) {
                tempAns += tiles[r][1] - tiles[r][0] + 1;
                r++;
            }
            if (r < row && tiles[r - 1][1] - tiles[tempL][0] + 1 >= carpetLen) {
                tempAns -= (tiles[r - 1][1] - tiles[tempL][0] + 1 - carpetLen);
            }
            ans = Math.max(ans, tempAns);
        }
        return ans;
    }

    public int maximumWhiteTilesRight(int[][] tiles, int carpetLen) {
        Arrays.sort(tiles, Comparator.comparingInt(a -> a[0]));
        int ans = 0;
        int cover = 0;
        int left = 0;
        for (int[] tile : tiles) {
            int tl = tile[0];
            int tr = tile[1];
            cover += tr - tl + 1;
            while (tiles[left][1] + carpetLen - 1 < tr) {
                cover -= tiles[left][1] - tiles[left][0] + 1;
                left++;
            }
            int uncover = Math.max(tr - carpetLen + 1 - tiles[left][0], 0);
            ans = Math.max(ans, cover - uncover);
        }
        return ans;
    }

    public static void main(String[] args) {
        // tiles =
        //[[8051,8057],[8074,8089],[7994,7995],[7969,7987],[8013,8020],[8123,8139],[7930,7950],[8096,8104],[7917,7925],[8027,8035],[8003,8011]]
        Code2271 code2271 = new Code2271();
        int[][] tiles = new int[][]{{3745, 3757}, {3663, 3681}, {3593, 3605}, {3890, 3903}, {3529, 3539}, {3684, 3686}, {3023, 3026}, {2551, 2569}, {3776, 3789}, {3243, 3256}, {3477, 3497}, {2650, 2654}, {2264, 2266}, {2582, 2599}, {2846, 2863}, {2346, 2364}, {3839, 3842}, {3926, 3935}, {2995, 3012}, {3152, 3167}, {4133, 4134}, {4048, 4058}, {3719, 3730}, {2498, 2510}, {2277, 2295}, {4117, 4128}, {3043, 3054}, {3394, 3402}, {3921, 3924}, {3500, 3514}, {2789, 2808}, {3291, 3294}, {2873, 2881}, {2760, 2760}, {3349, 3362}, {2888, 2899}, {3802, 3822}, {3540, 3542}, {3128, 3142}, {2617, 2632}, {3979, 3994}, {2780, 2781}, {3213, 3233}, {3099, 3113}, {3646, 3651}, {3956, 3963}, {2674, 2691}, {3860, 3873}, {3363, 3370}, {2727, 2737}, {2453, 2471}, {4011, 4031}, {3566, 3577}, {2705, 2707}, {3560, 3565}, {3454, 3456}, {3655, 3660}, {4100, 4103}, {2382, 2382}, {4032, 4033}, {2518, 2531}, {2739
                , 2749}, {3067, 3079}, {4068, 4074}, {2297, 2312}, {2489, 2490}, {2954, 2974}, {2400, 2418}, {3271, 3272}, {3628, 3632}, {3372, 3377}, {2920, 2940}, {3315, 3330}, {3417, 3435}, {4146, 4156}, {2324, 2340}, {2426, 2435}, {2373, 2376}, {3621, 3626}, {2826, 2832}, {3937, 3949}, {3178, 3195}, {4081, 4082}, {4092, 4098}, {3688, 3698}};
//        // 1638
        int carpetLen = 1638;
        System.out.println(code2271.maximumWhiteTiles(tiles, carpetLen));
    }
}
