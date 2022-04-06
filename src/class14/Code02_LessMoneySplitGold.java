package class14;

import java.util.PriorityQueue;

public class Code02_LessMoneySplitGold {

    /**
     * 一块金条切成两半，是需要花费和长度数值一样的铜板
     * 比如长度为20的金条，不管怎么切都要花费20个铜板，一群人想整分整块金条，怎么分最省铜板?
     * 例如，给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
     * 如果先把长度60的金条分成10和50，花费60；再把长度50的金条分成20和30，花费50；一共花费110铜板
     * 但如果先把长度60的金条分成30和30，花费60；再把长度30金条分成10和20，花费30；一共花费90铜板
     * 输入一个数组，返回分割的最小代价
     */
    //哈夫曼编码
    public static int lessMoney1(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }
        int lessMoney = 0;
        while (heap.size() > 1) {
            int cur = heap.poll() + heap.poll();
            lessMoney += cur;
            heap.add(cur);
        }
        return lessMoney;
    }


    public static int lessMoney2(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        return process(arr, 0);
    }

    /**
     * @param arr 还剩下要切割的
     * @param pre 目前的花费
     * @return
     */
    private static int process(int[] arr, int pre) {
        if (arr.length == 1) return pre;
        int money = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int[] copy = copyAndMergeTwo(arr, i, j);
                money = Math.min(process(copy, pre + arr[i] + arr[j]), money);
            }
        }
        return money;
    }

    private static int[] copyAndMergeTwo(int[] arr, int i, int j) {
        int[] copy = new int[arr.length - 1];
        int index = 0;
        for (int k = 0; k < arr.length; k++) {
            if (k != i && k != j) {
                copy[index++] = arr[k];
            }
        }
        copy[index] = arr[i] + arr[j];
        return copy;
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 100000;
        int maxSize = 6;
        int maxValue = 1000;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            if (lessMoney1(arr) != lessMoney2(arr)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
