package class03;

import java.util.Stack;

public class Code07_MinStack {

    private static class MinStack {
        private Stack<Integer> dataStack;
        private Stack<Integer> minStack;

        public MinStack() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int value) {
            if (dataStack.isEmpty() || value < getMin()) {
                dataStack.push(value);
                minStack.push(value);
            } else {
                dataStack.push(value);
                minStack.push(getMin());
            }
        }

        public int pop() {
            int value = dataStack.pop();
            minStack.pop();
            return value;
        }

        public int getMin() {
            return minStack.peek();
        }

    }

    public static void main(String[] args) {
        MinStack stack1 = new MinStack();
        stack1.push(3);
        System.out.println(stack1.getMin());
        stack1.push(4);
        System.out.println(stack1.getMin());
        stack1.push(1);
        System.out.println(stack1.getMin());
        System.out.println(stack1.pop());
        System.out.println(stack1.getMin());

    }


}
