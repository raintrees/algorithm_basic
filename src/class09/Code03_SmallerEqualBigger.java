package class09;

import structure.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单链表的头节点head，给定一个整数n，将链表按n划分成左边<n、中间==n、右边>n
 */
public class Code03_SmallerEqualBigger {

    public static Node smallerEqualBigger(Node head, int pivot) {
        if (head == null) return null;
        Node sH = null, sT = null, eH = null, eT = null, bH = null, bT = null, next = null;
        while (head != null) {
            next = head.next;
            head.next = null;
            if (head.val < pivot) {
                if (sH == null) {
                    sH = head;
                    sT = head;
                } else {
                    sT.next = head;
                    sT = head;
                }
            } else if (head.val == pivot) {
                if (eH == null) {
                    eH = head;
                    eT = head;
                } else {
                    eT.next = head;
                    eT = head;
                }
            } else {
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            head = next;
        }
        if (sH != null) {
            sT.next = eH;
            eT = eH == null ? sT : eT;
        }
        if (eT != null) {
            eT.next = bH;
        }
        return eH != null ? eH : (sH != null ? sH : bH);
    }


    public static Node test(Node head, int pivot) {
        if (head == null) return null;
        Node cur = head;
        int len = 0;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        Node[] nodes = new Node[len];
        cur = head;
        for (int i = 0; i < len; i++) {
            nodes[i] = cur;
            cur = cur.next;
        }
        partition(nodes, pivot);

        for (int i = 1; i < nodes.length; i++) {
            nodes[i - 1].next = nodes[i];
        }
        nodes[len - 1].next = null;
        return nodes[0];
    }

    private static void partition(Node[] nodes, int pivot) {
        int lessR = -1;
        int index = 0;
        int moreL = nodes.length;
        while (index < moreL) {
            if (nodes[index].val < pivot) {
                swap(nodes, ++lessR, index++);
            } else if (nodes[index].val > pivot) {
                swap(nodes, --moreL, index);
            } else {
                index++;
            }
        }
    }

    public static void swap(Node[] nodes, int i, int j) {
        Node temp = nodes[i];
        nodes[i] = nodes[j];
        nodes[j] = temp;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Node head1 = new Node(7);
        head1.next = new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);
        printLinkedList(head1);
        head1 = test(head1, 4);
        //head1 = smallerEqualBigger(head1, 5);
        printLinkedList(head1);

    }
}
