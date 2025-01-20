package systemimprove.dp.fromrecursion;

public class Code01_SumUp {
    /**
     * 总结：
     *
     * 所有动态规划都是尝试来的
     *
     * 什么杨的暴力递归能够优化
     *
     * 有重复调用 同一个子问题的解 这种递归可以优化
     * 如果每个子问题都是不同的解 无法优化 也不能优化
     *
     *
     * 暴力递归：  想通的入参一定带来相同的返回结果
     * 暴力递归与动态规划的关系：
     *1.某一个暴力递归 有解的重复调用 就可以的把这个暴力递归优化成动态规划
     * 任何动态规划问题  都一定对应着某一个有重复过程的暴力递归
     *
     * 但是： 不是所有的暴力递归都有动态规划的优化
     *
     *
     * 解决一个问题，可能有很多尝试方法
     * 在很多尝试的方法中 又有若干个尝试方法有动态规划的方式
     * 一个问题 可能有若干动态规划的解法
     *
     * 如何找到某个问题的动态递归的方式
     * 1.设计暴力递归  重要原则+4中尝尽的尝试模型
     * 2.分析有没有重复解：套路解决
     * 3.用记忆化搜素   ->> 严格表结构实现动态规划：套路解决
     * 4.看能否继续优化：套路解决
     *
     *
     *
     * 针对第二点：
     * 有没有重复解：
     * 1.列出调用过程 可以只列出前几层
     * 2.有没有重复解 一看便知
     *
     *
     *
     * 设计暴力递归的原则：
     * 1.每个可变参数的类型 一定不要比Int更复杂
     * 2.原则1可以违反 让类型突破到一维线性结构 但是必须是单一的可变参数
     * 3.如果返现违反原则1 但是不违反原则2 只需要做到记忆化搜索即可
     * 4.可变参数  能少就少
     *
     * 清楚原则之后：
     * 1.一定要找到不违反原则情况下的暴力尝试
     * 2.找到暴力尝试 不符合原则额 马上舍弃 找新的
     * 3.如果某个题目突破了设计原则 已经很难 出现概率较低
     *
     *
     *
     * 常见的四种模型：
     * 1.从左往右的尝试模型
     * 2.范围上的尝试模型
     * 3.多样本地位置全对应的尝试模型
     * 4.寻找业务限制的的尝试模型
     *
     *
     * 想出来暴力递归之后，并且存在解的重复调用
     * 1.找到那些参数的变化会影响返回值 对每一个变化的值列出可变范围
     * 2.参数之间所有组合数量意味着大小
     * 3.记忆化搜索就是缓存的方式 非常容易得到
     * 4.规定好严格表的大小 分析位置依赖 然后从基础填写到最终解
     * 5。对于有枚举行行为的决策过程 进一步优化
     *
     * 优化方式：
     * 1。空间压缩
     * 2.状态从简
     * 4.四边形不等式
     * 5.其他优化技巧
     *
     *
     */
}
