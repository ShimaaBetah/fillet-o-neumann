package operations;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;

public interface Operation {
    void execute() throws Exception;

    void memoryAccess();

    void writeBack() throws Exception;
}
