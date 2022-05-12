package instructions;

import operations.Operation;
import utils.Decoder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JumpInstruction extends Instruction {
    private static final int OPCODE_RANGE_START = 0;
    private static final int OPCODE_RANGE_END = 3;
    private static final int ADDRESS_RANGE_START = 4;
    private static final int ADDRESS_RANGE_END = 31;

    public JumpInstruction(int binaryInstruction) {
        super(binaryInstruction);
    }

    @Override
    public void decode() {
        int opcode = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), OPCODE_RANGE_START, OPCODE_RANGE_END);
        int address = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), ADDRESS_RANGE_START, ADDRESS_RANGE_END);
        setOperation(opcode, address);
    }

    private void setOperation(int opcode, int address) {
        Class<? extends Operation> operationClass = getOperationsMap().get(opcode);
        Constructor<? extends Operation> operationConstructor;
        try {
            operationConstructor = operationClass.getConstructor(int.class, int.class);
            Operation operation = operationConstructor.newInstance(opcode, address);
            setOperation(operation);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
