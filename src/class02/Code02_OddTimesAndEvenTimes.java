package class02;

/**
 * 奇数，偶数
 * <p>
 * a ^ 0 = a
 * a ^ a = 0
 * <p>
 * a & (-a) : 得到a最右侧的1
 */
public class Code02_OddTimesAndEvenTimes {

    //region 一个数组中只有一个数出现了奇数次
    public static void printOddTimesNum1(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }
    //endregion


    //region 一个数组中出现奇数次的两个数
    public static void printOddTimesNum2(int[] arr) {

        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        //eor此时为 奇数a，奇数b 异或的结果  eor = a ^ b
        int rightOne = eor & (-eor);
        int another = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i] & rightOne) != 0) {
                another ^= arr[i];
            }
        }
        System.out.println("其一：" + another + ";其二：" + (eor ^ another));
    }
    //endregion

    public static void main(String[] args) {
        //2
        int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
        printOddTimesNum1(arr1);

        //3 2
        int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
        printOddTimesNum2(arr2);
    }

}
