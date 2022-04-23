package instructions;
import java.util.HashMap;

import exceptions.InvalidRegisterNumberException;
import memory.Registers;
import operations.Add;
import operations.And;
import operations.Multiply;
import operations.Operation;
import operations.ShiftLeft;
import operations.ShiftRight;
import operations.Sub;
import utlis.Decoder;

public class RegisterInstruction extends Instruction {
    private int destinationRegister;
    private int firstRegister;
    private int secondRegister;
    private int shiftAmount;
    private int result;
    
    
    static HashMap<Integer, Operation> operations = new HashMap<Integer, Operation>();

    static {
        operations.put(0, new Add());
        operations.put(1, new Sub());
        operations.put(2, new Multiply());
        operations.put(5, new And());
        operations.put(8, new ShiftLeft());
        operations.put(9, new ShiftRight());

    }
    public RegisterInstruction(int bitMask) {
        super(bitMask);
    }

    @Override
    public void decode() {
        // TODO Auto-generated method stub
        setOpcode(Decoder.getBits(getBitMask(), 0, 3));
        destinationRegister = Decoder.getBits(getBitMask(), 4, 8);
        firstRegister = Decoder.getBits(getBitMask(), 9, 13);
        secondRegister = Decoder.getBits(getBitMask(), 14, 18);
        shiftAmount = Decoder.getBits(getBitMask(), 19, 31);
    }

    @Override
    public void execute() throws InvalidRegisterNumberException {
        // TODO Auto-generated method stub
        Registers registers = Registers.getInstance();
        int firstOperand = registers.getRegister(firstRegister);
        int secondOperand = registers.getRegister(secondRegister);
        result = operations.get(getOpcode()).execute(firstOperand, secondOperand, shiftAmount);

    }

    @Override
    public void memoryAccess() {
        // TODO Auto-generated method stub

    }

    @Override
    public void writeBack() throws InvalidRegisterNumberException {
        // TODO Auto-generated method stub
        Registers registers = Registers.getInstance();
        registers.setRegister(destinationRegister, result);

    }

    public int getDestinationRegister() {
        return destinationRegister;
    }
    
    public int getFirstRegister() {
        return firstRegister;
    }

    public int getSecondRegister() {
        return secondRegister;
    }


    public int getShiftAmount() {
        return shiftAmount;
    }

    public int getResult() {
        return result;
    }
    

}
