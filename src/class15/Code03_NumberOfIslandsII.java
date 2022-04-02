package class15;

import java.util.ArrayList;
import java.util.List;

public class Code03_NumberOfIslandsII {

    public static List<Integer> numIslands21(int m, int n, int[][] positions) {
        UnionFind unionFind = new UnionFind(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            ans.add(unionFind.connect(position[0], position[1]));
        }
        return ans;
    }

    private static class UnionFind {
        private int[] parents;
        private int[] sizes;
        private int sets;
        private int[] help;
        int col;
        int row;

        public UnionFind(int m, int n) {
            row = m;
            col = n;
            int len = row * col;
            parents = new int[len];
            sizes = new int[len];
            sets = 0;
            help = new int[len];
        }

        private int getIndex(int i, int j) {
            return i * col + j;
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

        public void union(int i1, int j1, int i2, int j2) {
            if (i1 < 0 || i1 >= row || j1 < 0 || j1 >= col || i2 < 0 || i2 >= row || j2 < 0 || j2 >= col) return;
            int aHead = find(getIndex(i1, j1));
            int bHead = find(getIndex(i2, j2));
            if (aHead != bHead) {
                if (sizes[aHead] >= sizes[bHead]) {
                    parents[bHead] = aHead;
                    sizes[aHead] += sizes[bHead];
                } else {
                    parents[aHead] = bHead;
                    sizes[bHead] += sizes[aHead];
                }
                sets--;
            }
        }

        public int connect(int i, int j) {
            int index = getIndex(i, j);
            if (sizes[index] == 0) {
                union(i, j, i - 1, j);
                union(i, j, i + 1, j);
                union(i, j, i, j - 1);
                union(i, j, i, j + 1);
            }
            return sets;
        }

    }


}
