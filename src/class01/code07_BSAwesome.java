package class01;

/**
 * 局部最小值问题
 */
public class code07_BSAwesome {

    public static int bsAwesome(int[] arr) {
        if (arr == null || arr.length == 0) return -1;

        if (arr.length == 1 || arr[0] < arr[1]) {
            return 0;
        }
        int N = arr.length;
        if (arr[N - 1] < arr[N - 2]) {
            return N - 1;
        }

        int l = 1;
        int r = N - 2;

        while (l < r) {
            int m = l + ((r - l) >> 1);
            if (arr[m] > arr[m - 1]) {
                r = m - 1;
            } else if (arr[m] > arr[m + 1]) {
                l = m + 1;
            } else {
                return m;
            }
        }
        //此时只有一个数了
        return l;
    }

}
