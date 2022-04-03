package class16;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Code03_TopologicalOrderDFS1 {

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
        private int deep;

        public Record(DirectedGraphNode node, int deep) {
            this.node = node;
            this.deep = deep;
        }
    }

    private static class RecordComparator implements Comparator<Record> {

        @Override
        public int compare(Record o1, Record o2) {
            return o1.deep - o2.deep == 0 ? 0 : (o1.deep > o2.deep ? -1 : 1);
        }
    }

    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        HashMap<DirectedGraphNode, Record> order = new HashMap<>();
        for (DirectedGraphNode node : graph) {
            f(node, order);
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

    public static Record f(DirectedGraphNode node, HashMap<DirectedGraphNode, Record> order) {
        if (order.containsKey(node)) {
            return order.get(node);
        }
        int deep = 0;
        for (DirectedGraphNode neighbor : node.neighbors) {
            deep = Math.max(deep, f(neighbor, order).deep);
        }
        Record record = new Record(node, deep + 1);
        order.put(node, record);
        return record;
    }

}
