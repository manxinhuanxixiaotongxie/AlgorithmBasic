package leetcode.classic150;

import java.util.ArrayList;
import java.util.List;

/**
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 */
public class Code022 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        process(n << 1,0,n,0,ans,new char[2*n]);
        return ans;
    }

    public void process(int n,int index,int leftSize,int leftMinusRightSize,List<String> res,char[] path) {
        if (index == n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.length; i++) {
                sb.append(path[i]);
            }
            res.add(sb.toString());
            return;
        }
        if (leftSize > 0) {
            // 可以放置左括号
            path[index] = '(';
            process(n,index+1,leftSize-1,leftMinusRightSize+1,res,path);
            path[index] = 0;
        }
        if (leftMinusRightSize > 0) {
            // 可以放置右括号
            path[index] = ')';
            process(n,index+1,leftSize,leftMinusRightSize-1,res,path);
            path[index] = 0;
        }
    }
}
