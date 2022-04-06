package class13;

import duishuqi.ForBT;
import structure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

//最低公共祖先
public class Code03_LowestAncestor {

    private static class Info {
        public TreeNode ancestor;
        public boolean findA;
        public boolean findB;

        public Info(TreeNode ancestor, boolean findA, boolean findB) {
            this.ancestor = ancestor;
            this.findA = findA;
            this.findB = findB;
        }
    }

    public static TreeNode lowestAncestor1(TreeNode root, TreeNode a, TreeNode b) {
        return process(root, a, b).ancestor;
    }

    private static Info process(TreeNode x, TreeNode a, TreeNode b) {
        if (x == null) return new Info(null, false, false);
        Info leftInfo = process(x.left, a, b);
        Info rightInfo = process(x.right, a, b);
        boolean findA = leftInfo.findA || rightInfo.findA || x == a;
        boolean findB = leftInfo.findB || rightInfo.findB || x == b;
        TreeNode ancestor = null;
        if (leftInfo.ancestor != null) {
            ancestor = leftInfo.ancestor;
        } else if (rightInfo.ancestor != null) {
            ancestor = rightInfo.ancestor;
        } else {
            if (findA && findB) {
                ancestor = x;
            }
        }
        return new Info(ancestor, findA, findB);
    }

    public static TreeNode lowestAncestor2(TreeNode root, TreeNode a, TreeNode b) {
        if (root == null) return null;
        HashMap<TreeNode, TreeNode> parentMap = new HashMap();
        parentMap.put(root, null);
        fillParentMap(root, parentMap);
        //a和a的父节点
        HashSet<TreeNode> aSet = new HashSet<>();
        aSet.add(a);
        TreeNode cur = parentMap.get(a);
        while (cur != null) {
            aSet.add(cur);
            cur = parentMap.get(cur);
        }
        cur = b;
        while (!aSet.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    private static void fillParentMap(TreeNode x, HashMap parentMap) {
        if (x.left != null) {
            parentMap.put(x.left, x);
            fillParentMap(x.left, parentMap);
        }
        if (x.right != null) {
            parentMap.put(x.right, x);
            fillParentMap(x.right, parentMap);
        }
    }

    // for test
    public static TreeNode pickRandomOne(TreeNode head) {
        if (head == null) {
            return null;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }


    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            TreeNode head = ForBT.generateRandomBST(maxLevel, maxValue);
            TreeNode o1 = pickRandomOne(head);
            TreeNode o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor1(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
