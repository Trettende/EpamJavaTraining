package ua.nure.matchenko.practice1;

public class Part7 {

    public static final int INT26 = 26;
    public static final int INT64 = 64;

    public static void main(String[] args) {
        test("A");
        test("B");
        test("Z");
        test("AA");
        test("AZ");
        test("BA");
        test("ZZ");
        test("AAA");
    }

    public static int str2int(String number) {
        int value = 0;

        for (int i = number.length() - 1, k = 1; i >= 0; i--, k *= INT26) {
            value += (number.charAt(i) - INT64) * k;
        }

        return value;
    }

    public static String int2str(int number) {
        StringBuilder builder = new StringBuilder();
        int digit;
        while (number > 0) {
            if (number % INT26 != 0) {
                digit = (number - 1) % INT26;
            } else {
                digit = INT26 - 1;
            }
            number = (number - 1) / INT26;
            builder.append((char) (digit + INT64 + 1));
        }

        return builder.reverse().toString();
    }

    public static String rightColumn(String number) {
        return int2str(str2int(number) + 1);
    }

    public static void test(String digits) {
        StringBuilder builder = new StringBuilder();
        builder.append(digits + " ==> ");
        int code = str2int(digits);
        builder.append(code + " ==> ");
        builder.append(int2str(code));
        System.out.println(builder.toString());
    }
}
