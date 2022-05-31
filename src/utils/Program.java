package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import exceptions.InvalidInstructionException;
import exceptions.InvalidRegisterException;

public class Program {
    private List<String> binaryInstructions;
    private String path;

    public Program(String path) {
        this.path = path;
        Parser parser = Optional.ofNullable(path).map(p -> {
            try {
                return new Parser(p);
            } catch (InvalidInstructionException | InvalidRegisterException e) {
                e.printStackTrace();
            }
            return null;
        }).orElse(null);
        binaryInstructions = parser.getBinaryInstructions();
    }

    public String getPath() {
        return path;
    }

    public List<Integer> getIntegerInstructions() {
        return binaryInstructions.stream().map(s -> Binary.binaryStringtoInt(s)).collect(ArrayList::new, ArrayList::add,
                ArrayList::addAll);
    }

    public List<String> getBinaryInstructions() {
        return binaryInstructions;
    }
}
