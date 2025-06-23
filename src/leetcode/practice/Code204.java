package leetcode.practice;

import java.util.List;

public class Code204 {
    /**
     * 欧拉筛：能够在O（N）的时间复杂度下找到从2到N的所有质数。
     * <p>
     * 素数：除了1和它本身之外，没有其他的因数。
     * 合数：除了1和它本身之外，还有其他的因数。
     * <p>
     * 对于每个素数P 我们标记P的倍数为合数
     * 关键在于对于每个合数只标记一次
     *
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        List<Integer> primes = new java.util.ArrayList<>();
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]) {
                ans++;
                primes.add(i);
            }
            for (Integer p : primes) {
                if (i * p > n) break;
                isPrime[i * p] = true;
                if (i % p == 0) break;
            }
        }
        return ans;
    }

    public int countPrimes2(int n) {
        boolean[] isPrime = new boolean[n + 1];
        int ans = 0;
        for (int i = 2; i < n; i++) {
            if (!isPrime[i]) {
                ans++;
                if ((long) i * i < n) {
                    for (int j = i * i; j <= n; j += i) {
                        isPrime[j] = true;
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code204 code204 = new Code204();
        int n = 499979;
        System.out.println("Count of primes less than " + n + ": " + code204.countPrimes(n));
        System.out.println("Count of primes less than " + n + " using countPrimes2: " + code204.countPrimes2(n));
    }
}
