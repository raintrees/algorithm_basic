package class04;


import duishuqi.ForSort;

/**
 * 求数组中的大两倍数对数量
 * 在一个数组中，
 * 对于每个数num，求有多少个后面的数 * 2 依然<num，求总个数
 * 比如：[3,1,7,0,2]
 * 3的后面有：1，0
 * 1的后面有：0
 * 7的后面有：0，2
 * 0的后面没有
 * 2的后面没有
 * 所以总共有5个
 */
public class Code04_BiggerThanRightTwice {

    public static int thanRightTwice(int[] arr) {
        if (arr == null || arr.length < 2) return 0;
        return process(arr, 0, arr.length - 1);
    }

    private static int process(int[] arr, int l, int r) {
        if (l == r) return 0;
        int m = l + ((r - l) >> 1);
        return process(arr, l, m) + process(arr, m + 1, r) + merge(arr, l, m, r);
    }

    private static int merge(int[] arr, int l, int m, int r) {
        int windowR = 0;
        int count = 0;
        //指针不回退
        for (int i = l; i <= m; i++) {
            while (windowR <= r && (arr[windowR] << 1) < arr[l]) {
                windowR++;
            }
            count += windowR - m - 1;
        }
        int p1 = l, p2 = m + 1, index = 0;
        int[] help = new int[r - l + 1];
        while (p1 <= m && p2 <= r) {
            help[index++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= m) {
            help[index++] = arr[p1++];
        }
        while (p2 <= r) {
            help[index++] = arr[p2++];
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
                count += arr[i] > (arr[j] << 1) ? 1 : 0;
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

            int ans2 = thanRightTwice(ints);
            if (ans1 != ans2) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

    }


}
