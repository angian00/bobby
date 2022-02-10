package com.angian.bobby;

public class Util {
    public static String formatLabelNumber(int number) {
        return padWithZeroes(number, 6);
    }

    private static String padWithZeroes(int number, int strLen) {
        StringBuilder sb = new StringBuilder();
        String numStr = Integer.toString(number);

        for (int i=0; i < (strLen-numStr.length()); i++) {
            sb.append("0");
        }

        sb.append(numStr);

        return sb.toString();
    }
}
