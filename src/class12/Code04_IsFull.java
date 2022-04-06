package class12;

import duishuqi.ForBT;
import structure.TreeNode;

//满
public class Code04_IsFull {

    private static class Info1 {
        public int height;
        public int nodes;

        public Info1(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }

    public static boolean isFull1(TreeNode root) {
        Info1 info = process1(root);
        return info.nodes == (1 << info.height) - 1;
    }

    private static Info1 process1(TreeNode x) {
        if (x == null) return new Info1(0, 0);
        Info1 leftInfo = process1(x.left);
        Info1 rightInfo = process1(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new Info1(height, nodes);
    }

    private static class Info2 {
        public boolean isFull;
        public int height;

        public Info2(boolean isFull, int height) {
            this.isFull = isFull;
            this.height = height;
        }
    }

    public static boolean isFull2(TreeNode root) {
        return process2(root).isFull;
    }

    private static Info2 process2(TreeNode x) {
        if (x == null) return new Info2(true, 0);
        Info2 leftInfo = process2(x.left);
        Info2 rightInfo = process2(x.right);
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        boolean isFull = true;
        if (!leftInfo.isFull) {
            isFull = false;
        } else if (!rightInfo.isFull) {
            isFull = false;
        } else if (leftInfo.height != rightInfo.height) {
            isFull = false;
        }
        return new Info2(isFull, height);
    }

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = ForBT.generateRandomBST(maxLevel, maxValue);
            if (isFull1(head) != isFull2(head)) {
                System.out.println("出错了!");
            }
        }
        System.out.println("测试结束");
    }


}
