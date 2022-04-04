package class10;

import com.sun.org.apache.regexp.internal.RE;
import structure.Node;

public class Code01_FindFirstIntersectNode {

    public static Node getFirstIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        Node loop1 = getLoop(head1);
        Node loop2 = getLoop(head2);
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        } else if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        return null;
    }

    private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node node1 = null;
        Node node2 = null;
        if (loop1 == loop2) {
            node1 = head1;
            node2 = head2;
            int len = 0;
            while (node1.next != loop1) {
                len++;
                node1 = node1.next;
            }
            while (node2.next != loop1) {
                len--;
                node2 = node2.next;
            }
            if (node1 != node2) return null;
            node1 = len > 0 ? head1 : head1;
            node2 = node1 == head1 ? head2 : head1;
            len = Math.abs(len);
            while (len-- > 0) {
                node1 = node1.next;
            }
            while (node1 != node2) {
                node1 = node1.next;
                node2 = node2.next;
            }
            return node2;
        } else {
            node1 = loop1.next;
            while (node1 != loop1) {
                if (node1 == loop2) {
                    return loop1;
                }
                node1 = node1.next;
            }
            return null;
        }
    }

    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        Node node1 = head1;
        Node node2 = head2;
        int len = 0;
        while (node1.next != null) {
            len++;
            node1 = node1.next;
        }
        while (node2.next != null) {
            len--;
            node2 = node2.next;
        }
        if (node1 != node2) return null;
        node1 = len > 0 ? head1 : head1;
        node2 = node1 == head1 ? head2 : head1;
        len = Math.abs(len);
        while (len-- > 0) {
            node1 = node1.next;
        }

        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node2;
    }

    private static Node getLoop(Node node) {
        if (node == null) return null;
        Node fast = node;
        Node slow = node;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = node;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

}
