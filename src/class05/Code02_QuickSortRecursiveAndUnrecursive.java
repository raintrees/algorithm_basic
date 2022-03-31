package class05;

import duishuqi.ForSort;

import java.util.Stack;

/**
 * 荷兰国旗问题
 * <p>
 * 快速随机排序
 */
public class Code02_QuickSortRecursiveAndUnrecursive {

    public static void quickSort1(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process1(arr, 0, arr.length - 1);
    }

    private static void process1(int[] arr, int l, int r) {
        if (l >= r) return;
        swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
        int equal = partition1(arr, l, r);
        process1(arr, l, equal - 1);
        process1(arr, equal + 1, r);
    }

    // <=  >
    private static int partition1(int[] arr, int l, int r) {
        if (l > r) return -1;
        if (l == r) return l;
        int lessEqualR = l - 1;
        int index = l;
        while (index < r) {
            if (arr[index] <= arr[r]) {
                swap(arr, index, ++lessEqualR);
            }
            index++;
        }
        swap(arr, r, ++lessEqualR);
        return lessEqualR;
    }

    public static void quickSort2(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process2(arr, 0, arr.length - 1);
    }

    private static void process2(int[] arr, int l, int r) {
        if (l >= r) return;
        swap(arr, l + (int) (Math.random() * (r - l + 1)), r);
        int[] equals = partition2(arr, l, r);
        process2(arr, l, equals[0] - 1);
        process2(arr, equals[1] + 1, r);
    }

    private static int[] partition2(int[] arr, int l, int r) {
        if (l > r) return new int[]{-1, -1};
        if (l == r) return new int[]{l, r};
        int lessR = l - 1;
        int index = 0;
        int moreL = r;

        while (index < moreL) {
            if (arr[index] < arr[r]) {
                swap(arr, index++, ++lessR);
            } else if (arr[index] == arr[r]) {
                index++;
            } else {
                swap(arr, --moreL, index);
            }
        }
        swap(arr, r, moreL);
        return new int[]{lessR + 1, moreL};
    }

    private static class Info {
        private int l;
        private int r;

        public Info(int l, int r) {
            this.l = l;
            this.r = r;
        }
    }

    public static void quickSort3(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        swap(arr, (int) (Math.random() * N), N - 1);
        int[] ints = partition2(arr, 0, N - 1);
        Stack<Info> stack = new Stack<>();
        stack.push(new Info(0, ints[0] - 1));
        stack.push(new Info(ints[1] + 1, N - 1));
        while (!stack.isEmpty()) {
            Info info = stack.pop();
            swap(arr, info.l + (int) (Math.random() * (info.r - info.l + 1)), info.r);
            int[] equals = partition2(arr, info.l, info.r);
            stack.push(new Info(info.l, equals[0] - 1));
            stack.push(new Info(equals[1] + 1, info.r));
        }

    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int maxValue = 50;
        int testTime = 100000;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] ints = ForSort.generateRandomArray(maxSize, maxValue);
            int[] copy = ForSort.copyArray(ints);

            quickSort3(ints);
            ForSort.comparator(copy);

            if (!ForSort.isEqual(ints, copy)) {
                succeed = false;
                ForSort.printArray(ints);
                ForSort.printArray(copy);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }

}
