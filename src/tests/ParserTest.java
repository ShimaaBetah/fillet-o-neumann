package tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Test;
import org.junit.function.ThrowingRunnable;

import exceptions.InvalidInstructionException;
import exceptions.InvalidRegisterException;
import utlis.Parser;
import utlis.Path;

public class ParserTest {
    String path = Path.PATH + "fillet-o-neumann/src/programs/";

    Parser parserR = Optional.ofNullable(path + "spicy-rprogram.txt").map(p -> {
        try {
            return new Parser(p);
        } catch (InvalidInstructionException | InvalidRegisterException e) {
            e.printStackTrace();
        }
        return null;
    }).orElse(null);

    Parser parserI = Optional.ofNullable(path + "spicy-iprogram.txt").map(p -> {
        try {
            return new Parser(p);
        } catch (InvalidInstructionException | InvalidRegisterException e) {
            e.printStackTrace();
        }
        return null;
    }).orElse(null);

    Parser parserJ = Optional.ofNullable(path + "spicy-jprogram.txt").map(p -> {
        try {
            return new Parser(p);
        } catch (InvalidInstructionException | InvalidRegisterException e) {
            e.printStackTrace();
        }
        return null;
    }).orElse(null);

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
                "01110000000000000000000000000010",
                parserJ.getBinaryInstructions().get(0));
    }

    @Test
    public void testLabel() {
        assertEquals(
                "01110000000000000000000000000001",
                parserJ.getBinaryInstructions().get(1));
    }

    @Test
    public void testSpicyJ3() {
        assertEquals(
                "01110000000000000000000000010100",
                parserJ.getBinaryInstructions().get(2));
    }

    @Test
    public void testNegativeJump() {
        assertThrows(InvalidInstructionException.class, new ThrowingRunnable() {
            @Override
            public void run() throws Throwable {
                new Parser(path + "negative-jump.txt");
            }
        });
        {
        }
    }

    @Test
    public void testEmptyFile() {
        Parser emptyFile = Optional.ofNullable(path + "empty-file.txt").map(p -> {
            try {
                return new Parser(p);
            } catch (InvalidInstructionException | InvalidRegisterException e) {
                e.printStackTrace();
            }
            return null;
        }).orElse(null);

        assertEquals(0, emptyFile.getBinaryInstructions().size());
    }
}
