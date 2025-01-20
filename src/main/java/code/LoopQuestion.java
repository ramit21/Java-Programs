package code;

public class LoopQuestion {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0, j = 0; i < 5 && j < 5; ++i, j = i + 1) {
            sum += i;
            System.out.println("i = " + i + ", j = " + j + ", sum = " + sum);
        }
        System.out.println(sum);
    }
}
