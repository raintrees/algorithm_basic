package class14;

public class Code01_Light {

    public static int minLight1(String str) {
        char[] chars = str.toCharArray();
        int minLight = 0;
        int i = 0;
        while (i < chars.length) {
            if (chars[i] == 'X') {
                i = i + 1;
            } else if (chars[i] == '.') {
                minLight++;
                if (i + 1 > chars.length) break;
                else {
                    if (chars[i + 1] == 'X') {
                        i = i + 2;
                    } else if (chars[i + 1] == '.') {
                        i = i + 3;
                    }
                }
            }
        }
        return minLight;
    }


}
