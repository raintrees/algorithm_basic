package class05;

import com.sun.scenario.effect.Merge;

/**
 * https://leetcode.com/problems/count-of-range-sum/
 * <p>
 * 给你一个整数数组nums 以及两个整数lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含lower和upper）之内的 区间和的个数 。
 * <p>
 * 区间和S(i, j)表示在nums中，位置从i到j的元素之和，包含i和j(i ≤ j)。
 * <p>
 * 示例 1：
 * 输入：nums = [-2,5,-1], lower = -2, upper = 2
 * 输出：3
 * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
 */
public class Code01_CountOfRangeSum {

    public static int countRangeSum(int[] nums, int lower, int upper) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long[] preSum = new long[nums.length];
        preSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }
        return process(preSum, 0, preSum.length - 1, lower, upper);
    }

    private static int process(long[] preSum, int l, int r, int lower, int upper) {
        if (l == r) {
            return preSum[l] >= lower && preSum[r] <= upper ? 1 : 0;
        }
        int m = l + ((r - l) >> 1);
        return process(preSum, l, m, lower, upper) + process(preSum, m + 1, r, lower, upper) + merge(preSum, l, m, r, lower, upper);
    }

    private static int merge(long[] preSum, int l, int m, int r, int lower, int upper) {
        int windowL = l, windowR = l;
        int count = 0;
        for (int i = m + 1; i <= r; i++) {
            long min = preSum[i] - upper;
            long max = preSum[i] - lower;
            while (windowL <= m && preSum[windowL] < min) {
                windowL++;
            }
            while (windowR <= m && preSum[windowR] <= max) {
                windowR++;
            }
            count += windowR - windowL;
        }
        long[] help = new long[r - l + 1];
        int p1 = l;
        int p2 = m+1;
        int index = 0;

        while (p1 <= m && p2 <= r) {
            help[index++] = preSum[p1] <= preSum[p2] ? preSum[p1++] : preSum[p2++];
        }

        while (p1 <= m) {
            help[index++] = preSum[p1++];
        }

        while (p2 <= r) {
            help[index++] = preSum[p2++];
        }

        for (int i = 0; i < help.length; i++) {
            preSum[i+l] = help[i];
        }
        return count;
    }

}
