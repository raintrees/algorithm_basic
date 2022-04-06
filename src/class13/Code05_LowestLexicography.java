package class13;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class Code05_LowestLexicography {

    private static class StringComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    public static String lowestString1(String[] strs) {
        if (strs == null || strs.length == 0) return null;
        Arrays.sort(strs, new StringComparator());
        String str = "";
        for (int i = 0; i < strs.length; i++) {
            str += strs[i];
        }
        return str;
    }

    public static String lowestString2(String[] strs) {
        if (strs == null || strs.length == 0) return null;
        TreeSet<String> treeSet = process(strs);
        return treeSet.first();
    }

    private static TreeSet<String> process(String[] strs) {
        TreeSet<String> treeSet = new TreeSet<>();
        if (strs.length == 0) {
            treeSet.add("");
        } else {
            for (int i = 0; i < strs.length; i++) {
                String[] arr = removeIndexStr(strs, i);
                TreeSet<String> strings = process(arr);
                for (String string : strings) {
                    treeSet.add(strs[i] + string);
                }
            }
        }
        return treeSet;
    }

    public static String[] removeIndexStr(String[] strs, int index) {
        String[] arr = new String[strs.length - 1];
        int j = 0;
        for (int i = 0; i < strs.length; i++) {
            if (i != index) {
                arr[j++] = strs[i];
            }
        }
        return arr;
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }


}
