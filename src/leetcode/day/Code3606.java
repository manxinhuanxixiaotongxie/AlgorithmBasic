package leetcode.day;

import java.util.*;

/**
 *
 * 给你三个长度为 n 的数组，分别描述 n 个优惠券的属性：code、businessLine 和 isActive。其中，第 i 个优惠券具有以下属性：
 *
 * code[i]：一个 字符串，表示优惠券的标识符。
 * businessLine[i]：一个 字符串，表示优惠券所属的业务类别。
 * isActive[i]：一个 布尔值，表示优惠券是否当前有效。
 * 当以下所有条件都满足时，优惠券被认为是 有效的 ：
 *
 * code[i] 不能为空，并且仅由字母数字字符（a-z、A-Z、0-9）和下划线（_）组成。
 * businessLine[i] 必须是以下四个类别之一："electronics"、"grocery"、"pharmacy"、"restaurant"。
 * isActive[i] 为 true 。
 * 返回所有 有效优惠券的标识符 组成的数组，按照以下规则排序：
 *
 * 先按照其 businessLine 的顺序排序："electronics"、"grocery"、"pharmacy"、"restaurant"。
 * 在每个类别内，再按照 标识符的字典序（升序）排序。
 *
 */
public class Code3606 {

     static final Map<String,Integer> map = new HashMap<>();

    static {
        map.put("electronics",0);
        map.put("grocery",1);
        map.put("pharmacy",2);
        map.put("restaurant",3);
    }

    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {

        List<String>[] group = new List[map.size()];
        Arrays.setAll(group,i->new ArrayList<>());

        for (int i = 0; i < code.length; i++) {
            Integer catagory = map.get(businessLine[i]);
            // 获取业务类别
            if (catagory != null && isValid(code[i].toCharArray()) && isActive[i]) {
                group[catagory].add(code[i]);
            }
        }
        List<String> res = new ArrayList<>();
        for (int i = 0; i < group.length; i++) {
            Collections.sort(group[i]);
            res.addAll(group[i]);
        }
        return res;
    }

    private boolean isValid(char[] str) {
        // 只能是数字 字母 下划线
        if (str == null || str.length == 0) {
            return false;
        }
        for (int i = 0; i < str.length; i++) {
            if ('A' <= str[i] && str[i] <= 'Z') {
                continue;
            } else if ('a' <= str[i] && str[i] <= 'z') {
                continue;
            } else if ('0' <= str[i] && str[i] <= '9') {
                continue;
            } else if (str[i] == '_') {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    static void main() {
        Code3606 code3606 = new Code3606();
        String[] code = {"SAVE20","","PHARMA5","SAVE@20"};
        String[] businessLine = {"restaurant","grocery","pharmacy","restaurant"};
        boolean[] isActive = {true,true,true,true};
        List<String> res = code3606.validateCoupons(code,businessLine,isActive);
        for (String s : res) {
            System.out.print(s + " ");
        }
    }
}
