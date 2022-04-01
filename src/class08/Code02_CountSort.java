package class08;

import duishuqi.ForSort;

//计数排序
public class Code02_CountSort {

    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) return;

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        int[] help = new int[max + 1];

        for (int i = 0; i < arr.length; i++) {
            help[arr[i]]++;
        }
        int index = 0;
        for (int i = 0; i < help.length; i++) {
            while (help[i]-- > 0) {
                arr[index++] = i;
            }
        }
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }


    public static void main(String[] args) {
        int maxSize = 50;
        int maxValue = 50;
        int testTime = 100000;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int[] ints = generateRandomArray(maxSize, maxValue);
            int[] copy = ForSort.copyArray(ints);

            countSort(ints);
            ForSort.comparator(copy);

            if (!ForSort.isEqual(ints,copy)) {
                succeed = false;
                ForSort.printArray(ints);
                ForSort.printArray(copy);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }

}
