package systemimprove.dp.division;

/**
 * 最优划分动态规划问题
 *
 * 计算最少（最多）可以划分多少段、最优划分得分等
 *
 * 一般定义f(i)表示长为i的前缀a[:i]再题目约束下，分割出的最少（最多）子数组个数（或者定义成分割方案数）
 * 枚举最后一个子数组的左端点L 从f[L]转移到f[i] 并考虑a[L:i]对最优解的影响
 *
 */
public class Template {
}
