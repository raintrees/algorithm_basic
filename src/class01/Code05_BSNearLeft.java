package class01;

import duishuqi.ForSort;

import java.util.Arrays;

/**
 * 在一个有序数组中，找大于等于某个数最左侧的位置 >=
 */
public class Code05_BSNearLeft {

    //返回下标
    public static int nearLeft(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;
        int l = 0;
        int r = arr.length - 1;
        int index = -1;
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] >= target) {
                index = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -index;
    }

    public static int test(int[] arr, int target) {
        if (arr == null || arr.length == 0) return -1;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= target) {
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
            if (test(arr, value) != nearLeft(arr, value)) {
                ForSort.printArray(arr);
                System.out.println(value);
                System.out.println(test(arr, value));
                System.out.println(nearLeft(arr, value));
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


}
