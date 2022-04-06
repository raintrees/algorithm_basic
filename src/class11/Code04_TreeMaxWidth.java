package class11;

import duishuqi.ForBT;
import structure.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Code04_TreeMaxWidth {

    public static int getTreeMaxWidth(TreeNode head) {
        if (head == null) return 0;
        int maxWidth = 0;
        int curLevelWidth = 0;
        TreeNode curLevelEnd = head;
        TreeNode nextLevelEnd = null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();

            if (head.left != null) {
                queue.add(head.left);
                nextLevelEnd = head.left;
            }
            if (head.right != null) {
                queue.add(head.right);
                nextLevelEnd = head.right;
            }
            curLevelWidth++;
            if (head == curLevelEnd) {
                maxWidth = Math.max(maxWidth, curLevelWidth);
                curLevelEnd = nextLevelEnd;
                curLevelWidth = 0;
            }

        }
        return maxWidth;
    }

    public static int maxWidthUseMap(TreeNode head) {
        if (head == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        // key 在 哪一层，value
        HashMap<TreeNode, Integer> levelMap = new HashMap<>();
        levelMap.put(head, 1);
        int curLevel = 1; // 当前你正在统计哪一层的宽度
        int curLevelNodes = 0; // 当前层curLevel层，宽度目前是多少
        int max = 0;
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int curNodeLevel = levelMap.get(cur);
            if (cur.left != null) {
                levelMap.put(cur.left, curNodeLevel + 1);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                levelMap.put(cur.right, curNodeLevel + 1);
                queue.add(cur.right);
            }
            if (curNodeLevel == curLevel) {
                curLevelNodes++;
            } else {
                max = Math.max(max, curLevelNodes);
                curLevel++;
                curLevelNodes = 1;
            }
        }
        max = Math.max(max, curLevelNodes);
        return max;
    }

    public static void main(String[] args) {
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = ForBT.generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != getTreeMaxWidth(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");

    }

}
