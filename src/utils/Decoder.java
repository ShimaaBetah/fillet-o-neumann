package utils;

public class Decoder {

    private Decoder() {
        throw new IllegalStateException("Utility class");
    }

    public static String invertDigits(String binaryInt) {
        String result = binaryInt;
        result = result.replace("0", " ");
        result = result.replace("1", "0");
        result = result.replace(" ", "1");
        return result;
    }

    public static int binaryToIntinTwosCompliment(String binaryString) {
        if (binaryString.charAt(0) == '1') {
            String invertedInt = invertDigits(binaryString);
            int decimalValue = Integer.parseInt(invertedInt, 2);

            decimalValue = (decimalValue + 1) * -1;

            return decimalValue;
        }
        return Integer.parseInt(binaryString, 2);
    }

    public static int binaryToInt(String binaryString) {
        char sign = binaryString.charAt(0);
        if (sign == '1') {
            return (int) Long.parseLong(binaryString, 2);
        }

        return Integer.parseInt(binaryString, 2);
    }

    public static String intToBinary(int integerValue) {
        String binaryString = Integer.toBinaryString(integerValue);
        StringBuilder sb = new StringBuilder(Parser.INSTRUCTION_SIZE);
        for (int i = binaryString.length(); i < Parser.INSTRUCTION_SIZE; i++) {
            sb.append("0");
        }
        sb.append(binaryString);
        return sb.toString();
    }

    public static int getIntValueOfBinarySegment(int integerValue, int start, int end) {
        return getIntValueOfBinarySegment(integerValue, start, end, false);
    }

    public static int getIntValueOfBinarySegment(int integerValue, int start, int end, boolean isTwosCompliment) {
        String binaryString = intToBinary(integerValue);
        String binaryStringSegment = binaryString.substring(start, end + 1);
        return isTwosCompliment ? binaryToIntinTwosCompliment(binaryStringSegment) : binaryToInt(binaryStringSegment);
    }

}
