package leetcode.day;

import java.util.HashSet;
import java.util.Set;

public class Code2056 {
    public int countCombinations(String[] pieces, int[][] positions) {
        if (pieces == null || positions == null || pieces.length == 0 || pieces.length == 0 || positions[0].length == 0 || pieces.length
                != positions.length) {
            return 0;
        }
        // 最小有一个
        int ans = 1;
        // 如果来到一个位置 使用map来标记已经来到过的位置
        // 只能上下左右走
        Set<String> rookSet = new HashSet<>();
        Set<String> queenSet = new HashSet<>();
        Set<String> bishopSet = new HashSet<>();
        for (int i = 0; i < pieces.length; i++) {
            String piece = pieces[i];
            int[] position = positions[i];
            int x = position[0];
            int y = position[1];
            if ("rook".equals(piece)) {
                // 车只能上下左右进行走动
                int tempX = x - 1;
                while (tempX > 0) {
                    if (rookSet.contains(tempX + "_" + y)) {
                        break;
                    }
                    rookSet.add(tempX + "_" + y);
                    ans++;
                    tempX--;
                }
                tempX = x + 1;
                while (tempX <= 8) {
                    if (rookSet.contains(tempX + "_" + y)) {
                        break;
                    }
                    rookSet.add(tempX + "_" + y);
                    ans++;
                    tempX++;
                }

                int tempY = y - 1;
                while (tempY > 0) {
                    if (rookSet.contains(x + "_" + tempY)) {
                        break;
                    }
                    rookSet.add(x + "_" + tempY);
                    ans++;
                    tempY--;
                }
                tempY = y + 1;
                while (tempY < 8) {
                    if (rookSet.contains(x + "_" + tempY)) {
                        break;
                    }
                    rookSet.add(x + "_" + tempY);
                    ans++;
                    tempY++;
                }
            } else if ("queen".equals(piece)) {
                // 皇后可以走上下左右和斜线
                queenSet.add(position[0] + "_" + position[1]);
                int tempX = x - 1;
                int tempY = y - 1;
                while (tempX >= 0 && tempY >= 0) {
                    if (queenSet.contains(tempX + "_" + tempY)) {
                        break;
                    }
                    queenSet.add(tempX + "_" + tempY);
                    ans++;
                    tempX--;
                    tempY--;
                }
                tempX = x + 1;
                tempY = y + 1;
                while (tempX <= 8 && tempY <= 8) {
                    if (queenSet.contains(tempX + "_" + tempY)) {
                        break;
                    }
                    queenSet.add(tempX + "_" + tempY);
                    ans++;
                    tempX++;
                    tempY++;
                }

                tempX = x - 1;
                tempY = y + 1;
                while (tempX > 0 && tempY <= 8) {
                    if (queenSet.contains(tempX + "_" + tempY)) {
                        break;
                    }
                    queenSet.add(tempX + "_" + tempY);
                    ans++;
                    tempX--;
                    tempY++;
                }

                tempX = x + 1;
                tempY = y - 1;
                while (tempX <= 8 && tempY > 0) {
                    if (queenSet.contains(tempX + "_" + tempY)) {
                        break;
                    }
                    queenSet.add(tempX + "_" + tempY);
                    ans++;
                    tempX++;
                    tempY--;
                }

                tempX = x - 1;
                while (tempX > 0) {
                    if (queenSet.contains(tempX + "_" + y)) {
                        break;
                    }
                    queenSet.add(tempX + "_" + y);
                    ans++;
                    tempX--;
                }
                tempX = x + 1;
                while (tempX <= 8) {
                    if (queenSet.contains(tempX + "_" + y)) {
                        break;
                    }
                    queenSet.add(tempX + "_" + y);
                    ans++;
                    tempX++;
                }

                tempY = y - 1;
                while (tempY > 0) {
                    if (queenSet.contains(x + "_" + tempY)) {
                        break;
                    }
                    queenSet.add(x + "_" + tempY);
                    ans++;
                    tempY--;
                }
                tempY = y + 1;
                while (tempY <= 8) {
                    if (queenSet.contains(x + "_" + tempY)) {
                        break;
                    }
                    queenSet.add(x + "_" + tempY);
                    ans++;
                    tempY++;
                }


            } else {
                bishopSet.add(position[0] + "_" + position[1]);
                // 只能走斜线
                int tempX = x - 1;
                int tempY = y - 1;
                while (tempX > 0 && tempY > 0) {
                    if (bishopSet.contains(tempX + "_" + tempY)) {
                        break;
                    }
                    bishopSet.add(tempX + "_" + tempY);
                    ans++;
                    tempX--;
                    tempY--;
                }
                // 右下
                tempX = x + 1;
                tempY = y + 1;
                while (tempX <= 8 && tempY <= 8) {
                    if (bishopSet.contains(tempX + "_" + tempY)) {
                        break;
                    }
                    bishopSet.add(tempX + "_" + tempY);
                    ans++;
                    tempX++;
                    tempY++;
                }
                tempX = x - 1;
                tempY = y + 1;
                while (tempX > 0 && tempY <= 8) {
                    if (bishopSet.contains(tempX + "_" + tempY)) {
                        break;
                    }
                    bishopSet.add(tempX + "_" + tempY);
                    ans++;
                    tempX--;
                    tempY++;
                }

                tempX = x + 1;
                tempY = y - 1;
                while (tempX <= 8 && tempY > 0) {
                    if (bishopSet.contains(tempX + "_" + tempY)) {
                        break;
                    }
                    bishopSet.add(tempX + "_" + tempY);
                    ans++;
                    tempX++;
                    tempY--;
                }
            }
        }

        // 去重
        Set<String> unionSet = new HashSet<>();
        unionSet.addAll(rookSet);
        unionSet.addAll(queenSet);
        unionSet.addAll(bishopSet);
        return unionSet.size();
    }
}
