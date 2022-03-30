package class03;

import java.util.Stack;

public class Code05_TwoStackImplementQueue {

    private static class MyQueue<T> {
        private Stack<T> pushStack;
        private Stack<T> popStack;

        public MyQueue() {
            pushStack = new Stack<>();
            popStack = new Stack<>();
        }

        //注意两点
        //倾倒的时候： 1、popstack必须为空 2、一次性将pushstack中的数据倾倒玩
        private void pushToPop() {
            if (popStack.isEmpty()) {
                while (!pushStack.isEmpty()) {
                    popStack.push(pushStack.pop());
                }
            }
        }

        public void add(T value) {
            pushStack.push(value);
            pushToPop();
        }

        public T poll() {
            pushToPop();
            return popStack.pop();
        }

        public T peek() {
            pushToPop();
            return popStack.peek();
        }
    }

    public static void main(String[] args) {
        MyQueue<Integer> test = new MyQueue<>();
        test.add(1);
        test.add(2);
        test.add(3);
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
        System.out.println(test.peek());
        System.out.println(test.poll());
    }

}
