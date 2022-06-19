package fillet.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fillet.exceptions.InvalidInstructionException;
import fillet.exceptions.InvalidRegisterException;
import org.jetbrains.annotations.NotNull;

public class Program {
    private final List<String> binaryInstructions;
    private Parser parser;

    public Program(@NotNull Parser parser) {
        binaryInstructions = parser.getBinaryInstructions();
    }

    public List<Integer> getIntegerInstructions() {
        return binaryInstructions.stream().map(Binary::binaryStringtoInt).collect(ArrayList::new, ArrayList::add,
                ArrayList::addAll);
    }

    public List<String> getBinaryInstructions() {
        return binaryInstructions;
    }
}
