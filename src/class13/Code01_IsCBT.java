package class13;

import duishuqi.ForBT;
import structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class Code01_IsCBT {

    public static boolean isCBT1(TreeNode root) {
        if (root == null) return true;
        boolean leaf = false;
        TreeNode l = null, r = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            l = root.left;
            r = root.right;
            if ((l == null && r != null) || (leaf && (l != null || r != null))) {
                return false;
            }
            if (l != null) queue.add(l);
            if (r != null) queue.add(r);
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }

    private static class Info {
        public int height;
        public boolean isFull;
        public boolean isCBT;

        public Info(int height, boolean isFull, boolean isCBT) {
            this.height = height;
            this.isFull = isFull;
            this.isCBT = isCBT;
        }
    }

    public static boolean isCBT2(TreeNode root) {
        if (root == null) return true;
        return process(root).isCBT;
    }

    private static Info process(TreeNode x) {
        if (x == null) return new Info(0, true, true);
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;
        boolean isCBT = false;
        if (leftInfo.isCBT && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        } else if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height) {
            isCBT = true;
        } else if (leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height + 1) {
            isCBT = true;
        } else if (leftInfo.isFull && rightInfo.isCBT && leftInfo.height == rightInfo.height) {
            isCBT = true;
        }
        return new Info(height, isFull, isCBT);
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = ForBT.generateRandomBST(maxLevel, maxValue);
            if (isCBT1(head) != isCBT2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
    
}
