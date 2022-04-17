package class16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1）Dijkstra算法必须指定一个源点
 * 2）生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，源点到其他所有点的最小距离都为正无穷大
 * 3）从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
 * 4）源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
 */
public class Code06_Dijkstra {

    public static Map<Node, Integer> dijkstra1(Node from) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        Set<Node> selectedNodes = new HashSet<>();
        distanceMap.put(from, 0);
        Node minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        while (minNode != null) {
            int distance = distanceMap.get(minNode);
            for (Edge edge : minNode.edges) {
                Node toNode = edge.to;
                if (!distanceMap.containsKey(toNode)) {
                    distanceMap.put(toNode, distance + edge.weight);
                } else {
                    distanceMap.put(toNode, Math.min(distanceMap.get(toNode), distance + edge.weight));
                }
            }
            selectedNodes.add(minNode);
            minNode = getMinDistanceAndUnselectedNode(distanceMap, selectedNodes);
        }
        return distanceMap;
    }

    private static Node getMinDistanceAndUnselectedNode(Map<Node, Integer> distanceMap, Set<Node> selectedNodes) {
        int minDistance = Integer.MAX_VALUE;
        Node minNode = null;
        for (Map.Entry<Node, Integer> entry : distanceMap.entrySet()) {
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selectedNodes.contains(node) && distance < minDistance) {
                minNode = node;
                minDistance = distance;
            }
        }
        return minNode;
    }

    //改进
    public static Map<Node, Integer> dijkstra2(Node from, int size) {
        Map<Node, Integer> distanceMap = new HashMap<>();
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(from, 0);
        while (!nodeHeap.isEmpty()) {
            NodeRecord nodeRecord = nodeHeap.pop();
            for (Edge edge : nodeRecord.node.edges) {
                nodeHeap.addOrUpdateOrIgnore(edge.to, edge.weight + nodeRecord.distance);
            }
            distanceMap.put(nodeRecord.node, nodeRecord.distance);
        }
        return distanceMap;
    }

    private static class NodeRecord {
        public Node node;
        public int distance;

        public NodeRecord(Node node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    //手写加强堆
    private static class NodeHeap {
        private Node[] nodes;
        //反向索引表
        private HashMap<Node, Integer> indexMap;
        //距离表
        private HashMap<Node, Integer> distanceMap;
        private int heapSize;

        public NodeHeap(int size) {
            nodes = new Node[size];
            indexMap = new HashMap<>();
            distanceMap = new HashMap<>();
            heapSize = 0;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        private boolean isEntered(Node node) {
            return indexMap.containsKey(node);
        }

        private boolean inHeap(Node node) {
            return isEntered(node) && indexMap.get(node) != -1;
        }

        // 有一个点叫node，现在发现了一个从源节点出发到达node的距离为distance
        // 判断要不要更新，如果需要的话，就更新
        public void addOrUpdateOrIgnore(Node node, int distance) {
            if (!isEntered(node)) {//add，新节点
                nodes[heapSize] = node;
                indexMap.put(node, heapSize);
                distanceMap.put(node, distance);
                heapInsert(heapSize++);
            }
            if (inHeap(node)) {//update,更新
                distanceMap.put(node, Math.min(distanceMap.get(node), distance));
                heapInsert(indexMap.get(node));
            }
        }

        public NodeRecord pop() {
            Node node = nodes[0];
            int distance = distanceMap.get(node);
            swap(0, --heapSize);
            indexMap.put(node, -1);
            distanceMap.remove(node);
            heapify(0);
            return new NodeRecord(node, distance);
        }


        private void heapInsert(int index) {
            while (distanceMap.get(nodes[index]) < distanceMap.get(nodes[(index - 1) / 2])) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int smallest = left + 1 < heapSize && distanceMap.get(nodes[left + 1]) < distanceMap.get(nodes[left]) ? left + 1 : left;
                smallest = distanceMap.get(nodes[index]) < distanceMap.get(nodes[smallest]) ? index : smallest;
                if (smallest == index) return;
                swap(smallest, index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            indexMap.put(nodes[i], j);
            indexMap.put(nodes[j], i);
            Node temp = nodes[i];
            nodes[i] = nodes[j];
            nodes[j] = temp;
        }


    }


}
