package org.example;
public class CipherProcessor {
    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ .,!?".toCharArray();

    public String encrypt(String text, int key) {
        return processText(text, key, true);
    }

    public String decrypt(String text, int key) {
        return processText(text, key, false);
    }

    private String processText(String text, int key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        for (char c : text.toUpperCase().toCharArray()) {
            result.append(processChar(c, key, encrypt));
        }
        return result.toString();
    }

    private char processChar(char c, int key, boolean encrypt) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (ALPHABET[i] == c) {
                int newPosition = encrypt ? (i + key) % ALPHABET.length : (i - key + ALPHABET.length) % ALPHABET.length;
                return ALPHABET[newPosition];
            }
        }
        return c;
    }
}
