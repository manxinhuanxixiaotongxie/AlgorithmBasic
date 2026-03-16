package leetcode.classic150;

import java.util.ArrayList;
import java.util.List;

/**
 * 你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为 更加简洁的规范路径。
 *
 * 在 Unix 风格的文件系统中规则如下：
 *
 * 一个点 '.' 表示当前目录本身。
 * 此外，两个点 '..' 表示将目录切换到上一级（指向父目录）。
 * 任意多个连续的斜杠（即，'//' 或 '///'）都被视为单个斜杠 '/'。
 * 任何其他格式的点（例如，'...' 或 '....'）均被视为有效的文件/目录名称。
 * 返回的 简化路径 必须遵循下述格式：
 *
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 *
 */
public class Code071 {
    /**
     * 思路：
     * 把path用/分割 得到一个字符串列表
     * 遍历字符串列表 用栈维护遍历过的字符串
     *
     * 1.如果当前字符串是空或者.什么也不做
     * 2，如果的当前字符串不是.. 字符入栈
     * 3.否则弹出栈顶字符串（前提是栈不为空） 模拟返回上一级目录
     *
     * 最后把栈中字符串用/连接起来 就是规范路径
     *
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        List<String> stk = new ArrayList<>();
        for (String s : path.split("/")) {
            if (s.isEmpty() || s.equals(".")) {
                continue;
            }
            if (!s.equals("..")) {
                stk.add(s);
            } else if (!stk.isEmpty()) {
                stk.removeLast(); // stk.remove(stk.size() - 1);
            }
        }
        return "/" + String.join("/", stk);
    }
}
