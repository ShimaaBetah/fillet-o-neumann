package operations;
/**
 * operation require the value of pc to execute the operation
 */
public interface OperationWithPc {
    void execute(int pc) throws Exception;

}
