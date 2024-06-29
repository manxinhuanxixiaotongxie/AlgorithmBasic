package systemimprove.code01;

public class Code01_Dichotomy {

    /**
     * 在一个有序数组中查找某个数是否存在
     * @param arr
     * @param num
     * @return
     */
    public static boolean findIfExist(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        while (L < R) {
            int mid = L + ((R - L) >> 1);
            if (arr[mid] == num) {
                return true;
            } else if (arr[mid] > num) {
                // R不能从mid开始 如果L==R的话会死循环
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return false;
    }

    /**
     * 在有序数组中找>=某个数最左侧的位置
     *
     * @param nums
     * @param num
     * @return
     */
    public int findLeftestNum(int[] nums, int num) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int L = 0;
        int R = nums.length - 1;
        int leftest = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] >= num) {
                leftest = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return leftest;
    }

    /**
     * 在有序数组中找<=某个数最右侧的位置
     *
     * @param nums
     * @param num
     * @return
     */
    public int findRightestNum(int[] nums, int num) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int L = 0;
        int R = nums.length - 1;
        int rightest = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
            if (nums[mid] <= num) {
                rightest = mid;
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return rightest;
    }

    /**
     * 局部最小值问题
     * 在一个无序数组中找到一个局部最小值
     *
     *
     * @param nums
     * @return
     */
    public int findPartMin(int[] nums) {

        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        if (nums[0] < nums[1]) {
            return nums[0];
        }
        int N = nums.length - 1;
        if (nums[N - 1] > nums[N]) {
            return N;
        }
        int L = 1;
        int R = N - 1;
        int ans = -1;
        while (L <= R) {
            int mid = L + ((R - L) >> 1);
//            if (nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) {
//                ans = mid;
//            } else if (nums[mid] > nums[mid-1] && nums[mid] < nums[mid+1]) {
//                R = mid -1;
//            } else {
//                L = mid + 1;
//            }

            if (nums[mid] > nums[mid - 1]) {
                R = mid - 1;
            } else if (nums[mid] > nums[mid + 1]) {
                L = mid + 1;
            } else {
                ans = mid;
                break;
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(findIfExist(arr, 7));
    }

}
