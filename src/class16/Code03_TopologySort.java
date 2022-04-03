package class16;

import java.util.*;

//拓扑排序
public class Code03_TopologySort {

    public static List<Node> sortedTopology(Graph graph) {
        //每个点的剩余入度
        Map<Node, Integer> inMap = new HashMap<>();
        //入度为零的点进入队列
        Queue<Node> zeroInQueue = new LinkedList<>();
        List<Node> ans = new ArrayList<>();
        for (Node node : graph.nodes.values()) {
            inMap.put(node, node.in);
            if (node.in == 0) zeroInQueue.add(node);
        }

        while (!zeroInQueue.isEmpty()) {
            Node node = zeroInQueue.poll();
            ans.add(node);
            for (Node next : node.nexts) {
                inMap.put(next, inMap.get(next) - 1);
                if (inMap.get(next) == 0) {
                    zeroInQueue.add(next);
                }
            }
        }
        return ans;
    }


}
