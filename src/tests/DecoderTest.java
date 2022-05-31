package tests;

import org.junit.Assert;
import org.junit.Test;

import utils.Decoder;

public class DecoderTest {

    @Test
    public void test1 () {
        String binaryInstruction = "11111111111111111111111111111111";
        String decoderOutpout = Decoder.intToBinary(-1);
        Assert.assertEquals(binaryInstruction, decoderOutpout);
    }

}
