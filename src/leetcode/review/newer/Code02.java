package leetcode.review.newer;

public class Code02 {
    public double[] convertTemperature(double celsius) {
        // 给你摄氏度 转换成开氏度以及的华氏度
        return new double[]{celsius + 273.15, celsius * 1.80 + 32};
    }
}
