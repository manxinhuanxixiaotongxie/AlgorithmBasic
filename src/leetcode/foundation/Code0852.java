package leetcode.foundation;

public class Code0852 {

    public int peakIndexInMountainArray(int[] arr) {

        int l = 0;
        int r = arr.length - 1;
        int ans = 0;
        // 二分
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] > arr[mid + 1]) {
                r = mid - 1;
                ans = arr[mid] > arr[ans] ? mid : ans;
            } else {
                l = mid + 1;
                ans = arr[mid + 1] > arr[ans] ? mid + 1 : ans;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code0852 code0852 = new Code0852();
//        System.out.println(code0852.peakIndexInMountainArray(new int[]{24, 69, 100, 99, 79, 78, 67, 36, 26, 19}));
        System.out.println(code0852.peakIndexInMountainArray(new int[]{40, 48, 61, 75, 100, 99, 98, 39, 30, 10}));
    }
}
