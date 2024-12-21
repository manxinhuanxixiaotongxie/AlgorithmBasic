package leetcode.practice;

public class Code0171 {

    public int titleToNumber(String columnTitle) {
        char[][] chars = new char[][]{
                {'A', 0},
                {'B', 1},
                {'C', 2},
                {'D', 3},
                {'E', 4},
                {'F', 5},
                {'G', 6},
                {'H', 7},
                {'I', 8},
                {'J', 9},
                {'K', 10},
                {'L', 11},
                {'M', 12},
                {'N', 13},
                {'O', 14},
                {'P', 15},
                {'Q', 16},
                {'R', 17},
                {'S', 18},
                {'T', 19},
                {'U', 20},
                {'V', 21},
                {'W', 22},
                {'X', 23},
                {'Y', 24},
                {'Z', 25}
        };
        int times = 0;
        char[] charArray = columnTitle.toCharArray();
        for (char c : charArray) {
            for (int i = 0; i < chars.length; i++) {
                if (c == chars[i][0]) {
                    times = times * 26 + chars[i][1] + 1;
                    break;
                }
            }
        }
        return times;
    }

}
