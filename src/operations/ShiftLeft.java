package operations;

public class ShiftLeft implements Operation {
    public int execute(int operand1, int operand2, int shiftAmount) {
        return operand1 << shiftAmount;
    }
}
    

