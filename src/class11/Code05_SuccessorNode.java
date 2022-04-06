package class11;

import org.w3c.dom.Node;

//后继节点
public class Code05_SuccessorNode {

    private static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }

    //1、node有右树，那么后继节点为右树的最左子节点
    //2、node无右树，一直往上找，
    public static Node getSuccessorNode(Node node) {
        if (node == null) return null;
        if (node.right == null) {
            Node parent = node.parent;
            while (parent != null && parent.right == node) {
                node = parent;
                parent = node.parent;
            }
            return parent;
        } else {
            return getMostLeft(node.right);
        }
    }

    public static Node getMostLeft(Node node) {
        if (node == null) return null;
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
