package class16;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Code03_TopologicalOrderDFS2 {
    private class DirectedGraphNode {
        public int label;
        public List<DirectedGraphNode> neighbors;

        public DirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<DirectedGraphNode>();
        }
    }

    private static class Record {
        private DirectedGraphNode node;
        private long nodes;

        public Record(DirectedGraphNode node, long nodes) {
            this.node = node;
            this.nodes = nodes;
        }
    }

    private static class RecordComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o1.nodes - o2.nodes == 0 ? 0 : (o1.nodes > o2.nodes ? -1 : 1);
        }
    }

    public static ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode directedGraphNode : graph) {
            f(directedGraphNode, order);
        }
        List<Record> records = new ArrayList<>();
        for (Record record : order.values()) {
            records.add(record);
        }
        records.sort(new RecordComparator());
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        for (Record record : records) {
            ans.add(record.node);
        }
        return ans;
    }

    private static Record f(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(node)) {
            return order.get(node);
        }
        long nodes = 0;
        for (DirectedGraphNode neighbor : node.neighbors) {
            nodes += Math.max(nodes, f(neighbor, order).nodes);
        }
        Record record = new Record(node, nodes + 1);
        order.put(node, record);
        return record;
    }

}
