package ua.nure.matchenko.practice1;

public class Part5 {
    public static void main(String[] args) {
        int number = Integer.parseInt(args[0]);
        int sum = 0;

        for (int i = 0, j = 1; i < Math.log10(number) + 1; i++, j*=10) {
            sum += number / j % 10;
        }

        System.out.println(sum);
    }
}
