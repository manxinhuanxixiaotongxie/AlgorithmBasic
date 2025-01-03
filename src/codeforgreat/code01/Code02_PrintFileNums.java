package codeforgreat.code01;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code02_PrintFileNums {

    /**
     * 使用递归实现
     *
     * @param path
     * @return
     */
    public int getFileNums(String path) {
        if (path == null || path.length() == 0) {
            return 0;
        }
        return process(path);
    }

    public int process(String path) {
        File file = new File(path);
        if (!file.isFile() && !file.isDirectory()) {
            return 0;
        }
        if (file.isFile()) {
            return 1;
        } else {
            // 是文件夹
            int ans = 0;
            File[] files = file.listFiles();
            for (File file1 : files) {
                if (file1.isFile()) {
                    ans++;
                } else {
                    // 是文件夹
                    ans += process(file1.getAbsolutePath());
                }
            }
            return ans;
        }
    }

    /**
     * 使用栈实现
     *
     * @param path
     * @return
     */
    public int getFileNums2(String path) {
        if (path == null || path.length() == 0) {
            return 0;
        }
        File file = new File(path);
        if (!file.isDirectory() && !file.isFile()) {
            return 0;
        }
        if (file.isFile()) {
            return 1;
        }
        int ans = 0;
        Stack<String> stack = new Stack<>();
        stack.push(path);
        while (!stack.isEmpty()) {
            String curPath = stack.pop();
            File curFile = new File(curPath);
            for (File tempFile : curFile.listFiles()) {
                if (tempFile.isFile()) {
                    ans++;
                } else {
                    stack.push(tempFile.getAbsolutePath());
                }
            }
        }
        return ans;
    }

    /**
     * 使用队列实现
     *
     * @param path
     * @return
     */
    public int getFileNums3(String path) {
        if (path == null || path.length() == 0) {
            return 0;
        }
        File file = new File(path);
        if (!file.isDirectory() && !file.isFile()) {
            return 0;
        }
        if (file.isFile()) {
            return 1;
        }
        int ans = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(path);
        while (!queue.isEmpty()) {
            String curPath = queue.poll();
            File curFile = new File(curPath);
            for (File tempFile : curFile.listFiles()) {
                if (tempFile.isFile()) {
                    ans++;
                } else {
                    queue.add(tempFile.getAbsolutePath());
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        Code02_PrintFileNums code02_printFileNums = new Code02_PrintFileNums();
        System.out.println(code02_printFileNums.getFileNums("E:\\mideawork\\202412\\合并\\background-manage-web"));
        System.out.println(code02_printFileNums.getFileNums2("E:\\mideawork\\202412\\合并\\background-manage-web"));
        System.out.println(code02_printFileNums.getFileNums3("E:\\mideawork\\202412\\合并\\background-manage-web"));
    }
}
