package ua.nure.matchenko.practice1;

public class Part4 {
    public static void main(String[] args) {
        int i1 = Integer.parseInt(args[0]);
        int i2 = Integer.parseInt(args[1]);

        int min = Integer.min(i1, i2);
        int max = Integer.max(i1, i2);
        int gcf = 1;

        for (int i = min; i > 1; i--) {
            if (max % i == 0 && min % i == 0) {
                gcf = i;
                break;
            }
        }

        System.out.println(gcf);
    }
}
