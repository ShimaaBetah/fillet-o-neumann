package operations.immediateoperations;

import exceptions.InvalidRegisterNumberException;
import memory.Registers;

public class MoveImmediate extends ImmediateOperation {


    public MoveImmediate(int opcode, int destinationRegister, int sourceRegister, int immediateValue) {
        super(opcode, destinationRegister, sourceRegister, immediateValue);
    }

    @Override
    public void execute() throws InvalidRegisterNumberException {
        // No execution execute
    }

    @Override
    public void memoryAccess() {
        // No memory access
    }

    @Override
    public void writeBack() throws InvalidRegisterNumberException {
        Registers registers = Registers.getInstance();
        registers.setRegister(getDestinationRegister(), getImmediateValue());
    }
}
