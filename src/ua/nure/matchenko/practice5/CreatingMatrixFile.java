package ua.nure.matchenko.practice5;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class CreatingMatrixFile {
    public static void createMatrixInFile(int length, int width) {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(
                        new File("5part4.txt")))) {
            SecureRandom random = new SecureRandom();
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    builder.append(random.nextInt(900) + 100)
                            .append(" ");
                }
                builder.deleteCharAt(builder.length() - 1)
                        .append("\n");
            }
            builder.deleteCharAt(builder.length() - 1);
            writer.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CreatingMatrixFile.createMatrixInFile(5, 20);
    }
}
