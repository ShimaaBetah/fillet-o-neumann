package utils;

public class Binary {
    private Binary() {
        throw new IllegalStateException("Utility class");
    }

    public static String intToBinaryString(int sID) {
        /*
         * java's toBinaryString() method doesn't return the whole binary
         * so this a workaround to get the binary string
         */
        return Long.toBinaryString(sID & 0xffffffffL | 0x100000000L).substring(1);
    }

    public static int binaryStringtoInt(String binary) {
        /*
         * convert binary string to integer
         */
        return (int) Long.parseLong(binary, 2);
    }
}
