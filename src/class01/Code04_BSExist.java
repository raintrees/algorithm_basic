package class01;

import duishuqi.ForSort;

import java.util.Arrays;

/**
 * 在一个有序数组中，找某个数是否存在
 */
public class Code04_BSExist {

    //二分
    public static boolean bsExist(int[] arr, int target) {
        if (arr == null || arr.length == 0) return false;

        int l = 0;
        int r = arr.length - 1;
        //防止（l + r）/ 2 超过int最大值
        while (l <= r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] == target) {
                return true;
            } else if (arr[m] < target) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return false;
    }

    public static boolean test(int[] arr, int target) {
        if (arr == null || arr.length == 0) return false;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int maxValue = 50;
        int maxSize = 50;
        int testTime = 100000;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr = ForSort.generateRandomArray(maxValue, maxSize);
            Arrays.sort(arr);
            int value = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());

            if (test(arr, value) != bsExist(arr, value)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }


}
