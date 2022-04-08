package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.Test;

import utlis.*;

public class ParserTest {
    String path = Path.PATH;
    Parser parser = new Parser(path+"fillet-o-neumann/src/programs/spicy-program.txt");

    public String getBinary(int sID) {
        /**
         * java's toBinaryString() method doesn't return the whole binary
         * so this a workaround to get the binary string
         */
        return Long.toBinaryString(sID & 0xffffffffL | 0x100000000L).substring(1);
    }

    @Test
    public void testSpicy1() {
        assertEquals(
                "00000000100000000000000000000000",
                getBinary(parser.getBinaryInstructions().get(0)));
    }

    @Test
    public void testSpicy2() {
        assertEquals(
                "00000000100001000000000000000000",
                getBinary(parser.getBinaryInstructions().get(1)));
    }

    @Test
    public void testSpicy3() {
        assertEquals(
                "00000000000000000010000000000000",
                getBinary(parser.getBinaryInstructions().get(2)));
    }

    @Test
    public void testSpicy4() {
        assertEquals(
                "00000000000000000000000000000000",
                getBinary(parser.getBinaryInstructions().get(3)));
    }

    @Test
    public void testSpicy5() {
        assertEquals(
                "01010001100010001000000001100110",
                getBinary(parser.getBinaryInstructions().get(4)));
    }

}
