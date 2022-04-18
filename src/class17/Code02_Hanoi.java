package class17;

//汉诺塔问题
public class Code02_Hanoi {

    public static void hanoi1(int N) {
        leftToRight(N);
    }

    private static void leftToRight(int N) {
        if (N == 1) {
            System.out.println("Move 1 to right");
        } else {
            leftToMid(N - 1);
            System.out.println("Move " + N + " to right");
            midToRight(N - 1);
        }
    }

    private static void midToRight(int N) {
        if (N == 1) {
            System.out.println("Move 1 to right");
        } else {
            midToLeft(N - 1);
            System.out.println("Move " + N + " to right");
            leftToRight(N - 1);
        }
    }

    private static void midToLeft(int N) {
        if (N == 1) {
            System.out.println("Move 1 to left");
        } else {
            midToRight(N - 1);
            System.out.println("Move " + N + " to left");
            rightToLeft(N - 1);
        }
    }

    private static void rightToLeft(int N) {
        if (N == 1) {
            System.out.println("Move 1 to left");
        } else {
            rightToMid(N - 1);
            System.out.println("Move " + N + " to left");
            midToLeft(N - 1);
        }
    }

    private static void rightToMid(int N) {
        if (N == 1) {
            System.out.println("Move 1 to mid");
        } else {
            rightToLeft(N - 1);
            System.out.println("Move " + N + " to mid");
            leftToMid(N - 1);
        }
    }

    private static void leftToMid(int N) {
        if (N == 1) {
            System.out.println("Move 1 to mid");
        } else {
            leftToRight(N - 1);
            System.out.println("Move " + N + " to mid");
            rightToMid(N - 1);
        }
    }

    public static void hanoi2(int N) {
        func(N, "left", "right", "mid");
    }

    private static void func(int N, String from, String to, String another) {
        if (N == 1) {
            System.out.println("Move 1 from " + from + " to " + to);
        } else {
            func(N - 1, from, another, to);
            System.out.println("Move " + N + " from " + from + " to " + to);
            func(N - 1, another, to, from);
        }
    }

    public static void main(String[] args) {
        hanoi1(3);
        hanoi2(3);
    }

}
