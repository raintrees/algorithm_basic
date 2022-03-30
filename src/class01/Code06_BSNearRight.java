package class01;

import duishuqi.ForSort;

import java.util.Arrays;

/**
 * 在一个有序数组中，找小于等于某个数最右侧的位置 <=
 */
public class Code06_BSNearRight {

    public static int nearRight(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        int l = 0;
        int r = arr.length - 1;
        int index = -1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] <= target) {
                index = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return -index;
    }

    // for test
    public static int test(int[] arr, int value) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] <= value) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 10;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = ForSort.generateRandomArray(maxSize, maxValue);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
            if (test(arr, value) != nearRight(arr, value)) {
                ForSort.printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(nearRight(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

}
