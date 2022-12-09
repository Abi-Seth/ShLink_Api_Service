package com.shlink.shlink.utils;

public class GenerateUniqueLinkIdentifier {
    public static String generateUnquieIdentifier() {
        StringBuilder identifier = new StringBuilder(6);;
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

        for (int i = 0; i < 6; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            identifier.append(AlphaNumericString.charAt(index));
        }
        return identifier.toString();
    }
}
