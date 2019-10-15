package ua.nure.matchenko.practice1;

public class Part6 {
    public static void main(String[] args) {
        int length = Integer.parseInt(args[0]);
        int[] arr = new int[length];
        int index = 0;

        for (int i = 2; i <= Integer.MAX_VALUE; i++) {
            if (index < length) {
                if (isSimple(i)) {
                    arr[index++] = i;
                }
            } else {
                break;
            }
        }

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    private static boolean isSimple(int number) {
        int cnt = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                cnt++;
                if (cnt > 2) {
                    return false;
                }
            }
        }
        return true;
    }
}
