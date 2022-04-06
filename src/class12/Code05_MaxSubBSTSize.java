package class12;

import duishuqi.ForBT;
import structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Code05_MaxSubBSTSize {

    private static class Info {
        public int min;
        public int max;
        public int maxSubBSTSize;
        public int allSize;

        public Info(int min, int max, int maxSubBSTSize, int allSize) {
            this.min = min;
            this.max = max;
            this.maxSubBSTSize = maxSubBSTSize;
            this.allSize = allSize;
        }
    }

    public static int maxSubBSTSize(TreeNode root) {
        if (root == null) return 0;
        return process(root).maxSubBSTSize;
    }

    private static Info process(TreeNode x) {
        if (x == null) return null;
        Info leftInfo = process(x.left);
        Info rightInfo = process(x.right);
        int min = x.val, max = x.val, allSize = 1, p1 = 0, p2 = 0, p3 = 0;
        if (leftInfo != null) {
            min = Math.min(min, leftInfo.min);
            max = Math.max(max, leftInfo.max);
            allSize += leftInfo.maxSubBSTSize;
            p1 = leftInfo.maxSubBSTSize;
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.min);
            max = Math.max(max, rightInfo.max);
            allSize += rightInfo.maxSubBSTSize;
            p2 = rightInfo.maxSubBSTSize;
        }
        boolean leftIsBST = leftInfo == null ? true : leftInfo.allSize == leftInfo.maxSubBSTSize;
        boolean rightIsBST = rightInfo == null ? true : rightInfo.allSize == rightInfo.maxSubBSTSize;
        if (leftIsBST && rightIsBST) {
            boolean leftMaxLessX = leftInfo == null ? true : leftInfo.max < x.val;
            boolean rightMinMoreX = rightInfo == null ? true : rightInfo.min > x.val;
            if (leftMaxLessX && rightMinMoreX) {
                p3 = (leftInfo == null ? 0 : leftInfo.maxSubBSTSize) + (rightInfo == null ? 0 : rightInfo.maxSubBSTSize) + 1;
            }
        }
        return new Info(min, max, Math.max(p1, Math.max(p2, p3)), allSize);
    }

    public static int test(TreeNode root) {
        if (root == null) return 0;
        int size = getMaxSubSize(root);
        if (size != 0) return size;
        return Math.max(test(root.left), test(root.right));
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
            if (maxSubBSTSize(head) != test(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
