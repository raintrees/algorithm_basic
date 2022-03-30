package class03;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//双向链表实现栈和队列
public class Code03_DoubleEndsQueueToStackAndQueue {

    private static class Node<T> {
        public T value;
        public Node<T> last;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }

    //双端队列
    private static class DoubleEndsQueue<T> {

        private Node<T> head;
        private Node<T> tail;

        public void addFromHead(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                head.last = node;
                node.next = head;
                head = node;
            }
        }

        public void addFromBottom(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.last = tail;
                tail = node;
            }
        }

        public T popFromHead() {
            if (head == null) {
                return null;
            }
            Node<T> node = head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = head.next;
                head.last = null;
                node.next = null;
            }
            return node.value;
        }

        public T popFromBottom() {
            if (head == null) {
                return null;
            }
            Node<T> node = tail;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                tail = tail.last;
                tail.next = null;
                node.last = null;
            }
            return node.value;
        }

        public boolean isEmpty() {
            return head == null;
        }
    }

    private static class MyStack<T> {
        private DoubleEndsQueue<T> queue;

        public MyStack() {
            queue = new DoubleEndsQueue<>();
        }

        public void push(T value) {
            queue.addFromHead(value);
        }

        public T pop() {
            if (queue.isEmpty()) throw new RuntimeException("stack is empty");
            return queue.popFromHead();
        }
    }

    private static class MyQueue<T> {
        private DoubleEndsQueue<T> queue;

        public MyQueue() {
            queue = new DoubleEndsQueue<>();
        }

        public void add(T value) {
            queue.addFromHead(value);
        }

        public T poll() {
            if (queue.isEmpty()) throw new RuntimeException("queue is empty");
            return queue.popFromBottom();
        }
    }

    public static boolean isEqual(Integer o1, Integer o2) {
        if (o1 == null && o2 != null) {
            return false;
        }
        if (o1 != null && o2 == null) {
            return false;
        }
        if (o1 == null && o2 == null) {
            return true;
        }
        return o1.equals(o2);
    }


    public static void main(String[] args) {
        int oneTestDataNum = 100;
        int value = 10000;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            MyStack<Integer> myStack = new MyStack<>();
            MyQueue<Integer> myQueue = new MyQueue<>();
            Stack<Integer> stack = new Stack<>();
            Queue<Integer> queue = new LinkedList<>();
            for (int j = 0; j < oneTestDataNum; j++) {
                int nums = (int) (Math.random() * value);
                if (stack.isEmpty()) {
                    myStack.push(nums);
                    stack.push(nums);
                } else {
                    if (Math.random() < 0.5) {
                        myStack.push(nums);
                        stack.push(nums);
                    } else {
                        if (!isEqual(myStack.pop(), stack.pop())) {
                            System.out.println("oops1!");
                        }
                    }
                }
                int numq = (int) (Math.random() * value);
                if (queue.isEmpty()) {
                    myQueue.add(numq);
                    queue.offer(numq);
                } else {
                    if (Math.random() < 0.5) {
                        myQueue.add(numq);
                        queue.offer(numq);
                    } else {
                        if (!isEqual(myQueue.poll(), queue.poll())) {
                            System.out.println("oops2!");
                        }
                    }
                }
            }
        }
        System.out.println("finish!");
    }

}
