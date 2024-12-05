package leetcode.day;

public class Code3001 {
    public int minMovesToCaptureTheQueen(int a, int b, int c, int d, int e, int f) {
        if (insSame(a, b, c, d, e, f)) {
            return 1;
        }
        if (isSameLine(a, b, c, d, e, f)) {
            return 1;
        }
        return 2;
    }

    /**
     * 是否在同一直线上
     *
     * @return
     */
    private boolean isSameLine(int a, int b, int c, int d, int e, int f) {
        boolean ans = false;
        if (a == e) {
            ans = true;
            // 三者在同一行
            if (b < d && d < f) {
                while (b < f) {
                    if (b == d && c == e) {
                        return false;
                    }
                    b++;
                }
            }

            if (f < d && d < b) {
                while (f < b) {
                    if (f == d && c == e) {
                        return false;
                    }
                    f++;
                }
            }
        }

        // 三者在同一列
        if (b == f) {
            ans = true;
            if (a < c && c < e) {
                while (a < e) {
                    if (a == c && d == f) {
                        return false;
                    }
                    a++;
                }
            }
            if (e < c && c < a) {
                while (e < a) {
                    if (e == c && d == f) {
                        return false;
                    }
                    e++;
                }
            }
        }

        return ans;
    }

    private boolean insSame(int a, int b, int c, int d, int e, int f) {
        boolean ans1 = (Math.abs(c - e) == Math.abs(d - f));
        boolean ans = ((c == e && d == f) || ans1);
        if (ans1) {
            // c d e f在一个对角线上
            if (c < e) {
                // 从行数小的开始爬楼
                if (d < f) {
                    // 说明皇后在右下角
                    while (c < e) {
                        if (c == a && d == b) {
                            return false;
                        }
                        c++;
                        d++;
                    }
                } else {
                    // 说明皇后在左下角
                    while (c < e) {
                        if (c == a && d == b) {
                            return false;
                        }
                        c++;
                        d--;
                    }
                }
            } else {
                // 说明皇后在上半区
                if (d < f) {
                    // 皇后在右上角
                    while (d < f) {
                        if (c == a && d == b) {
                            return false;
                        }
                        c--;
                        d++;
                    }
                } else {
                    // 皇后在\左上角
                    while (d > f) {
                        if (c == a && d == b) {
                            return false;
                        }
                        d--;
                        c--;
                    }
                }
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Code3001 solution = new Code3001();
        System.out.println(solution.minMovesToCaptureTheQueen(4, 5, 7, 8, 2, 3));
//        System.out.println(solution.minMovesToCaptureTheQueen(1, 1, 4, 1, 8, 1));
//        System.out.println(solution.minMovesToCaptureTheQueen(1, 1, 8, 8, 2, 3));
//        System.out.println(solution.minMovesToCaptureTheQueen(5, 3, 3, 4, 5, 2));
    }


}
