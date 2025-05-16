package code12;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author Scurry
 * @Date 2023-06-28 16:43
 */
public class Code09_MaxHappyParty {
    /**
     * 派对的最大快乐值
     * 员工只有一个领导
     * 最底层的员工没有下属
     * 每个员工都有一个快乐值
     * 领导去了直系下属都不能去
     */

    public static class Employee {
        int happy;
        String flag;
        List<Employee> subordinates;

        Employee(int happy, List<Employee> subordinates, String flag) {
            this.happy = happy;
            this.subordinates = subordinates;
            this.flag = flag;
        }

        public Employee(int happy, String flag) {
            this.happy = happy;
            this.flag = flag;
        }
    }

    /**
     * 领导去：
     * 员工都不能去
     * int happy = x.happy;
     * <p>
     * 领导不去：
     * 所有的下属最大的快乐值
     * <p>
     * for(Employee employee:subordinates) {
     * <p>
     * }
     */

    public static class Info {
        int yes;
        int no;

        Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static int maxHappy(Employee employee) {
        Info process = process(employee);
        return Math.max(process.no, process.yes);
    }

    public static void main(String[] args) {
        Employee employeeA = new Employee(1, "a");


        Employee employeeB = new Employee(2, "b");
        Employee employeeC = new Employee(3, "c");
        Employee employeeD = new Employee(4, "d");
        List<Employee> listA = new ArrayList<>();
        listA.add(employeeB);
        listA.add(employeeC);
        listA.add(employeeD);
        employeeA.subordinates = listA;


        List<Employee> listB = new ArrayList<>();
        Employee employeeE = new Employee(5, "e");
        Employee employeeF = new Employee(6, "f");
        Employee employeeG = new Employee(7, "g");
        listB.add(employeeE);
        listB.add(employeeF);
        listB.add(employeeG);
        employeeB.subordinates = listB;


        List<Employee> listC = new ArrayList<>();
        Employee employeeH = new Employee(8, "h");
        Employee employeeI = new Employee(9, "i");
        Employee employeeJ = new Employee(10, "j");
        listC.add(employeeH);
        listC.add(employeeI);
        listC.add(employeeJ);
        employeeC.subordinates = listC;

        List<Employee> listD = new ArrayList<>();
        Employee employeeK = new Employee(11, "k");
        Employee employeeL = new Employee(12, "l");
        Employee employeeM = new Employee(13, "m");
        listD.add(employeeK);
        listD.add(employeeL);
        listD.add(employeeM);
        employeeD.subordinates = listD;
        System.out.println(maxHappy(employeeA));

    }

    /**
     * 最大快乐值
     *
     * @param employee
     * @return
     */
    public static Info process(Employee employee) {
        if (employee.subordinates == null) {
            return new Info(employee.happy, 0);
        }
        // 当前节点来
        int yes = employee.happy;
        // 当前节点不来
        int no = 0;
        for (Employee employee1 : employee.subordinates) {
            Info process = process(employee1);
            // 领导来的话  就是子节点（整棵树）不来的值
            yes += process.no;
            // 领导不来 取当前节点来不来最大值
            no += Math.max(process.yes, process.no);

            System.out.println(yes);
            System.out.println(no);
        }
        return new Info(yes, no);
    }

}
