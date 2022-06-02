package fillet.operations;

import fillet.exceptions.AddressOutOfRangeException;
import fillet.exceptions.InvalidRegisterNumberException;

public interface Operation {
    void readRegisters() throws InvalidRegisterNumberException;

    void execute() throws Exception;

    void memoryAccess() throws InvalidRegisterNumberException, AddressOutOfRangeException;

    void writeBack() throws Exception;
}
