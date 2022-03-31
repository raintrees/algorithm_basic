package class04;

import duishuqi.ForSort;

//归并排序
public class Code01_MergeSort {

    public static void mergeSort1(int[] arr) {
        if (arr == null || arr.length < 2) return;
        process(arr, 0, arr.length - 1);
    }

    private static void process(int[] arr, int l, int r) {
        if (l == r) return;
        int m = l + ((r - l) >> 1);
        process(arr, l, m);
        process(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int index = 0;
        int p1 = l;
        int p2 = m + 1;

        while (p1 <= m && p2 <= r) {
            help[index++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= m) {
            help[index++] = arr[p1++];
        }

        while (p1 <= r) {
            help[index++] = arr[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    public static void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) return;
        int N = arr.length;
        int step = 1;
        while (step < N) {
            //每一个步长，第一组开始的下标都为0
            int l = 0;
            while (l < N) {
                int m = step + l - 1;
                if (m >= N) break;
                int r = Math.min(N - 1, m + step);
                merge(arr, l, m, r);
                l = m + 1;
            }
            if (step > (N >> 1)) break;
            step <<= 1;
        }
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int maxValue = 50;
        int testTime = 100000;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] ints = ForSort.generateRandomArray(maxSize, maxValue);
            int[] copy = ForSort.copyArray(ints);

            mergeSort2(ints);
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
