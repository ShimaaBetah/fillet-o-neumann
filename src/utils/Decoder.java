package utils;

public class Decoder {
    public static int binaryToInt(String binaryString) {
        if (binaryString.charAt(0) == '1') {
            return (int) Long.parseLong(binaryString, 2);
        }
        return Integer.parseInt(binaryString, 2);
    }

    public static String intToBinary(int integerValue) {
        String binaryString = Integer.toBinaryString(integerValue);
        StringBuilder sb = new StringBuilder(32);
        for (int i = binaryString.length(); i < 32; i++) {
            sb.append("0");
        }
        sb.append(binaryString);
        return sb.toString();
    }

    public static int getIntValueOfBinarySegment(int integerValue, int start, int end) {
        String binaryString = intToBinary(integerValue);
        String binaryStringSegment = binaryString.substring(start, end + 1);
        return binaryToInt(binaryStringSegment);
    }
}
