package instructions;

import operations.Operation;
import operations.immediateoperations.ImmediateOperationFactory;
import utils.Decoder;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ImmediateInstruction extends Instruction {

    public ImmediateInstruction(int binaryInstruction) {
        super(binaryInstruction);
        setOperationFactory(new ImmediateOperationFactory());
    }

    @Override
    public void decode() {
        Operation operation = getOperationFactory().create(getBinaryInstruction());
        setOperation(operation);
    }
}
