package ua.nure.matchenko.practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.ResourceBundle;

public class Part5 {
    private static void printAppropriateValue() {
        String result;
        Locale locale;
        ResourceBundle bundle;
        String line;

        try(BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in)
        )) {
            while ((line = reader.readLine()) != null) {
                String[] input = line.split(" ");
                if ("stop".equals(input[0])) {
                    break;
                } else if (input.length == 1) {
                    result = "No such values";
                } else {
                    locale = new Locale(input[1]);
                    bundle = ResourceBundle.getBundle("resources", locale);
                    if (bundle != null) {
                        result = bundle.getString(input[0]);
                    } else {
                        result = "No such values";
                    }
                }
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        printAppropriateValue();
    }
}
