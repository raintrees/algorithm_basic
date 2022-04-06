package class13;

import duishuqi.ForBT;
import structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Code02_MaxSubBSTHead {

    private static class Info {
        public int min;
        public int max;
        public int maxSubBSTSize;
        public TreeNode maxSubBSTHead;

        public Info(int min, int max, int maxSubBSTSize, TreeNode maxSubBSTHead) {
            this.min = min;
            this.max = max;
            this.maxSubBSTSize = maxSubBSTSize;
            this.maxSubBSTHead = maxSubBSTHead;
        }
    }

    public static TreeNode getMaxSubBSTHead(TreeNode root) {
        if (root == null) return null;
        return process(root).maxSubBSTHead;
    }

    private static Info process(TreeNode x) {
        if (x == null) return null;
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int min = x.val, max = x.val, p1 = 0, p2 = 0, p3 = 0;
        TreeNode head1 = null, head2 = null, head3 = null;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            p1 = leftInfo.maxSubBSTSize;
            head1 = leftInfo.maxSubBSTHead;
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            p2 = rightInfo.maxSubBSTSize;
            head2 = rightInfo.maxSubBSTHead;
        }
        boolean leftIsBST = leftInfo == null ? true : leftInfo.maxSubBSTHead == x.left;
        boolean rightIsBST = rightInfo == null ? true : rightInfo.maxSubBSTHead == x.right;
        if (leftIsBST && rightIsBST) {
            boolean leftMaxLessX = leftInfo == null ? true : leftInfo.max < x.val;
            boolean rightMinMoreX = rightInfo == null ? true : rightInfo.min > x.val;
            if (leftMaxLessX && rightMinMoreX) {
                p3 = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
                head3 = x;
            }
        }
        int maxSubBSTSize = Math.max(p1, Math.max(p2, p3));
        TreeNode maxSubBSTHead = maxSubBSTSize == p1 ? head1 : (maxSubBSTSize == p2 ? head2 : head3);
        return new Info(min, max, maxSubBSTSize,maxSubBSTHead);
    }

    public static TreeNode test(TreeNode root) {
        if (root == null) return null;
        int size = getMaxSubSize(root);
        if (size != 0) return root;
        TreeNode leftHead = test(root.left);
        TreeNode rightHead = test(root.right);
        return getMaxSubSize(leftHead) >= getMaxSubSize(rightHead) ? leftHead : rightHead;
    }

    private static int getMaxSubSize(TreeNode x) {
        if (x == null) return 0;
        List<TreeNode> list = new ArrayList<>();
        in(x, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1).val >= list.get(i).val) return 0;
        }
        return list.size();
    }

    private static void in(TreeNode x, List<TreeNode> list) {
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
            if (getMaxSubBSTHead(head) != test(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
