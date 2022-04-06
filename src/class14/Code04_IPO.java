package class14;


import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入正数数组costs、正数数组profits、正数K和正数M
 * costs[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能挣到的钱(利润)
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目，不能并行的做项目。
 * 输出：最后获得的最大钱数
 */
public class Code04_IPO {

    private static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    private static class ProgramCComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }

    private static class ProgramPComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }

    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        Program[] programs = new Program[profits.length];
        for (int i = 0; i < profits.length; i++) {
            programs[i] = new Program(profits[i], capital[i]);
        }
        PriorityQueue<Program> cHeap = new PriorityQueue<>(new ProgramCComparator());
        PriorityQueue<Program> pHeap = new PriorityQueue<>(new ProgramPComparator());
        for (int i = 0; i < programs.length; i++) {
            cHeap.add(programs[i]);
        }
        while (k-- != 0) {
            while (!cHeap.isEmpty() && cHeap.peek().c <= w) {
                pHeap.add(cHeap.poll());
            }
            if (pHeap.isEmpty()) {
                return w;
            }
            w += pHeap.poll().p;
        }
        return w;
    }


}
