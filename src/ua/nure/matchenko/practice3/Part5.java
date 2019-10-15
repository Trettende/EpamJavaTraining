package ua.nure.matchenko.practice3;

public class Part5 {
    public static String decimal2Roman(int x) {
        int lastDigit = x % 10;
        String[] lastSymbolsArr = {"X", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String lastSymbol = lastSymbolsArr[lastDigit];
        if (x <= 10) {
            return lastSymbol;
        }

        int firstDigit = (x - lastDigit) / 10;
        String[] firstSymbolsArr = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        String firstSymbol = firstSymbolsArr[firstDigit];

        StringBuilder builder = new StringBuilder();
        builder.append(firstSymbol);
        if (lastDigit != 0) {
            builder.append(lastSymbol);
        }

        return builder.toString();
    }

    public static int roman2Decimal(String s) {
        char[] chars = s.toCharArray();
        int result = 0;
        for (int i = 0; i < chars.length; i++) {
            Romans symbol = Romans.valueOf(String.valueOf(chars[i]));

            int value = symbol.getValue();
            result += value;


            if (i + 1 < chars.length) {
                Romans nextSymbol = Romans.valueOf(String.valueOf(chars[i+1]));
                if (symbol.ordinal() < nextSymbol.ordinal()) {
                    result -= (value + symbol.getValue());
                }
            }
        }

        return result;
    }

    enum Romans {
        I(1), V(5), X(10), L(50), C(100);
        private int value;

        Romans(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public static void main(String[] args) {
        //main
    }
}
