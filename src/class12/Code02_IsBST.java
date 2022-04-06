package class12;

import duishuqi.ForBT;
import structure.TreeNode;

import javax.sound.sampled.DataLine.Info;
import java.util.LinkedList;
import java.util.List;

//搜索二叉树
public class Code02_IsBST {

    private static class Info {
        public int min;
        public int max;
        public boolean isBST;

        public Info(int min, int max, boolean isBST) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }
    }

    public static boolean isBST1(TreeNode root) {
        if (root == null) return true;
        return process(root).isBST;
    }

    private static Info process(TreeNode x) {
        if (x == null) return null;
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int min = x.val;
        int max = x.val;
        if (leftInfo != null) {
            min = Math.min(leftInfo.min, min);
            max = Math.max(leftInfo.max, max);
        }
        if (rightInfo != null) {
            min = Math.min(rightInfo.min, min);
            max = Math.max(rightInfo.max, max);
        }
        boolean isCBT = false;
        boolean leftIsCBT = leftInfo == null ? true : leftInfo.isBST;
        boolean rightIsCBT = rightInfo == null ? true : rightInfo.isBST;
        if (leftIsCBT && rightIsCBT) {
            boolean leftMaxLessX = leftInfo == null ? true : leftInfo.max < x.val;
            boolean rightMinMoreX = rightInfo == null ? true : rightInfo.min > x.val;
            if (leftMaxLessX && rightMinMoreX) {
                isCBT = true;
            }
        }
        return new Info(min, max, isCBT);
    }

    public static boolean isBST2(TreeNode root) {
        if (root == null) return true;
        List<TreeNode> list = new LinkedList<>();
        in(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).val >= list.get(i).val) {
                return false;
            }
        }
        return true;
    }

    public static void in(TreeNode x, List<TreeNode> list) {
        if (x == null) return;
        in(x.left, list);
        list.add(x);
        in(x.right, list);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = ForBT.generateRandomBST(maxLevel, maxValue);
            if (isBST1(head) != isBST2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
