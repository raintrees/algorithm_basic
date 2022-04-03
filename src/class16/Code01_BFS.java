package class16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

//宽度优先遍历
public class Code01_BFS {

    public static void bfs(Node start) {
        Queue<Node> queue = new LinkedList<>();
        HashSet<Node> set = new HashSet<>();
        queue.add(start);
        set.add(start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            System.out.print(node.value);
            for (Node next : node.nexts) {
                if (!set.contains(next)) {
                    queue.add(next);
                    set.add(next);
                }
            }
        }
        System.out.println();
    }

}
