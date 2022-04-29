package instructions;

import operations.Operation;
import utils.Decoder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ImmediateInstruction extends Instruction {

    private static final int OPCODE_RANGE_START = 0;
    private static final int OPCODE_RANGE_END = 3;
    private static final int DESTINATION_REGISTER_RANGE_START = 4;
    private static final int DESTINATION_REGISTER_RANGE_END = 8;
    private static final int FIRST_OPERAND_RANGE_START = 9;
    private static final int FIRST_OPERAND_RANGE_END = 13;
    private static final int IMMEDIATE_VALUE_RANGE_START = 14;
    private static final int IMMEDIATE_VALUE_RANGE_END = 31;

    public ImmediateInstruction(int binaryInstruction) {
        super(binaryInstruction);
    }

    @Override
    public void decode() {
        int opcode = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), OPCODE_RANGE_START, OPCODE_RANGE_END);
        int destinationRegister = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), DESTINATION_REGISTER_RANGE_START, DESTINATION_REGISTER_RANGE_END);
        int sourceRegister = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), FIRST_OPERAND_RANGE_START, FIRST_OPERAND_RANGE_END);
        int immediateValue = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), IMMEDIATE_VALUE_RANGE_START, IMMEDIATE_VALUE_RANGE_END);
        setOperation(opcode, destinationRegister, sourceRegister, immediateValue);
    }

    private void setOperation(int opcode, int destinationRegister, int sourceRegister, int immediateValue) {
        Class<? extends Operation> operationClass = getOperationsMap().get(opcode);
        Constructor<? extends Operation> operationConstructor;
        try {
            operationConstructor = operationClass.getConstructor(int.class, int.class, int.class, int.class);
            Operation operation = operationConstructor.newInstance(opcode, destinationRegister, sourceRegister, immediateValue);
            setOperation(operation);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
