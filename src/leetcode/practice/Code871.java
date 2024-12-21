package leetcode.practice;

import java.util.PriorityQueue;

/**
 * 测试通过
 */
public class Code871 {

    public static void main(String[] args) {
        Code871 solution = new Code871();
//        int[][] stations = {{10, 60}, {20, 30}, {30, 30}, {60, 40}};
//        int[][] stations = {{25,25},{50,25},{75,25}};
        int[][] stations = {{13, 21}, {26, 115}, {100, 47}, {225, 99}, {299, 141}, {444, 198}, {608, 190}, {636, 157}, {647, 255}, {841, 123}};
        System.out.println(solution.minRefuelStops(1000, 299, stations));

    }

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        if (target <= 0 || startFuel <= 0) {
            return -1;
        }
        if ((stations == null || stations.length == 0) && startFuel < target) {
            return -1;
        }
        if (startFuel >= target) {
            return 0;
        }
        if (stations[0][0] > startFuel) {
            return -1;
        }
        PriorityQueue<Info> queue = new PriorityQueue<>((o1, o2) -> o2.fuel - o1.fuel);
        int R = 0;
        int ans = 0;
        int pre = 0;
        while (R < stations.length) {

            while (startFuel - stations[R][0] + pre >= 0) {
                queue.add(new Info(R, stations[R][1]));
                R++;
                if (R == stations.length) {
                    break;
                }
            }
            if (R == stations.length) {
                break;
            }
            // 形成了一个区间
            // 油量最大的加油站进行加油
            if (startFuel < stations[R][0]) {
                if (queue.isEmpty()) {
                    return -1;
                }
                Info info = queue.poll();
                // 剩余的油量是当前加油站的油量加上之前剩余的油量（刚开始的油量减去已经走过路程）
                startFuel += info.fuel - (pre > stations[info.index][0] ? 0 : stations[info.index][0] - pre);
                // 剩余的路程为
                target -= (pre < stations[info.index][0]?stations[info.index][0] - pre:0);
                pre = Math.max(stations[info.index][0], pre);
                ans++;
            }
        }
        while (startFuel < target) {
            if (queue.isEmpty()) {
                return -1;
            } else {
                Info info = queue.poll();
                startFuel += info.fuel - (pre > stations[info.index][0] ? 0 : stations[info.index][0] - pre);
                target -= (pre < stations[info.index][0]?stations[info.index][0] - pre:0);
                pre = Math.max(stations[info.index][0], pre);
                ans++;

            }

        }
        return ans;

    }
}

class Info {
    int index;
    int fuel;

    Info(int index, int fuel) {
        this.index = index;
        this.fuel = fuel;
    }
}
