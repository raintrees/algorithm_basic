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


}
