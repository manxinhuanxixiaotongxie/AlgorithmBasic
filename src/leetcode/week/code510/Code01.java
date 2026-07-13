package leetcode.week.code510;

/**
 * 给你两个有效时间 startTime 和 endTime，它们均以字符串形式表示，格式为 "HH:MM:SS"。
 * <p>
 * 返回从 startTime 到 endTime 经过的秒数（包含两个端点）。
 *
 */
public class Code01 {
    public int secondsBetweenTimes(String startTime, String endTime) {
        // 开始计算
        int ans = 0;
        int size = 2;
        String[] startSplit = startTime.split(":");
        String[] endSplit = endTime.split(":");
        // 进位信息
        int minus = 0;
        int base = 1;
        for (int i = size; i >= 0; i--) {
            String curEnd = endSplit[i];
            int curEndInt = Integer.parseInt(curEnd);
            String curStart = startSplit[i];
            int curStartInt = Integer.parseInt(curStart);
            if ((curEndInt + minus) >= curStartInt) {
                ans += (curEndInt - curStartInt + minus) * base;
                minus = 0;
            } else {
                ans += (60 + curEndInt - curStartInt + minus) * base;
                minus = -1;
            }
            base *= 60;
        }
        return ans;
    }

    static void main() {
        Code01 c = new Code01();
        String startTime = "12:34:56";
        String endTime = "13:00:00";
        System.out.println(c.secondsBetweenTimes(startTime, endTime));
    }
}
