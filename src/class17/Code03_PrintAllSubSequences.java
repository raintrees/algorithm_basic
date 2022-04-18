package class17;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//打印一个字符串的全部子序列
public class Code03_PrintAllSubSequences {

    public static List<String> subs(String s) {
        List<String> ans = new ArrayList<>();
        char[] str = s.toCharArray();
        process1(str, ans, "", 0);
        return ans;
    }

    /**
     * @param str
     * @param ans
     * @param path  当前的子序列
     * @param index 当前的位置
     */
    private static void process1(char[] str, List<String> ans, String path, int index) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        //要当前位置
        process1(str, ans, path + str[index], index + 1);
        //不要当前位置
        process1(str, ans, path, index + 1);
    }

    public static List<String> subsNoRepeat(String s) {
        List<String> ans = new ArrayList<>();
        char[] str = s.toCharArray();
        Set<String> set = new HashSet<>();
        process2(str, set, "", 0);
        for (String s1 : set) {
            ans.add(s1);
        }
        return ans;
    }

    /**
     * @param str
     * @param ans
     * @param path  当前的子序列
     * @param index 当前的位置
     */
    private static void process2(char[] str, Set<String> ans, String path, int index) {
        if (index == str.length) {
            ans.add(path);
            return;
        }
        //要当前位置
        process2(str, ans, path + str[index], index + 1);
        //不要当前位置
        process2(str, ans, path, index + 1);
    }


    public static void main(String[] args) {
        List<String> ans1 = subs("abcd");
        for (String an : ans1) {
            System.out.println(an);
        }
        System.out.println("===========");
        List<String> ans2 = subsNoRepeat("accc");
        for (String an : ans2) {
            System.out.println(an);
        }
    }


}
