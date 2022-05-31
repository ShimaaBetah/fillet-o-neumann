package operations;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;

public interface Operation {
    void readRegisters() throws InvalidRegisterNumberException;

    void execute() throws Exception;

    void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException;

    void writeBack() throws Exception;
}
