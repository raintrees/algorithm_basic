package class04;

import duishuqi.ForSort;

/**
 * 求数组中的逆序对数量
 * <p>
 * 在一个数组中，
 * 任何一个前面的数a，和任何一个后面的数b，
 * 如果(a,b)是降序的，就称为逆序对
 * 返回数组中所有的逆序对
 */
public class Code03_ReversePairs {

    public static int reversePairs(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) return 0;
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int index = help.length - 1, p1 = m, p2 = r;
        int count = 0;
        while (p1 >= l && p2 >= m + 1) {
            count += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[index--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[index--] = arr[p1--];
        }
        while (p2 >= m + 1) {
            help[index--] = arr[p2--];
        }
        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return count;
    }

    public static int test(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                count += arr[i] > arr[j] ? 1 : 0;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int maxValue = 50;
        int testTime = 100000;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] ints = ForSort.generateRandomArray(maxSize, maxValue);
            int ans1 = test(ints);

            int ans2 = reversePairs(ints);
            if (ans1 != ans2) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }

}
