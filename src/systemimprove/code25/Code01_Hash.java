package systemimprove.code25;

/**
 * 经典的hash结构的实现：
 * 前置：哈希函数的特性
 * 1.相同的输入一定会产生相同的输出
 * 2.不同的输入，可能会产生相同的输出 hash冲突
 *
 * 1.离散性 hash的离散性体现在什么地方呢？
 * 1.1 输入的值可能具有很强的规律性，但是输出的值一定要具有很强的离散性
 * 2.均匀性
 * 2.1 在一段区间之内，hash函数的值要尽量均匀的分布
 * 假设输出的值在一个范围之内 那么在这段范围之间的值要尽量均匀的分布
 * 用一根相同长度的绳子去截取这个区间 那么在这跟绳子在这段区间之内的分布是大致相等的
 * 在1-9999的长度内 均匀分布一定的hash值 用一根长度为100或者其他长度绳子去截取这个区间 那么原分布的hash值在这个区间内的分布是大致相等的
 *
 * hash函数的设计：
 * 1.桶的概念 hash表的设计引入桶的概念 一个桶里面可以放多个值（hash冲突的时候放在相同的桶内）
 * 2.由于hash函数的均匀性，那么在各个桶之间的也具有均匀性 每个桶的拥有的元素的个数是大致相等的
 *
 * hash表的设计：取模是怎么处理：
 * 取模取的是桶的长度
 * % 与 &（桶的长度减1）
 *
 * 先取整再取余   取余：保留低位的信息   取整：保留高位的信息 怎么保留高位的信息：
 * bitmap的实现： 位图    int[n >> 5] |= 1<< (n&15)
 *
 * hash表的设计：
 * 1.桶的设计
 * 1.1 桶的长度
 * 桶的长度是一个质数 这是一个经验值 保证二进制的运算不受影响
 *
 * 计算hash值需要跟桶的长度相关  然后再跟桶的长度取模准确说是跟桶的长度减1进行与运算
 *
 * 2.桶内的设计
 *
 * 经典的hash表的桶内设计是 一个链表 hash冲突的时候 放在链表的尾部
 *
 * 将链表换成红黑树 只是优化了常数时间
 *
 *
 * *****************************************************
 *
 *
 *
 * 为什么时间复杂度是O(1) 为什么不是O(n) 为什么不是O(logn)
 *
 * 我们从最差的情况进行分析：
 * 1.最差的情况是什么？
 * 初始的长度是1 链表的长度为2的时候就需要进行扩容
 * 我们加到N个数据的时候 大概经历扩容的次数是logN次
 * 每次扩容需要进行的数据迁移的代价是 0 1 2 4 一直到N 大概是一个等比数列
 * 收敛的时候是N 总共假如的个数是N 时间复杂度是O（N）对于单个而言 平均的时间复杂度是O(1)
 *
 *
 * *******************************************************
 *
 *
 * 一致性hash   分布式集群的基础性算法
 *
 * 负载算法：
 * 1.随机
 * 2.取模 取模带来的问题
 * 增加机器或者减少机器 会导致数据的全量迁移
 * 这是第一个问题
 * 第二个问题
 * 3.一致性hash
 *
 *
 *
 * 布隆过滤器：
 * 类似黑名单过滤系统 只能新增不能删除
 * 一定会有失误率 首先有咨询这个问题 允不允许有失误率 不允许有失误率的话  不能使用布隆过滤器
 *
 * 放更多的数据可以使用位图
 * int类型的数组 最大的长度不能超过整数的最大值 21亿多
 * 那么最大的表示的数量就是21亿*32     整形是4个字节 32位 每一个字节都可以表示32个数
 *
 * 如何表示更多的数：
 * 1.使用long类型的数组
 * 2.使用二维矩阵表示位图
 *
 * 位图实际使用的空间 如果是m长度（bits） 那么实际使用的长度是m/8
 *
 * 布隆过滤器的过程：
 * 1.使用K个函数算出不同的hash值进行描黑（查找的时候根据每个位置找到的描黑的点 如果K个位置都描黑了 那么证明之前加入过）
 *
 * 不确定的因素：
 * 1.M的长度定多少合适 如果m过小，样本量比较大的话 那么失误率会比较大 所有的节点都被描黑了 那么失误率就是100%
 * 2.M比较大 失误率低 但是浪费了空间
 *
 * 有三个因素可以供参考：
 * 1.样本量
 * 2.失误率
 * 3.样本占用的空间
 *
 * M的大小跟的样本量 失误率有关 与样本占用空间无关
 *
 * M越大 失误率越低 呈现一个负相关的趋势
 *       n*lnp
 * m = ------------
 *      (ln2)^2
 * K的个数：
 *
 * k= ln2 * m/n
 * k与P的关系：
 * P不能一味的下降 如果只有一个HASH函数的话 那么失误率会比较高 肯定不如果多个hash好 但是hash函数不能一味的增长下去
 * 会迅速耗尽M的空间 造成资源浪费 一定会存在一个K值是最合适的
 *
 * p = (1 - e^(-kn/m))^k
 *
 * 面试的时候：
 * 1.先算出M的大小 估算一个大概的范围  向上取整 实际的值不能比理论值小
 * 2.然后根据M的大小算出一个K HASH函数的个数
 * 3.然后根据K的个数算出失误率 真实的失误率一定是比理论值低的
 *
 * 怎么找到一K个HASH函数
 * 准备两个HASH函数 一个f(md5()) 一个g(shal()) 两个HASH函数的结果是不一样的
 * f*1 + g
 * f*2 + g
 * f*3 + g
 * f*4 + g
 * .....
 *
 * 布隆过滤器一定要初始化全部数据吗？
 * 不一定 但是要有原始数量的大小以及一个预期的失误率
 *
 *
 * 经典的布隆过滤器不支持删除 为什么不支持删除呢？
 * 1.删除的时候会影响其他的数据
 * 2.删除的时候会影响失误率 为什么会影响失误率呢？
 * 布隆过滤器是讲元素的多个位置对应的hash计算了多个位置的值都要置为0
 * 由于hash冲突  置为0 会影响到其他元素的计算结果
 *
 *
 * 一致性hash：
 *
 *
 * 经典后端服务器架构：
 * 前置：hash具有很强的离散性 在取模之后依然具有很强的离散性跟均匀性
 * 在经典的后端服务器架构中，对key进行hash之后，然后取模 需要注意什么？
 * 如果对key有排序的查询需求 对key做索引 假如要找到 1<key<100这种范围查询
 * 需要在每台机器上做检索然后在汇总merge  ==》map reduce
 *
 *
 * 多台机器做负载均衡的话
 * 高频的key一定要要有一定的数量
 * 中频的key一定要有一定的数量
 * 低频的key一定要有一定的数量
 *
 * 要实现这个  只能在业务key去挖掘什么key适合做hashkey
 *
 * 如果要做到负载均衡的话 高频 中频 低频一定要有一定的数量
 *
 * 极端例子：
 * 如果使用国家名 做key的话 所有的国家会均匀分布在所有的机器上
 * 假设高频的key是中美这两个 所有的key在机器上去均匀分布的
 * 高频只有两个 势必会导致负载不均衡
 *
 * 分布式数据库在设计key的时候让他的种类多一点 比如身份证id数量足够多
 * 可以保证高频的key有一定的数量 低频的key有一定的数量 中频的key有一定的数量
 *
 * 传统的负载有什么问题：
 * 迁移数据的成本是全量的
 * 之前的数据量是1亿 三台机器 现在数据量增大到了10亿 增加三台机器
 * 意味着之前三台机器的数据会重新计算hash值 然后重新取模 数据迁移到哪台机器上
 *
 *
 * ==》数据迁移的成本是全量的 减机器是一样
 *
 *
 * 一致性hash：
 * 可以做到迁移数据的成本很低
 * 也可以做到增加或者减少机器的成本很低
 *
 * 一致性hash不取模 一致性hash是一个环
 *
 *
 * 假设有三台机器（要么是ip不同 要么是hostname） 想象成一个hash环  由于hash具有离散性 不能保证这三台机器的hash值是均匀分布
 *
 *
 * 假设m1 m2 m3能够均分整个环 怎么找到对应物理节点 顺时针找到的第一个节点
 * 如果能保证m1 m2 m3能够均分整个环 那么可以认为三台机器实现了负载均衡
 *
 * 怎么找到对应的位置 对m1 m2 m3的hash值进行排序
 *
 * 每一个逻辑小实例维护一个已经排序了的m1 m2 m3的hash值
 *
 * 找到的第一个数比他大的数  可以使用二分
 *
 * 没有解决的问题：
 * 1.环很小的时候  不能把环均分
 * 2.一开始是均分的 但是后面加节点依然导致分的不均匀
 *
 *
 * 解决上面的问题：
 * 虚拟节点技术
 *
 * 不再使用物理节点去抢占位置 而是使用虚拟节点去抢占位置
 *
 * 每个物理节点维护一定数量的路由表 由维护的路由表的这些节点去抢位置 虚拟节点的数量越多 越能分的均匀
 *
 * 举个例子：
 * 每个机器都有1000个字符串 是一张路由表 用所有的字符串去抢占位置
 *
 * 每个区域抢占到的位置都有自己的物理机的节点
 *
 * 只使用m1 m2 m3去抢占位置 本身的节点的数量比较少 无法均匀分布 但是每个机器的虚拟的节点去抢占位置的时候
 * 既然HASH函数具有离散性 我在环上随意画等长线段 每个线段拥有的点的数量应该是差不多
 *
 * 两个虚拟节点之后迁移数据值需要进行虚拟节点之后的数据迁移  只需要虚拟节点的数据转移到新的物理节点即可
 *
 *   同时也可以进行负载均衡的动作：
 *
 *   假设 m1机器的性能很强  m2 的性能一般   但是m3的性能相对较差
 *
 *   那么可以给m1分配更多的虚拟节点  m2分配中等的虚拟节点  m3分配较少的虚拟节点
 *   可以实现均匀的负载均衡
 *
 *
 *   机器宕机：
 *   1.先转移数据再进行下线
 *   2.严重的灾难 需要对物理节点进行主备操作
 *
 *
 *
 */
public class Code01_Hash {
}
