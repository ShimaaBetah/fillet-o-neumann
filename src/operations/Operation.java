package operations;

import exceptions.InvalidRegisterNumberException;

public interface Operation {
    void execute() throws InvalidRegisterNumberException;

    void memoryAccess();

    void writeBack() throws InvalidRegisterNumberException;
}
