package systemimprove.code49;

public class Code01_Redis01 {
    /**
     * 为什么要用redis
     * 1.https://db-engines.com/en/ranking 排名网站
     *
     * 文件的存储 页的概念
     * 磁盘预读都是以页为单位的 一页4kb
     * 随着文件的不断增加 文件系统的页也会增加 文件的查询的性能会下降 为什么会下降 因为页的增加 磁盘IO的次数增加
     *
     * 数据库也是这种形式 引入页的概念 每一个非叶子节点存储的都是页数据 使用B树或者B+树
     * 数据库的性能得到保证 减少IO的次数
     *
     * 经典的mysql架构：
     * 1.客户端
     * 2.连接器
     * 3.分析器
     * 4.优化器
     * 5.执行器
     * 6.缓存
     * 7.存储引擎
     * 8.文件系统
     *
     * 其中IO主要发生在存储引擎 发生的是磁盘IO
     *
     * 是不是随着数据的增大 性能一定会变差
     * 不一定
     * 如果是少量的查询 得益于数据库的特殊设计的 性能依然很好
     * 如果查询的数量变多 IO变成瓶颈
     *
     */
}
