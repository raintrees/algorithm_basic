package class15;

public class Code02_NumberOfIslands {

    public static int numIslands3(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int islands = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1') {
                    islands++;
                    infect(grid, i, j);
                }
            }
        }
        return islands;
    }

    public static void infect(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') return;
        grid[i][j] = '2';
        infect(grid, i - 1, j);
        infect(grid, i + 1, j);
        infect(grid, i, j - 1);
        infect(grid, i, j + 1);
    }

    public static int numIslands2(char[][] grid) {
        UnionFind2 unionFind2 = new UnionFind2(grid);
        for (int i = 1; i < grid.length; i++) {
            if (grid[i][0] == '1' && grid[i - 1][0] == '1') {
                unionFind2.union(i, 0, i - 1, 0);
            }
        }

        for (int j = 1; j < grid[0].length; j++) {
            if (grid[0][j] == '1' && grid[0][j - 1] == '1') {
                unionFind2.union(0, j, 0, j - 1);
            }
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    if (grid[i - 1][j] == '1') {
                        unionFind2.union(i, j, i - 1, j);
                    }
                    if (grid[i][j - 1] == '1') {
                        unionFind2.union(i, j, i, j - 1);
                    }
                }
            }
        }
        return unionFind2.sets();
    }

    private static class UnionFind2 {
        private int[] parents;
        private int[] sizes;
        private int sets;
        private int[] help;
        private int col;

        public UnionFind2(char[][] grid) {
            int row = grid.length;
            col = grid[0].length;
            int len = row * col;
            parents = new int[len];
            sizes = new int[len];
            sets = 0;
            help = new int[len];
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (grid[i][j] == '1') {
                        int index = getIndex(i, j);
                        parents[index] = index;
                        sizes[index] = 1;
                        sets++;
                    }
                }
            }
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

    }


}
