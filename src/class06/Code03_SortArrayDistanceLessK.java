package class06;

import duishuqi.ForSort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k
 * k相对于数组长度来说是比较小的。请选择一个合适的排序策略，对这个数组进行排序。
 */
public class Code03_SortArrayDistanceLessK {

    public static void sortedArrDistanceLessK(int[] arr, int k) {
        if (k == 0) return;
        int index = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (; index <= Math.min(k - 1, arr.length - 1); index++) {
            queue.add(arr[index]);
        }
        int i = 0;
        for (; index < arr.length; i++, index++) {
            queue.add(arr[index]);
            arr[i] = queue.poll();
        }

        while (!queue.isEmpty()) {
            arr[i++] = queue.poll();
        }
    }

    // for test
    public static int[] randomArrayNoMoveMoreK(int maxSize, int maxValue, int K) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        // 先排个序
        Arrays.sort(arr);
        // 然后开始随意交换，但是保证每个数距离不超过K
        // swap[i] == true, 表示i位置已经参与过交换
        // swap[i] == false, 表示i位置没有参与过交换
        boolean[] isSwap = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            int j = Math.min(i + (int) (Math.random() * (K + 1)), arr.length - 1);
            if (!isSwap[i] && !isSwap[j]) {
                isSwap[i] = true;
                isSwap[j] = true;
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        int maxSize = 50;
        int maxValue = 50;
        int testTime = 100000;
        boolean succeed = true;

        for (int i = 0; i < testTime; i++) {
            int k = (int) (Math.random() * maxSize) + 1;
            int[] ints = randomArrayNoMoveMoreK(maxSize, maxValue,k);
            int[] copy = ForSort.copyArray(ints);
            sortedArrDistanceLessK(ints,k);
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
