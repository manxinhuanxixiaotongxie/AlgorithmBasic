package systemimprove.code11;


import java.util.HashSet;

/**
 * 给定一个字符串str，只由‘X’和‘.’两种字符构成。
 * ‘X’表示墙，不能放灯，也不需要点亮
 * ‘.’表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class Code02_Light {

    public int getLights(String s) {
        if (s== null || s.length() == 0) {
            return 0;
        }

        char[] str = s.toCharArray();
        return process(str,0,new HashSet<>());

    }

    public int process(char[] str, int index, HashSet<Integer> set) {
        if (index == str.length) {
for (int i = 0; i < str.length; i++) {
                if (str[i] == '.') {
                    if (!set.contains(i - 1) && !set.contains(i) && !set.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return set.size();
        } else {
            int no = process(str, index + 1, set);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                set.add(index);
                yes = process(str, index + 1, set);
                set.remove(index);
            }
            return Math.min(yes, no);
        }
    }
}
