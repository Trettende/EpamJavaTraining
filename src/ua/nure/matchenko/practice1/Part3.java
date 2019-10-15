package ua.nure.matchenko.practice1;

public class Part3 {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        for (String s: args) {
            builder.append(s + " ");
        }
        builder.deleteCharAt(builder.length() - 1);

        System.out.println(builder.toString());
    }
}
