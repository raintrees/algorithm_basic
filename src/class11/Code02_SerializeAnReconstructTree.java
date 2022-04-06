package class11;

import com.sun.org.apache.bcel.internal.generic.NEW;
import structure.Node;
import structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code02_SerializeAnReconstructTree {


    public static Queue<String> preSerial(TreeNode root) {
        Queue<String> queue = new LinkedList<>();
        pres(root, queue);
        return queue;
    }

    private static void pres(TreeNode node, Queue<String> queue) {
        if (node == null) {
            queue.add(null);
        } else {
            queue.add(String.valueOf(node.val));
            pres(node.left, queue);
            pres(node.right, queue);
        }
    }

    public static Queue<String> posSerial(TreeNode root) {
        Queue<String> queue = new LinkedList<>();
        poss(root, queue);
        return queue;
    }

    private static void poss(TreeNode node, Queue<String> queue) {
        if (node == null) {
            queue.add(null);
        } else {
            pres(node.left, queue);
            pres(node.right, queue);
            queue.add(String.valueOf(node.val));
        }
    }

    public static Queue<String> levelSerial(TreeNode root) {
        Queue<String> queue = new LinkedList<>();
        if (root == null) {
            queue.add(null);
        } else {
            Queue<TreeNode> help = new LinkedList<>();
            queue.add(String.valueOf(root.val));
            while (!help.isEmpty()) {
                root = help.poll();
                if (root.left != null) {
                    queue.add(String.valueOf(root.left.val));
                    help.add(root.left);
                } else {
                    queue.add(null);
                }
                if (root.right != null) {
                    queue.add(String.valueOf(root.right.val));
                    help.add(root.right);
                } else {
                    queue.add(null);
                }
            }

        }
        return queue;
    }

    public static TreeNode buildByPreQueue(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) return null;
        return preb(queue);
    }

    private static TreeNode preb(Queue<String> queue) {
        String value = queue.poll();
        if (value == null) {
            return null;
        } else {
            TreeNode head = new TreeNode(Integer.valueOf(value));
            head.left = preb(queue);
            head.right = preb(queue);
            return head;
        }
    }

    public static TreeNode buildByPosQueue(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) return null;
        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()) {
            stack.push(queue.poll());
        }
        return posb(stack);
    }

    private static TreeNode posb(Stack<String> stack) {
        String value = stack.pop();
        if (value == null) {
            return null;
        } else {
            TreeNode head = new TreeNode(Integer.valueOf(value));
            head.right = posb(stack);
            head.left = posb(stack);
            return head;
        }
    }

    public static TreeNode buildByLevelQueue(Queue<String> queue) {
        if (queue == null || queue.isEmpty()) {
            return null;
        }
        TreeNode root = generateNode(queue.poll());
        if (root == null) return null;
        Queue<TreeNode> help = new LinkedList<>();
        help.add(root);
        while (!help.isEmpty()) {
            root = help.poll();
            root.left = generateNode(queue.poll());
            root.right = generateNode(queue.poll());
            if (root.left != null) {
                help.add(root.left);
            }
            if (root.right != null) {
                help.add(root.right);
            }
        }
        return root;
    }

    private static TreeNode generateNode(String value) {
        if (value == null) return null;
        return new TreeNode(Integer.valueOf(value));
    }

}
