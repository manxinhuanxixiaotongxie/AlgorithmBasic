package leetcode;

public class Code0394 {
    public String decodeString(String s) {
        return process(s.toCharArray(), 0).ans;
    }

    private Info process(char[] chars, int index) {
        StringBuilder sb = new StringBuilder();
        while (index < chars.length && chars[index] != ']') {
            if (chars[index] >= '0' && chars[index] <= '9') {
                int times = 0;
                while (chars[index] >= '0' && chars[index] <= '9') {
                    times = times * 10 + chars[index] - '0';
                    index++;
                }
                index++;
                Info next = process(chars, index);
                index = next.index + 1;
                for (int i = 0; i < times; i++) {
                    sb.append(next.ans);
                }
            } else {
                sb.append(chars[index++]);
            }
        }
        return new Info(sb.toString(), index);
    }


    class Info {
        String ans;
        int index;

        Info(String ans, int index) {
            this.ans = ans;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        Code0394 code0394 = new Code0394();
        System.out.println(code0394.decodeString("[a]2[bc]"));
    }
}
