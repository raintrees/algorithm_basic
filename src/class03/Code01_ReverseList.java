package class03;

import structure.DoubleNode;
import duishuqi.ForNode;
import structure.Node;

import java.util.ArrayList;
import java.util.List;

//反转链表
public class Code01_ReverseList {

    public static Node reverseLinkedList(Node head) {
        if (head == null) return null;
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static DoubleNode reverseDoubleList(DoubleNode head) {
        if (head == null) return null;
        DoubleNode pre = null;
        DoubleNode next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            head.last = next;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static Node testReverseLinkedList(Node head) {
        if (head == null) return null;
        List<Node> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        int size = list.size();
        for (int i = 1; i < size; i++) {
            list.get(i).next = list.get(i - 1);
        }
        return list.get(size - 1);
    }

    public static DoubleNode testReverseDoubleList(DoubleNode head) {
        if (head == null) return null;
        List<DoubleNode> list = new ArrayList<>();
        while (head != null) {
            list.add(head);
            head = head.next;
        }
        list.get(0).next = null;
        DoubleNode pre = list.get(0);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            DoubleNode cur = list.get(i);
            cur.last = null;
            cur.next = pre;
            pre.last = cur;
            pre = cur;
        }
        return list.get(size - 1);
    }

    // for test
    public static void main(String[] args) {
        int len = 50;
        int value = 100;
        int testTime = 100000;
        System.out.println("test begin!");
        for (int i = 0; i < testTime; i++) {
            Node node1 = ForNode.generateRandomLinkedList(len, value);
            List<Integer> list1 = ForNode.getLinkedListOriginOrder(node1);
            node1 = reverseLinkedList(node1);
            if (!ForNode.checkLinkedListReverse(list1, node1)) {
                System.out.println("Oops1!");
            }

            Node node2 = ForNode.generateRandomLinkedList(len, value);
            List<Integer> list2 = ForNode.getLinkedListOriginOrder(node2);
            node2 = testReverseLinkedList(node2);
            if (!ForNode.checkLinkedListReverse(list2, node2)) {
                System.out.println("Oops2!");
            }

            DoubleNode node3 = ForNode.generateRandomDoubleList(len, value);
            List<Integer> list3 = ForNode.getDoubleListOriginOrder(node3);
            node3 = reverseDoubleList(node3);
            if (!ForNode.checkDoubleListReverse(list3, node3)) {
                System.out.println("Oops3!");
            }

            DoubleNode node4 = ForNode.generateRandomDoubleList(len, value);
            List<Integer> list4 = ForNode.getDoubleListOriginOrder(node4);
            node4 = reverseDoubleList(node4);
            if (!ForNode.checkDoubleListReverse(list4, node4)) {
                System.out.println("Oops4!");
            }

        }
        System.out.println("test finish!");

    }


}
