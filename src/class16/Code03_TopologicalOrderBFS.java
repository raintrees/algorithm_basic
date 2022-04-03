package class16;

import java.util.*;

public class Code03_TopologicalOrderBFS {

    private class DirectedGraphNode {
        public int label;
        public List<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Integer> inMap = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            inMap.put(node, 0);
        }

        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                inMap.put(neighbor, inMap.get(neighbor) + 1);
            }
        }
        Queue<DirectedGraphNode> zeroInQueue = new LinkedList<>();
        for (Map.Entry<DirectedGraphNode, Integer> entry : inMap.entrySet()) {
            if (entry.getValue() == 0) {
                zeroInQueue.add(entry.getKey());
            }
        }
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        while (!zeroInQueue.isEmpty()) {
            DirectedGraphNode cur = zeroInQueue.poll();
            ans.add(cur);
            for (DirectedGraphNode neighbor : cur.neighbors) {
                inMap.put(neighbor, inMap.get(neighbor) - 1);
                if (inMap.get(neighbor) == 0) {
                    zeroInQueue.add(neighbor);
                }
            }
        }
        return ans;
    }

}
