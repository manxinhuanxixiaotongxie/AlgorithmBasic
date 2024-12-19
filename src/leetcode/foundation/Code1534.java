package leetcode.foundation;

public class Code1534 {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int ans = 0;
        for (int i = 0; i < arr.length - 2; i++) {
            int j = i + 1;
            while (j < arr.length - 1) {
                if (Math.abs(arr[i] - arr[j]) <= a) {
                    int k = j + 1;
                    while (k < arr.length) {
                        if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                            ans++;
                        }
                        k++;
                    }
                }
                j++;
            }
        }

        return ans;
    }

}
