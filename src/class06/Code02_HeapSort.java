package class06;

import duishuqi.ForSort;

public class Code02_HeapSort {

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2) return;
//        for (int i = 0; i < arr.length; i++) {
//            heapInsert(arr, i);
//        }
        int heapSize = arr.length;
        for (int i = heapSize - 1; i >= 0; i++) {
            heapify(arr, i, heapSize);
        }

        swap(arr, 0, heapSize--);

        while (heapSize > 0) {
            heapify(arr, 0, heapSize);
            swap(arr, 0, heapSize--);
        }

    }

    private static void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) {
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private static void heapify(int[] arr, int index, int size) {
        int left = index * 2 + 1;
        while (left < size) {
            int largest = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[index] > arr[largest] ? index : largest;
            if (largest == index) return;
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
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

            heapSort(ints);
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
