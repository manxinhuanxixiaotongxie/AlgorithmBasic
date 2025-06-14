package systemimprove.code33;

/**
 * 经典的hash结构的实现：
 * 前置：哈希函数的特性
 * 1.相同的输入一定会产生相同的输出
 * 2.不同的输入，可能会产生相同的输出 hash冲突
 * <p>
 * 1.离散性 hash的离散性体现在什么地方呢？
 * 1.1 输入的值可能具有很强的规律性，但是输出的值一定要具有很强的离散性
 * 2.均匀性
 * <p>
 * 一个点经过hash函数的映射一定可以映射到out上的一个点上
 * 再有一个点 一定可以通过哈希函数映射到out区域一个点
 * 如果把这个输出想象成一个数轴 有大量的输入 每一个输入都会点在这个数轴上的某一个点上
 * 大量的输入进来之后 映射完了之后 在整个区域是非常离散的
 * 拿一个绳子去截取这个区域上的任意区域 一定是均匀的
 * <p>
 * 甚至可以做一个比喻 将输出域想象成 一个房子 输入想象成一瓶香水
 * HASH函数会把这个香水喷到这个房子的任意一个地方 导致的效果是 一个人在这个房间的任意的位置都能闻到这个香水的味道并且味道是差不多的
 * <p>
 * 2.1 在一段区间之内，hash函数的值要尽量均匀的分布
 * 假设输出的值在一个范围之内 那么在这段范围之间的值要尽量均匀的分布
 * 用一根相同长度的绳子去截取这个区间 那么在这跟绳子在这段区间之内的分布是大致相等的
 * 在1-9999的长度内 均匀分布一定的hash值 用一根长度为100或者其他长度绳子去截取这个区间 那么原分布的hash值在这个区间内的分布是大致相等的
 * <p>
 * hash函数的设计：
 * 1.桶的概念 hash表的设计引入桶的概念 一个桶里面可以放多个值（hash冲突的时候放在相同的桶内）
 * 2.由于hash函数的均匀性，那么在各个桶之间的也具有均匀性 每个桶的拥有的元素的个数是大致相等的
 * <p>
 * hash表的设计：取模是怎么处理：
 * 取模取的是桶的长度
 * % 与 &（桶的长度减1）
 * <p>
 * 先取整再取余   取余：保留低位的信息   取整：保留高位的信息 怎么保留高位的信息：
 * bitmap的实现： 位图    int[n >> 5] |= 1<< (n & 15)
 * <p>
 * hash表的设计：
 * <p>
 * <p>
 * 经典的hash表的设计：
 * <p>
 * 哈希表是怎么把数据挂进去的；
 * ABC是key  25是value
 * <p>
 * 先把ABC通过hash函数计算出一个值  然后再跟桶的长度取模
 * 假设模完的值是5 就把这个记录挂在5下面
 * 经典的hash相同的key会挂在同一个桶下面 通过单链表的形式挂在同一个桶下面
 * 相同的hash值怎么挂  顺着向下挂
 * <p>
 * <p>
 * <p>
 * 1.桶的设计
 * 1.1 桶的长度
 * 桶的长度是一个质数 这是一个经验值 保证二进制的运算不受影响
 * <p>
 * <p>
 * <p>
 * 计算hash值需要跟桶的长度相关  然后再跟桶的长度取模准确说是跟桶的长度减1进行与运算
 * <p>
 * 2.桶内的设计
 * <p>
 * 经典的hash表的桶内设计是 一个链表 hash冲突的时候 放在链表的尾部
 * <p>
 * 将链表换成红黑树 只是优化了常数时间
 * <p>
 * <p>
 * hash表的扩容操作：
 * 随着新K的不断出现 会导致桶内的元素越来越多
 * 总会有一个时刻会让我觉得难受
 * 这时刻是什么时候 每个余元都有自己的规定
 * 如果新挂的时候已经发现他所在的桶里已经到了规定的长度
 * 根据hash表的均匀性 可以认为其他的链基本也已经满了
 * <p>
 * 扩容的过程：
 * 1.将桶的长度扩大一倍 每个数据怎么办呢  每个数据都重新计算 如果扩容之后的长度是34的话
 * 那么将所有值都模上34 重新决定每个数据都在哪个桶里面
 * 毫无疑问在扩容之后  链表长度大概会变成原来的长度的一半
 * 此时再接受新的key 什么时候再感觉到难受了 会重新进行扩容
 * <p>
 * 那么因为存在扩容行为，时间复杂度怎么计算：
 * 怎么这年增删改查的时间复杂度都是O(1)呢？
 * 我们现在使用最差的情况进行时间复杂度的计算
 * 假设链表的长度是2的时候就需要进行扩容
 * 现在有N个数据 假设初始化N=1000 那么这1000个数据经历了多少次的扩容行为
 * 第一个数进来
 * 第二个数进来 扩容 长度为2
 * 第三个数进来 扩容 长度为4
 * 第五个数进来 扩容 长度为8
 * 第九个数进来的时候 扩容 长度为16
 * 低17个数进来的时候 扩容 长度为32
 * 低33个数进来的时候 扩容 长度为64
 * ....
 *
 * 上述的含义是：hash的长度假设是8 那么总共存在的数据就是的16 第17个数到来的时候 需要进行扩容
 * 扩容消耗的代价是16
 * 那么在长度为2的时候，第三个元素要进来 就需要进行扩容 在扩容的过程中 需要将所有的元素进行rehash的操作
 * 在rehash的过程中 需要对hash表中的数据进行重新计算 消耗的次数是16
 * 因此可以得出这个结论：扩容的代价是16
 *
 * 在数据量是N的时候 总消耗代价是：
 * 举个例子：总数据量N=1000
 *
 * 这1000个数据经历的扩容行为的总代价是：
 * 2+4+8+16+32+64+128+256+512 = 1022
 * 前面可能会不符合 后面会越来越符合
 * 因为我们说哈希表的增删改查的代价指的是单次的代价
 * 趋近于O(1)
 * <p>
 * <p>
 * <p>
 * 这是最接近极限的时候 hash表的代价 在工程上的改进
 * 1.一开始的时候桶的长度设计的比较大
 * 2.链表的长度设计的长一点
 * 3.使用红黑树代替链表
 * <p>
 * 在工程上 hash表的改进都与时间复杂度无关 时间复杂度都是O(1) 对于时间复杂度来说 无关轻重
 * <p>
 * <p>
 * <p>
 * <p>
 * *****************************************************
 * <p>
 * <p>
 * <p>
 * 为什么时间复杂度是O(1) 为什么不是O(n) 为什么不是O(logn)
 * <p>
 * 我们从最差的情况进行分析：
 * 1.最差的情况是什么？
 * 初始的长度是1 链表的长度为2的时候就需要进行扩容
 * 我们加到N个数据的时候 大概经历扩容的次数是logN次
 * 每次扩容需要进行的数据迁移的代价是 0 1 2 4 一直到N 大概是一个等比数列
 * 收敛的时候是N 总共假如的个数是N 时间复杂度是O（N）对于单个而言 平均的时间复杂度是O(1)
 * <p>
 * <p>
 * *******************************************************
 * <p>
 * <p>
 * 一致性hash   分布式集群的基础性算法
 * <p>
 * 负载算法：
 * 1.随机
 * 2.取模 取模带来的问题
 * 增加机器或者减少机器 会导致数据的全量迁移
 * 这是第一个问题
 * 第二个问题  数据倾斜
 * 3.一致性hash
 * <p>
 * <p>
 * <p>
 * 布隆过滤器：
 * 不安全的网页的黑名单包含100亿个黑名单网页 每个网页url最多占用64B 现在想实现一个网页过滤系统
 * 利用该系统可以根据网页的url判断该网页是否在黑名单中 请你设计这个系统
 * <p>
 * 要求：
 * 1.该系统允许有一定的误判率 不可避免
 * 2，使用的额外空间不能超过30GB
 * <p>
 * 类似黑名单过滤系统 经典的布隆过滤器只能新增不能删除
 * <p>
 * 一定会有失误率 首先有咨询这个问题 允不允许有失误率 不允许有失误率的话  不能使用布隆过滤器
 * <p>
 * 放更多的数据可以使用位图
 * int类型的数组 最大的长度不能超过整数的最大值 21亿多
 * 那么最大的表示的数量就是21亿*32     整形是4个字节 32位 每一个字节都可以表示32个数
 * <p>
 * 如何表示更多的数：
 * 1.使用long类型的数组
 * 2.使用二维矩阵表示位图
 * <p>
 * 位图实际使用的空间 如果是m长度（bits） 那么实际使用的长度是m/8
 * <p>
 * 布隆过滤器的过程（以三个hash函数为例）：
 * 1.使用第一个HASH函数计算出一个hash值，然后在m上对应的位置进行描黑
 * 2.使用第二个hash函数计算出一个hash值，然后在m上对应的位置进行描黑
 * 3.使用第三个hash函数计算出一个hash值，然后在m上对应的位置进行描黑
 * 这三个hash函数是独立的 相互不影响
 * <p>
 * 不确定的因素：
 * 1.M的长度定多少合适 如果m过小，样本量比较大的话 那么失误率会比较大 所有的节点都被描黑了 那么失误率就是100%
 * 2.一定是M越大 失误率越低 但是失误率一定不可以避免
 * <p>
 * 有三个因素可以供参考：
 * 1.样本量
 * 2.失误率
 * 3.样本占用的空间
 * <p>
 * M的大小跟的样本量 失误率有关 与样本占用空间无关
 * <p>
 * M越大 失误率越低 呈现一个负相关的趋势
 * <p>
 * n*lnp
 * m = ------------
 * (ln2)^2
 * <p>
 * 计算出来是一个小数 进行向上取整
 * <p>
 * K的个数：
 * <p>
 * k定多少合适 HASH函数的个数很少 假设只有一个函数 也就是一个string通过一个HASH函数去描黑
 * 会面对采样不足的问题 失误率会比较大
 * <p>
 * 如果K比较大 有一千万个哈希函数的个数 意味着需要对一个string进行一千万次的计算过程  会迅速耗尽m的空间 导致失误率上升
 * <p>
 * 也就是 当时HASH函数的个数比较少 会面临采样不足导致失误率上升
 * HASH函数的个数比较大 会导致m比较快的耗尽 同时这时候样本量n  布隆过滤器空间大小m已经是一个确定的值
 * <p>
 * 因此失误率与函数的个数应该呈现的关系是:
 * ^
 * |  |             \
 * \           \
 * \        \
 * -----
 * |
 * |------------------------------>
 * <p>
 * 大概是一个这样的趋势
 * <p>
 * <p>
 * k= ln2 * m/n
 * <p>
 * 计算出来之后是一个小数 向上取整
 * <p>
 * k与P的关系：
 * P不能一味的下降 如果只有一个HASH函数的话 那么失误率会比较高 肯定不如果多个hash好 但是hash函数不能一味的增长下去
 * 会迅速耗尽M的空间 造成资源浪费 一定会存在一个K值是最合适的
 * <p>
 * p = (1 - e^(-kn/m))^k
 * <p>
 * 面试的时候：
 * 1.先算出M的大小 估算一个大概的范围  向上取整 实际的值不能比理论值小
 * 100亿的数据计算出来的空间大小假设是17G 问一下面试官是不是能扩大一下空间大小 升到20G
 * 那么就能得到的两个值  一个样本量的大小 一个是占用空间m的长度
 * 进行计算HASH函数的个数
 * <p>
 * 2.然后根据M的大小算出一个K HASH函数的个数
 * <p>
 * <p>
 * <p>
 * 3.然后根据K的个数算出失误率 真实的失误率一定是比理论值低的
 * <p>
 * 怎么找到一K个HASH函数
 * 准备两个HASH函数 一个f(md5()) 一个g(shal()) 两个HASH函数的结果是不一样的
 * f*1 + g
 * f*2 + g
 * f*3 + g
 * f*4 + g
 * .....
 * <p>
 * 布隆过滤器一定要初始化全部数据吗？
 * 不一定 但是要有原始数量的大小以及一个预期的失误率
 * <p>
 * <p>
 * 经典的布隆过滤器不支持删除 为什么不支持删除呢？
 * 1.删除的时候会影响其他的数据
 * 2.删除的时候会影响失误率 为什么会影响失误率呢？
 * 布隆过滤器是讲元素的多个位置对应的hash计算了多个位置的值都要置为0
 * 由于hash冲突  置为0 会影响到其他元素的计算结果
 * <p>
 * <p>
 * 一致性hash：
 * <p>
 * <p>
 * 经典后端服务器架构：
 * 前置：hash具有很强的离散性 在取模之后依然具有很强的离散性跟均匀性
 * <p>
 * 在经典的后端服务器架构中，对key进行hash之后，然后取模 需要注意什么？
 * <p>
 * 如果对key有排序的查询需求 对key做索引 假如要找到 1<key<100这种范围查询
 * <p>
 * 需要在每台机器上做检索然后在汇总merge  ==》map reduce
 * <p>
 * <p>
 * 假设有三台机器 一定数量的key在经过hash函数之后一定能够均匀的分布在三台机器上
 * 但是假设hashkey选的不合理的话 可能会出现验证的问题
 * 比如使用国家名作为hashkey  高频的key是中美 低频的key是其他国家
 * 尽管数据在三台机器上是均匀分布的 但是高频的key一定会在同一台机器上
 * <p>
 * 我们在进行hashkey的选择上一定要保证高频的key 中频 低频的key都有一定的数量
 * 基本数量都是差不多的
 * <p>
 * 多台机器做负载均衡的话
 * 高频的key一定要要有一定的数量
 * 中频的key一定要有一定的数量
 * 低频的key一定要有一定的数量
 * <p>
 * 要实现这个  只能在业务key去挖掘什么key适合做hashkey
 * <p>
 * 如果要做到负载均衡的话 高频 中频 低频一定要有一定的数量
 * <p>
 * 极端例子：
 * 如果使用国家名 做hashkey的话 所有的国家会均匀分布在所有的机器上
 * 假设高频的hashkey是中美这两个 所有的key在机器上去均匀分布的(hash函数均匀性)
 * 高频只有两个 势必会导致负载不均衡
 * <p>
 * 分布式数据库在设计key的时候让他的种类多一点 比如身份证id数量足够多
 * 可以保证高频的key有一定的数量 低频的key有一定的数量 中频的key有一定的数量
 * <p>
 * 传统的负载有什么问题：
 * 迁移数据的成本是全量的
 * 之前的数据量是1亿 三台机器 现在数据量增大到了10亿 增加三台机器
 * 意味着之前三台机器的数据会重新计算hash值 然后重新取模 数据迁移到哪台机器上
 * <p>
 * <p>
 * ==》数据迁移的成本是全量的 减机器是一样
 * <p>
 * <p>
 * <p>
 * 一致性hash：
 * 可以做到迁移数据的成本很低
 * 也可以做到增加或者减少机器的成本很低
 * <p>
 * 一致性hash不取模 一致性hash是一个环
 * <p>
 * <p>
 * 假设有三台机器（要么是ip不同 要么是hostname） 想象成一个hash环  由于hash具有离散性 不能保证这三台机器的hash值是均匀分布
 * <p>
 * <p>
 * 怎么保证机器上环没有冲突：
 * 1.数量比较小的时候 冲突不影响
 * 2.真的冲突了 那就冲突了  m1 m2占用了环上的相同位置 那就让M1 m2保存相同的数据
 * <p>
 * 概率其实是很低  MD5hash冲突的概率 一毫秒用一个字符窜使用MD5计算hash值 一万年才会出现一次冲突
 * <p>
 * <p>
 * 使用hash环的好处是：
 * 原来m1 m2 m3已经均匀分摊了所有样本 这时候新增 一台机器 数据转移的成本是多少呢 不是全量了
 * 假设M4放在 m1 m2之间 那么数据迁移的成本就是m1-m4这段 m4-m2这段以及环上其他位置不需要进行调整 迁移的成本比较小
 * <p>
 * 查找一个数据怎么查找呢？ 计算一个hash值出来之后，顺时针沿着这个环进行查找 找到对一个物理节点 查找数据的时候是顺时针找到的第一个节点
 * <p>
 * 与在一个有序数组中找到离计算出来hash最近比他大是一样的
 * 并且这个额过程是可以二分的
 * <p>
 * 假设m1 m2 m3能够均分整个环 怎么找到对应物理节点 顺时针找到的第一个节点
 * 如果能保证m1 m2 m3能够均分整个环 那么可以认为三台机器实现了负载均衡
 * <p>
 * 怎么找到对应的位置 对m1 m2 m3的hash值进行排序
 * <p>
 * 每一个逻辑小实例维护一个已经排序了的m1 m2 m3的hash值
 * <p>
 * 找到的第一个数比他大的数  可以使用二分
 * <p>
 * 没有解决的问题：
 * 1.环很小的时候  不能把环均分
 * 2.一开始是均分的 但是后面加节点依然导致分的不均匀
 * <p>
 * <p>
 * <p>
 * <p>
 * 解决上面的问题：
 * 虚拟节点技术
 * <p>
 * 不再使用物理节点去抢占位置 而是使用虚拟节点去抢占位置
 * <p>
 * 每个物理节点维护一定数量的路由表 由维护的路由表的这些节点去抢位置 虚拟节点的数量越多 越能分的均匀
 * <p>
 * 举个例子：
 * 每个机器都有1000个字符串 是一张路由表 用所有的字符串去抢占hash环上面的位置
 * <p>
 * 每个区域抢占到的位置都有自己的物理机的节点
 * <p>
 * 只使用m1 m2 m3去抢占位置 本身的节点的数量比较少 无法均匀分布 但是每个机器的虚拟的节点去抢占位置的时候
 * 既然HASH函数具有离散性 我在环上随意画等长线段 每个线段拥有的点的数量应该是差不多
 * <p>
 * 两个虚拟节点之后迁移数据值需要进行虚拟节点之后的数据迁移  只需要虚拟节点的数据转移到新的物理节点即可
 * <p>
 * 同时也可以进行负载均衡的动作：
 * <p>
 * 假设 m1机器的性能很强  m2 的性能一般   但是m3的性能相对较差
 * <p>
 * 那么可以给m1分配更多的虚拟节点  m2分配中等的虚拟节点  m3分配较少的虚拟节点
 * 可以实现均匀的负载均衡
 * <p>
 * <p>
 * 机器宕机：
 * 1.先转移数据再进行下线
 * 2.严重的灾难 需要对物理节点进行主备操作
 * <p>
 * <p>
 * 一致性hash是今天所有分布式的基础
 */
public class Code01_Hash {
}
