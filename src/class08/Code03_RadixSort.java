package class08;

import duishuqi.ForSort;

public class Code03_RadixSort {

    public static void radixSort(int[] arr) {
        int i = 0, j = 0, radix = 10;
        radixSort(arr, 0, arr.length - 1, maxBits(arr));
    }

    private static void radixSort(int[] arr, int l, int r, int maxBits) {
        int i = 0, j = 0, radix = 10;
        for (int d = 1; d <= maxBits; d++) {
            int[] count = new int[radix];
            for (i = l; i <= r; i++) {
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < count.length; i++) {
                count[i] = count[i - 1] + count[i];
            }
            int[] help = new int[r - l + 1];
            for (i = r; i >= l; i--) {
                j = getDigit(arr[i], d);
                help[--count[j]] = arr[i];
            }
            for (i = l, j = 0; i <= r; i++, j++) {
                arr[i] = help[j];
            }
        }
    }

    private static int maxBits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        int bits = 0;
        while (max != 0) {
            bits++;
            max /= 10;
        }
        return bits;
    }

    private static int getDigit(int num, int d) {
        return (num / (int) (Math.pow(10, d - 1))) % 10;
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

            radixSort(ints);
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
