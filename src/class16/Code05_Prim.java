package class16;

import java.util.*;

/**
 * 1）可以从任意节点出发来寻找最小生成树
 * 2）某个点加入到被选取的点中后，解锁这个点出发的所有新的边
 * 3）在所有解锁的边中选最小的边，然后看看这个边会不会形成环
 * 4）如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3）
 * 5）如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2）
 * 6）当所有点都被选取，最小生成树就得到了
 */
public class Code05_Prim {

    private static class EdgeComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> primMST(Graph graph) {
        //解锁的边进入小根堆
        PriorityQueue<Edge> heap = new PriorityQueue<>(new EdgeComparator());
        //已经解锁的点
        Set<Node> nodeSet = new HashSet<>();
        Set<Edge> ans = new HashSet<>();
        for (Node node : graph.nodes.values()) {
            if (!nodeSet.contains(node)) {
                nodeSet.add(node);
                for (Edge edge : node.edges) {
                    heap.add(edge);
                }
                while (!heap.isEmpty()) {
                    Edge edge = heap.poll();
                    if (!nodeSet.contains(edge.to)) {
                        ans.add(edge);
                        for (Edge edge1 : edge.to.edges) {
                            heap.add(edge1);
                        }
                    }
                }
            }
            //break; 注释掉是为了防止森林
        }
        return ans;
    }

}
