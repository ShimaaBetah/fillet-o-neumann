package instructions;

import operations.Operation;
import operations.jumpoperations.JumpOperationFactory;
import utils.Decoder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class JumpInstruction extends Instruction {

    public JumpInstruction(int binaryInstruction) {
        super(binaryInstruction);
        setOperationFactory(new JumpOperationFactory());
    }

    @Override
    public void decode() {
        Operation operation = getOperationFactory().create(getBinaryInstruction());
        setOperation(operation);
    }

}
