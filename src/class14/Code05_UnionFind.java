package class14;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

//并查集
public class Code05_UnionFind<T> {

    private static class Node<V> {
        V value;

        public Node(V v) {
            value = v;
        }
    }

    private static class UnionFind<V> {
        private HashMap<V, Node<V>> nodes;
        private HashMap<Node<V>, Node<V>> parentMap;
        private HashMap<Node<V>, Integer> sizes;

        public UnionFind(List<V> list) {
            nodes = new HashMap<>();
            parentMap = new HashMap<>();
            sizes = new HashMap<>();
            for (V v : list) {
                Node<V> node = new Node<>(v);
                nodes.put(v, node);
                parentMap.put(node, node);
                sizes.put(node, 1);
            }
        }

        private Node<V> find(Node<V> node) {
            Stack<Node<V>> stack = new Stack<>();
            while (node != parentMap.get(node)) {
                stack.push(node);
                node = parentMap.get(node);
            }
            while (!stack.isEmpty()) {
                parentMap.put(stack.pop(), node);
            }
            return node;
        }

        public boolean isSameSet(V a, V b) {
            return find(nodes.get(a)) == find(nodes.get(b));
        }

        public void union(V a, V b) {
            Node<V> aHead = find(nodes.get(a));
            Node<V> bHead = find(nodes.get(b));
            if (aHead != bHead) {
                int aSize = sizes.get(aHead);
                int bSize = sizes.get(bHead);
                Node<V> big = aSize >= bSize ? aHead : bHead;
                Node<V> small = big == aHead ? bHead : aHead;
                parentMap.put(small, big);
                sizes.put(big, aSize + bSize);
                sizes.remove(small);
            }
        }
    }


}
