package class03;

public class Code04_RingArray {

    private static class MyStack {
        private int[] help;
        private int size;
        private int limit;
        private int index;

        public MyStack(int limit) {
            this.limit = limit;
            help = new int[limit];
            size = 0;
            index = 0;
        }

        public void push(int value) {
            if (size == limit) throw new RuntimeException("stack is full");
            help[index++] = value;
            size++;
        }

        public int pop() {
            if (size == 0) throw new RuntimeException("stack is empty");
            size--;
            return help[--index];
        }
    }

    private static class MyQueue {
        private int[] help;
        private int size;
        private int limit;
        private int pushI;
        private int popI;

        public MyQueue(int limit) {
            this.limit = limit;
            help = new int[limit];
            size = 0;
            pushI = 0;
            popI = 0;
        }

        private int nextIndex(int index) {
            return index - 1 < limit ? index + 1 : 0;
        }

        public void add(int value) {
            if (size == limit) throw new RuntimeException("queue is full");
            size++;
            help[pushI] = value;
            pushI = nextIndex(pushI);
        }

        public int poll() {
            if (size == 0) throw new RuntimeException("queue is empty");
            size--;
            int ans = help[popI];
            popI = nextIndex(popI);
            return ans;
        }

        public static void main(String[] args) {
            MyStack myStack = new MyStack(5);
            myStack.push(1);
            myStack.push(2);
            myStack.push(3);
            myStack.push(4);
            myStack.push(5);
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());
            System.out.println(myStack.pop());

            MyQueue myQueue = new MyQueue(5);
            myQueue.add(1);
            myQueue.add(2);
            myQueue.add(3);
            myQueue.add(4);
            myQueue.add(5);
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
            System.out.println(myQueue.poll());
        }

    }

}
