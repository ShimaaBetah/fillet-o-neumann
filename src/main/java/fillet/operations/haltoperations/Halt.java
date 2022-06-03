package fillet.operations.haltoperations;

import fillet.exceptions.AddressOutOfRangeException;
import fillet.exceptions.InvalidRegisterNumberException;
import fillet.signals.Signals;

public class Halt extends HaltOperation {

    public Halt(int opcode) {
        super(opcode);
    }


    @Override
    public void execute() throws Exception {
        Signals.getInstance().setHaltSignal(true);
    }

    @Override
    public void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException {
        // No memory access
    }

    @Override
    public void writeBack() throws Exception {
        // No write back
    }
}
