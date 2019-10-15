package ua.nure.matchenko.practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Part4 {
    public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes());
        byte[] hash = digest.digest();
        StringBuilder builder = new StringBuilder();

        for (byte b : hash) {
            builder.append(String.format("%02X", b));
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        try {
            System.out.println(hash("asdf", "MD5"));
            System.out.println(hash("asdf", "SHA-256"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
