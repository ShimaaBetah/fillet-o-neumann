package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import utlis.*;

public class ParserTest {
    String path = Path.PATH;
    Parser parserR = new Parser(path + "fillet-o-neumann/src/programs/spicy-rprogram.txt");
    Parser parserI = new Parser(path + "fillet-o-neumann/src/programs/spicy-iprogram.txt");
    Parser parserJ = new Parser(path + "fillet-o-neumann/src/programs/spicy-jprogram.txt");

    @Test
    public void testSpicyR1() {
        assertEquals(
                "00000000100000000000000000000000",
                parserR.getBinaryInstructions().get(0));
    }

    @Test
    public void testSpicyR2() {
        assertEquals(
                "00000000100001000000000000000000",
                parserR.getBinaryInstructions().get(1));
    }

    @Test
    public void testSpicyR3() {
        assertEquals(
                "00000000000000000010000000000000",
                parserR.getBinaryInstructions().get(2));
    }

    @Test
    public void testSpicyR4() {
        assertEquals(
                "00000000000000000000000000000000",
                parserR.getBinaryInstructions().get(3));
    }

    @Test
    public void testSpicyR5() {
        assertEquals(
                "01010001100010001000000001100110",
                parserR.getBinaryInstructions().get(4));
    }

    @Test
    public void testSpicyI1() {
        assertEquals(
                "00110000100000000000000000000001",
                parserI.getBinaryInstructions().get(0));
    }

    @Test
    public void testSpicyI2() {
        assertEquals(
                "10100000100010000000000001111000",
                parserI.getBinaryInstructions().get(1));
    }

    @Test
    public void testSpicyJ1() {
        assertEquals(
                "011110010101010101010101010101000100",
                parserJ.getBinaryInstructions().get(0));
    }

}
