package book_05string;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-05-09 19:05
 */
public class GetLeftestStr {

    /**
     * 给定一个字符串数组Str[] 在strs中有些位置为null
     * 但在不为null的位置上 字符串是按照字典顺序有小到大依次出现的
     * 再给定一个字符串str
     * 返回str在strs中出现的最左位置
     *
     * @param strs
     * @param str
     * @return
     */
    public int GetLeftestStr(String[] strs, String str) {

        if (strs == null || strs.length == 0 || str == null) {
            return -1;
        }
        /**
         * 尽可能采用二分
         */

        int res = -1;
        if (str == null) {
            return -1;
        }

        int left = 0;
        int right = strs.length - 1;
        int i = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (strs[mid] != null) {
                // 中间节点比str字典序大，说明最左的位置在左半区
                if (strs[mid].compareTo(str) > 0) {
                    right = mid - 1;
                }
                // 中间节点比str字典序大，说明最左的位置在右半区
                if (strs[mid].compareTo(str) < 0) {
                    left = mid + 1;
                }
                // 中间节点比str相等，还需要继续向左边寻找
                if (strs[mid].compareTo(str) == 0) {
                    res = mid;
                    right = mid - 1;
                }
            } else {
                /**
                 * 如果中间节点为null
                 * 1.从mid向左进行遍历
                 *   mid..left全部都是null 意味着要要在mid+1到right进行查找
                 *   mid..left不全都是null 1）有没有相等的值 有的话right = mid-1
                 *  2.mid从右向左重复上述步骤
                 */
                // 中间节点为空
//                right = mid -1;
//                int length = 0;
//                for (int i = mid; i >= left; i--) {
//                    if (strs[i] == null) {
//                        length++;
//                        continue;
//                    }
//
//                    if (strs[i].compareTo(str) == 0) {
//                        res = i;
//                        right = i-1;
//                        break;
//                    }
//
//                    if (strs[i].compareTo(str) >= 0) {
//                        right = i-1;
//                        break;
//                    }
//                    if (strs[i].compareTo(str) < 0) {
//                        left = mid + 1;
//                        break;
//                    }
//
//                }
//
//                if (mid == length) {
//                    left = mid+1;
//                }

                i = mid;
                while (strs[i] == null && --i >= left) ;

                if (i < left || strs[i].compareTo(str) < 0) {
                    left = mid + 1;
                } else {
                    res = strs[i].equals(str) ? i : res;
                    right = i - 1;
                }
            }
        }

        return res;
    }
}
