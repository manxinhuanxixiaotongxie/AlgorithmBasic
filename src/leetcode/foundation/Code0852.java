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

    public int peakIndexInMountainArray2(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        int ans = l;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < arr[mid + 1]) {
                ans = mid + 1;
                l = mid + 1;
            } else {
                ans = mid;
                r = mid - 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code0852 code0852 = new Code0852();
        System.out.println(code0852.peakIndexInMountainArray(new int[]{40, 48, 61, 75, 100, 99, 98, 39, 30, 10}));
    }
}
