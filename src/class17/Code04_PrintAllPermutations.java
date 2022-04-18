package class17;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印一个字符串的全部排列
 * <p>
 * 打印一个字符串的全部排列，要求不要出现重复的排列
 */
public class Code04_PrintAllPermutations {

    public static List<String> permutation1(String s) {
        List<String> ans = new ArrayList<>();
        char[] chars = s.toCharArray();
        List<Character> str = new ArrayList<>();
        for (char aChar : chars) {
            str.add(aChar);
        }
        process1(str, "", ans);
        return ans;
    }

    private static void process1(List<Character> str, String path, List<String> ans) {
        if (str.isEmpty()) {
            ans.add(path);
            return;
        } else {
            int size = str.size();
            for (int i = 0; i < size; i++) {
                char cur = str.get(i);
                str.remove(i);
                process1(str, path + cur, ans);
                //清除现场
                str.add(i, cur);
            }
        }
    }

    public static List<String> permutation2(String s) {
        char[] str = s.toCharArray();
        List<String> ans = new ArrayList<>();
        process2(str, 0, ans);
        return ans;
    }

    private static void process2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        } else {
            for (int i = index; i < str.length; i++) {
                swap(str, i, index);
                process2(str, index + 1, ans);
                swap(str, i, index);
            }
        }
    }

    public static List<String> permutation3(String s) {
        char[] str = s.toCharArray();
        List<String> ans = new ArrayList<>();
        process3(str, 0, ans);
        return ans;
    }

    private static void process3(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
            return;
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    swap(str, i, index);
                    process3(str, index + 1, ans);
                    swap(str, i, index);
                }
            }
        }
    }

    private static void swap(char[] str, int i, int j) {
        char temp = str[i];
        str[i] = str[j];
        str[j] = temp;
    }

    public static void main(String[] args) {
        String s = "abcc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("===============");
        List<String> ans2 = permutation2(s);
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("===============");
        List<String> ans3 = permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }
    }


}
