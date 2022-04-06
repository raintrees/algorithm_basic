package class13;

import javax.sound.sampled.DataLine.Info;
import java.util.ArrayList;
import java.util.List;

public class Code04_MaxHappy {

    public static class Employee {
        public int happy;
        public List<Employee> nexts;

        public Employee(int h) {
            happy = h;
            nexts = new ArrayList<>();
        }
    }

    private static class Info {
        public int yes;
        public int no;

        public Info(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }

    public static int maxHappy(Employee boss) {
        Info info = process(boss);
        return Math.max(info.no, info.yes);
    }

    private static Info process(Employee employee) {
        if (employee == null) return new Info(0, 0);
        int no = 0;
        int yes = employee.happy;
        for (Employee next : employee.nexts) {
            Info nextInfo = process(next);
            no += Math.max(nextInfo.no, nextInfo.yes);
            yes += nextInfo.no;
        }
        return new Info(yes, no);
    }

    public static int test(Employee boss) {
        if (boss == null) return 0;
        return Math.max(processTest(boss, true), processTest(boss, false));
    }

    public static int processTest(Employee x, boolean flag) {
        if (flag) {
            int yes = 0;
            for (Employee next : x.nexts) {
                yes += processTest(next, false);
            }
            return yes;
        } else {
            int ans1 = x.happy, ans2 = 0;
            for (Employee next : x.nexts) {
                ans1 += processTest(next, true);
                ans2 += processTest(next, false);
            }
            return Math.max(ans1, ans2);
        }
    }

    // for test
    public static Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        Employee boss = new Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            Employee next = new Employee((int) (Math.random() * (maxHappy + 1)));
            e.nexts.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxNexts = 7;
        int maxHappy = 100;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            Employee boss = genarateBoss(maxLevel, maxNexts, maxHappy);
            if (maxHappy(boss) != test(boss)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
