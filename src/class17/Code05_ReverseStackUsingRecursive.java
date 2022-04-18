package class17;

import java.util.Stack;

/**
 * 给你一个栈，请你逆序这个栈，
 * 不能申请额外的数据结构，
 * 只能使用递归函数。 如何实现?
 */
public class Code05_ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack) {
        if (stack.isEmpty()) return;
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    /**
     * 返回栈底元素
     * 将栈 1 2 3 变成 1 2，返回3
     *
     * @param stack
     * @return
     */
    public static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args) {
        Stack<Integer> test = new Stack<Integer>();
        test.push(1);
        test.push(2);
        test.push(3);
        test.push(4);
        test.push(5);
        reverse(test);
        while (!test.isEmpty()) {
            System.out.println(test.pop());
        }

    }

}
