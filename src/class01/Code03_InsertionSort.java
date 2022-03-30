package class01;

import duishuqi.ForSort;

/**
 * 插入排序
 */
public class Code03_InsertionSort {

    public static void insertionSort(int[] arr) {

        if (arr == null || arr.length < 2) return;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j + 1] > arr[j]; j--) {
                swap(arr, j, j + 1);
            }
        }

    }

    public static void swap(int[] arr, int i, int j) {
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
            int[] copyArray = ForSort.copyArray(ints);

            insertionSort(ints);
            ForSort.comparator(copyArray);

            if (!ForSort.isEqual(ints, copyArray)) {
                succeed = false;
                ForSort.printArray(ints);
                ForSort.printArray(copyArray);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
