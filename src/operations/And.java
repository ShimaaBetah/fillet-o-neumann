package operations;

public class And implements operations.Operation {
    public int execute(int operand1, int operand2, int shiftAmount) {
        return operand1 & operand2;
    }
}
    

