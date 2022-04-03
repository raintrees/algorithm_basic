package class16;

import java.util.*;

//最小生成树，K算法

/**
 * 1）总是从权值最小的边开始考虑，依次考察权值依次变大的边
 * 2）当前的边要么进入最小生成树的集合，要么丢弃
 * 3）如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4）如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5）考察完所有边之后，最小生成树的集合也得到了
 */
public class Code04_Kruskal {

    public static Set<Edge> kruskalMST(Graph graph) {
        PriorityQueue<Edge> heap = new PriorityQueue<>(new EdgeWeightComparator());
        for (Edge edge : graph.edges) {
            heap.add(edge);
        }
        List<Node> nodes = new ArrayList<>();
        for (Node node : graph.nodes.values()) {
            nodes.add(node);
        }
        UnionFind unionFind = new UnionFind(nodes);
        Set<Edge> set = new HashSet<>();
        while (!heap.isEmpty()) {
            Edge edge = heap.poll();
            if (!unionFind.isSameSet(edge.from, edge.to)) {
                set.add(edge);
                unionFind.union(edge.from, edge.to);
            }
        }
        return set;
    }

    private static class EdgeWeightComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    private static class UnionFind {
        private HashMap<Node, Node> parents;
        private HashMap<Node, Integer> sizes;

        public UnionFind(List<Node> nodes) {
            parents = new HashMap<>();
            sizes = new HashMap<>();
            for (Node node : nodes) {
                parents.put(node, node);
                sizes.put(node, 1);
            }
        }

        private Node find(Node node) {
            Stack<Node> stack = new Stack<>();
            while (node != parents.get(node)) {
                stack.push(node);
                node = parents.get(node);
            }
            while (!stack.isEmpty()) {
                parents.put(stack.pop(), node);
            }
            return node;
        }

        public void union(Node a, Node b) {
            Node aHead = find(a);
            Node bHead = find(b);
            if (aHead != bHead) {
                int aSize = sizes.get(aHead);
                int bSize = sizes.get(bHead);
                Node big = aSize >= bSize ? aHead : bHead;
                Node small = big == aHead ? bHead : aHead;
                parents.put(small, big);
                sizes.put(big, aSize + bSize);
                sizes.remove(small);
            }
        }

        public boolean isSameSet(Node a, Node b) {
            return find(a) == find(b);
        }
    }
}
