package class16;

import java.util.HashSet;
import java.util.Stack;

//深度优先遍历
public class Code02_DFS {

    public static void dfs(Node start) {
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.push(start);
        set.add(start);
        System.out.print(start);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            for (Node next : node.nexts) {
                if (!set.contains(next)) {
                    set.add(next);
                    stack.add(node);
                    stack.add(next);
                    System.out.print(next.value);
                    break;
                }
            }
        }
        System.out.println();
    }

}
