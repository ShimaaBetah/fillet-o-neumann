package utlis;

public class Decoder {
// from binary string to int
    public static int binaryToInt(String binary) {
        int result = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                result += Math.pow(2, binary.length() - i - 1);
            }
        }
        return result;
    }

    // from int to binary string
    public static String intToBinary(int number) {
        String result = "";
        while (number > 0) {
            result = (number % 2) + result;
            number /= 2;
        }
        while(result.length()<32){
            result = "0"+result;
        }
        return result;
    }

    //get specific bits from bitmask and convert to int
    public static int  getBits(int bitmask, int start, int end) {
        String bitmaskString = intToBinary(bitmask);
        String bits = bitmaskString.substring(start, end+1);
        return binaryToInt(bits);
    }
    public static void main(String[] args) {
        String s = "00001111111111111110000000000000";
        int n = binaryToInt(s);
        Decoder d = new Decoder();
        System.out.println(d.getBits(n, 4, 8));
    } 

}
