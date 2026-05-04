package com.akiro;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final String ALL = LOWER + UPPER + DIGITS + SPECIAL;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generate(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("Длина пароля должна быть >= 4");
        }

        StringBuilder sb = new StringBuilder(length);
        sb.append(LOWER.charAt(RANDOM.nextInt(LOWER.length())));
        sb.append(UPPER.charAt(RANDOM.nextInt(UPPER.length())));
        sb.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        sb.append(SPECIAL.charAt(RANDOM.nextInt(SPECIAL.length())));

        for (int i = 4; i < length; i++) {
            sb.append(ALL.charAt(RANDOM.nextInt(ALL.length())));
        }

        char[] pwd = sb.toString().toCharArray();
        for (int i = 0; i < pwd.length; i++) {
            int idx = RANDOM.nextInt(pwd.length);
            char temp = pwd[i];
            pwd[i] = pwd[idx];
            pwd[idx] = temp;
        }
        return new String(pwd);
    }

    public static void main(String[] args) {
        int length = 12; // Настройка длины
        if (args.length > 0) {
            try {
                length = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println("⚠️ Неверный аргумент. Используется длина " + length);
            }
        }
        System.out.println("🔐 Сгенерированный пароль: " + generate(length));
    }
}
