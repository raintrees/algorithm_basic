package class07;

//最大线段重合问题

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Properties;

/**
 * 给定很多线段，每个线段都有两个数组[start, end]，表示线段开始位置和结束位置，左右都是闭区间
 * 规定：
 * 1）线段的开始和结束位置一定都是整数值
 * 2）线段重合区域的长度必须>=1
 * 返回线段最多重合区域中，包含了几条线段
 */
public class Code01_CoverMax {

    public static int coverMax(int[][] nums) {

        Program[] programs = new Program[nums.length];
        for (int i = 0; i < nums.length; i++) {
            programs[i] = new Program(nums[i][0], nums[i][1]);
        }
        Arrays.sort(programs, new ProgramComparator());
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        int ans = 0;
        for (int i = 0; i < programs.length; i++) {
            while (!queue.isEmpty() && queue.peek() <= programs[i].start) {
                queue.poll();
            }
            queue.add(programs[i].end);
            ans = Math.max(ans, queue.size());
        }
        return ans;
    }

    private static class ProgramComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.start - o2.start;
        }
    }

    private static class Program {
        private int start;
        private int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int test(int[][] lines) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lines.length; i++) {
            min = Math.min(min, lines[i][0]);
            max = Math.max(max, lines[i][1]);
        }
        int ans = 0;
        for (double i = min + 0.5; i <= max; i += 1) {
            int count = 0;
            for (int j = 0; j < lines.length; j++) {
                if (lines[j][0] <= i && lines[j][1] >= i) {
                    count++;
                }
            }
            ans = Math.max(count, ans);
        }
        return ans;
    }

    // 和maxCover2过程是一样的
    // 只是代码更短
    // 不使用类定义的写法
    public static int maxCover3(int[][] m) {
        // m是二维数组，可以认为m内部是一个一个的一维数组
        // 每一个一维数组就是一个对象，也就是线段
        // 如下的code，就是根据每一个线段的开始位置排序
        // 比如, m = { {5,7}, {1,4}, {2,6} } 跑完如下的code之后变成：{ {1,4}, {2,6}, {5,7} }
        Arrays.sort(m, (a, b) -> (a[0] - b[0]));
        // 准备好小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int max = 0;
        for (int[] line : m) {
            while (!heap.isEmpty() && heap.peek() <= line[0]) {
                heap.poll();
            }
            heap.add(line[1]);
            max = Math.max(max, heap.size());
        }
        return max;
    }

    // for test
    public static int[][] generateLines(int N, int L, int R) {
        int size = (int) (Math.random() * N) + 1;
        int[][] ans = new int[size][2];
        for (int i = 0; i < size; i++) {
            int a = L + (int) (Math.random() * (R - L + 1));
            int b = L + (int) (Math.random() * (R - L + 1));
            if (a == b) {
                b = a + 1;
            }
            ans[i][0] = Math.min(a, b);
            ans[i][1] = Math.max(a, b);
        }
        return ans;
    }

    public static void main(String[] args) {

        System.out.println("test begin");
        int N = 100;
        int L = 0;
        int R = 200;
        int testTimes = 200000;
        for (int i = 0; i < testTimes; i++) {
            int[][] lines = generateLines(N, L, R);
            int ans1 = test(lines);
            int ans2 = coverMax(lines);
            int ans3 = maxCover3(lines);
            if (ans1 != ans2 || ans1 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test end");
    }




}
