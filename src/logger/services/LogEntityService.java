package logger.services;

import instructions.Instruction;
import memory.MainMemory;
import memory.RegisterFile;
import utils.Binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;

public class LogEntityService {
    public static String logRegisters(RegisterFile registerFile, int[] registersToLog) {
        List<String> headers = Arrays.asList("Register", "Value");

        List<List<String>> rows = new ArrayList<>();
        for (int regIdx : registersToLog) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(regIdx));
            try {
                row.add(String.valueOf(registerFile.getRegister(regIdx)));
            } catch (InvalidRegisterNumberException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            rows.add(row);
        }

        return GenerateTableService.execute(headers, rows);
    }

    public static String logMainMemoryWord(MainMemory memory, int startAddress, int endAddress) {
        List<String> headers = Arrays.asList("Address", "ValueAsInt", "ValueAsBinary");

        List<List<String>> rows = new ArrayList<>();
        for (int i = startAddress; i <= endAddress; i++) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(i));
            try {
                int value = memory.loadWord(i);
                row.add(String.valueOf(value));
                String binary = Binary.intToBinaryString(value);
                row.add(binary);
            } catch (AddressOutOfRangeException e) {
                e.printStackTrace();
            }
            rows.add(row);
        }

        return GenerateTableService.execute(headers, rows);
    }

    public static String logPipeline(int cycle, Instruction[] pipeline) {
        //TODO: refactor this method
        List<String> headers = Arrays.asList("Cycle", "Fetch", "Decode", "Execute", "Memory", "WriteBack");
        final int FETCH_POSITION = 0;
        final int DECODE_POSITION = 1;
        final int READ_REGISTERS_POSITION = 2;
        final int EXECUTE_POSITION1 = 3;
        final int EXECUTE_POSITION2 = 4;
        final int MEMORY_POSITION = 5;
        final int WRITE_BACK_POSITION = 6;
        final String nullInstruction = "-".repeat(13);
        List<List<String>> rows = new ArrayList<>();

        String[] row = new String[headers.size()];
        row[0] = String.valueOf(cycle);
        row[1] = pipeline[FETCH_POSITION] == null ? nullInstruction : "instruction " + pipeline[FETCH_POSITION].getPC();

        if (pipeline[DECODE_POSITION] == null && pipeline[READ_REGISTERS_POSITION] == null) {
            row[2] = nullInstruction;
        } else if (pipeline[DECODE_POSITION] != null) {
            row[2] = "instruction " + pipeline[DECODE_POSITION].getPC();
        } else if (pipeline[READ_REGISTERS_POSITION] != null) {
            row[2] = "instruction " + pipeline[READ_REGISTERS_POSITION].getPC();
        }

        if (pipeline[EXECUTE_POSITION1] == null && pipeline[EXECUTE_POSITION2] == null) {
            row[3] = nullInstruction;
        } else if (pipeline[EXECUTE_POSITION1] != null) {
            row[3] = "instruction " + pipeline[EXECUTE_POSITION1].getPC();
        } else if (pipeline[EXECUTE_POSITION2] != null) {
            row[3] = "instruction " + pipeline[EXECUTE_POSITION2].getPC();
        }

        row[4] = pipeline[MEMORY_POSITION] == null ? nullInstruction : "instruction " + pipeline[MEMORY_POSITION].getPC();
        row[5] = pipeline[WRITE_BACK_POSITION] == null ? nullInstruction : "instruction " + pipeline[WRITE_BACK_POSITION].getPC();

        rows.add(Arrays.asList(row));
        return GenerateTableService.execute(headers, rows);
    }
}
