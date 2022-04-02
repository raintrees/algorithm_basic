package class15;

/**
 * https://leetcode-cn.com/problems/number-of-provinces/
 */
public class Code01_FriendCircles {

    public static int findCircleNum(int[][] M) {

        int N = M.length;
        UnionFind unionFind = new UnionFind(N);

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                if (M[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind.sets();
    }

    private static class UnionFind {
        private int[] parents;
        private int[] sizes;
        private int sets;
        private int[] help;

        public UnionFind(int N) {
            parents = new int[N];
            sizes = new int[N];
            sets = N;
            help = new int[N];
            for (int i = 0; i < N; i++) {
                parents[i] = i;
                sizes[i] = 1;
            }
        }

        private int find(int i) {
            int index = 0;
            while (i != parents[i]) {
                help[index++] = i;
                i = parents[i];
            }
            for (index--; index >= 0; index--) {
                parents[help[index]] = i;
            }
            return i;
        }

        public int sets() {
            return sets;
        }

        public void union(int i, int j) {
            int iHead = find(i);
            int jHead = find(j);
            if (iHead != jHead) {
                if (sizes[iHead] >= sizes[jHead]) {
                    parents[jHead] = iHead;
                    sizes[iHead] += sizes[jHead];
                } else {
                    parents[iHead] = jHead;
                    sizes[jHead] += sizes[iHead];
                }
                sets--;
            }
        }

    }

}
