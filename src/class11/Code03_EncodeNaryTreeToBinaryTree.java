package class11;

import structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Code03_EncodeNaryTreeToBinaryTree {

    // 提交时不要提交这个类
    public static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    public static TreeNode encode(Node root) {
        if (root == null) return null;
        TreeNode head = new TreeNode(root.val);
        head.left = en(root.children);
        return head;
    }

    private static TreeNode en(List<Node> children) {
        TreeNode head = null;
        TreeNode cur = null;
        for (Node child : children) {
            TreeNode node = new TreeNode(child.val);
            if (head == null) {
                head = node;
            } else {
                cur.right = node;
            }
            cur = node;
            cur.left = en(child.children);
        }
        return head;
    }

    public static Node decode(TreeNode head) {
        if (head == null) return null;
        return new Node(head.val, de(head.left));
    }

    private static List<Node> de(TreeNode node) {
        List<Node> children = new ArrayList<>();
        while (node != null) {
            Node child = new Node(node.val, de(node.right));
            children.add(child);
            node = node.left;
        }
        return children;
    }
}
