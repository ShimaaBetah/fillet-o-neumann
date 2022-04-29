package instructions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import operations.Operation;
import utils.Decoder;

public class RegisterInstruction extends Instruction {

    public RegisterInstruction(int bitMask) {
        super(bitMask);
    }

    @Override
    public void decode() {
        int opcode = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), 0, 3);
        int destinationRegister = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), 4, 8);
        int firstRegister = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), 9, 13);
        int secondRegister = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), 14, 18);
        int shiftAmount = Decoder.getIntValueOfBinarySegment(getBinaryInstruction(), 19, 31);
        setOperation(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);

    }

    private void setOperation(int opcode, int destinationRegister, int firstRegister, int secondRegister, int shiftAmount) {
        Class<? extends Operation> operationClass = getOperationsMap().get(opcode);
        Constructor<? extends Operation> operationConstructor;
        try {
            operationConstructor = operationClass.getConstructor(int.class, int.class, int.class, int.class, int.class);
            Operation operation = operationConstructor.newInstance(opcode, destinationRegister, firstRegister, secondRegister, shiftAmount);
            setOperation(operation);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
