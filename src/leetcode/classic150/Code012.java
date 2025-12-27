package leetcode.classic150;

import java.util.HashMap;
import java.util.Map;

/**
 * 整数转罗马数字
 *
 */
public class Code012 {

    static Map<Integer,String> map = null;

    static {
        map = new HashMap<>();
        map.put(1,"I");
        map.put(2,"II");
        map.put(3,"III");
        map.put(4,"IV");
        map.put(5,"V");
        map.put(6,"VI");
        map.put(7,"VII");
        map.put(8,"VIII");
        map.put(9,"IX");
        map.put(10,"X");
        map.put(20,"XX");
        map.put(30,"XXX");
        map.put(40,"XL");
        map.put(50,"L");
        map.put(60,"LX");
        map.put(70,"LXX");
        map.put(80,"LXXX");
        map.put(90,"XC");
        map.put(100,"C");
        map.put(200,"CC");
        map.put(300,"CCC");
        map.put(400,"CD");
        map.put(500,"D");
        map.put(600,"DC");
        map.put(700,"DCC");
        map.put(800,"DCCC");
        map.put(900,"CM");
        map.put(1000,"M");
        map.put(2000,"MM");
        map.put(3000,"MMM");
    }
    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int mix = 0;
        while (num > 0) {
            int cur = num % 10;
            if (cur == 0) {
                num /= 10;
                mix++;
                continue;
            }
            int curNum = cur * (int)Math.pow(10, mix);
            sb.insert(0,map.get(curNum));
            num /= 10;
            mix++;
        }
        return  sb.toString();
    }

    static void main() {
        Code012 code012 = new Code012();
        System.out.println(code012.intToRoman(10));
    }
}
