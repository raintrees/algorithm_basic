package class03;

import jdk.nashorn.internal.ir.WhileNode;
import structure.Node;

import java.util.ArrayList;
import java.util.List;

//把链表中的给定值都删除
public class Code02_DeleteGivenValue {

    public static Node removeNum(Node head, int num) {
        //确定新头
        while (head != null) {
            if (head.val != num) {
                break;
            }
            head = head.next;
        }
        if (head == null) return null;
        Node cur = head;
        Node pre = head;
        while (cur != null) {
            if (cur.val == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
    }

    public static Node test(Node head, int num) {
        List<Node> list = new ArrayList<>();
        while (head != null) {
            if (head.val != num) {
                list.add(head);
            }
            head = head.next;
        }
        if (list.size() == 0) return null;
        for (int i = 1; i < list.size(); i++) {
            list.get(i - 1).next = list.get(i);
        }
        return list.get(0);
    }

}
