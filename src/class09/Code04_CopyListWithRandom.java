package class09;

import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * rand指针是单链表节点结构中新增的指针，rand可能指向链表中的任意一个节点，也可能指向null
 * 给定一个由Node节点类型组成的无环单链表的头节点head，请实现一个函数完成这个链表的复制
 * 返回复制的新链表的头节点，要求时间复杂度O(N)，额外空间复杂度O(1)
 */
public class Code04_CopyListWithRandom {

    private static class Node {
        int value;
        Node next;
        Node rand;

        Node(int val) {
            value = val;
        }
    }

    public static Node copyWithRandom(Node head) {
        if (head == null) return null;
        Node cur = head;
        Node copy = null;
        Node next = null;
        while (cur != null) {
            copy = new Node(cur.value);
            next = cur.next;
            cur.next = copy;
            copy.next = next;
            cur = next;
        }
        cur = head;
        while (cur != null) {
            copy = cur.next;
            copy.rand = cur.rand == null ? null : cur.rand.next;
            cur = cur.next.next;
        }
        Node ans = cur.next;
        cur = head;
        while (cur != null) {
            copy = cur.next;
            next = cur.next.next;
            cur.next = next;
            copy.next = next == null ? null : next.next;
            cur = next;
        }
        return ans;
    }

    public static Node test(Node head) {
        if (head == null) return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        while (cur != null) {
            map.put(cur, new Node(cur.value));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).rand = map.get(cur.rand);
            cur = cur.next;
        }
        return map.get(head);
    }


}
