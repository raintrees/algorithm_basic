package class10;

import structure.TreeNode;

public class Code02_RecursiveTraversalBT {

    public static void f(TreeNode node) {
        if (node == null) return;
        System.out.print("pre-" + node.val);
        f(node.left);
        System.out.print("in-" + node.val);
        f(node.right);
        System.out.print("pos-" + node.val);
    }


}
