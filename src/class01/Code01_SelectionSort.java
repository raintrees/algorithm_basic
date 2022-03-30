package class01;

import duishuqi.ForSort;

/**
 * 选择排序
 */
public class Code01_SelectionSort {

    /**
     * 0 ~ N-1 找最小值
     * 1 ~ N-1 min
     * 2 ~ N-1 min
     * ...
     * N-1 ~ N-1 min
     * @param arr
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length == 0) return;
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[i] > arr[j] ? j : minIndex;
            }
            swap(arr, minIndex, i);
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

            selectionSort(ints);
            ForSort.comparator(copyArray);

            if (!ForSort.isEqual(ints,copyArray)) {
                succeed = false;
                ForSort.printArray(ints);
                ForSort.printArray(copyArray);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }
}
