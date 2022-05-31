package logger.services;

import memory.MainMemory;
import memory.Registers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogEntityService {
    public static String logRegisters(Registers registers, int[] registersToLog) {
        List<String> headers = Arrays.asList("Register", "Value");

        List<List<String>> rows = new ArrayList<>();
        for (int regIdx : registersToLog) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(regIdx));
            row.add(String.valueOf(registers.getRegister(regIdx)));
            rows.add(row);
        }

        return GenerateTableService.execute(headers, rows);
    }

    public static String logMainMemory(MainMemory memory, int startAddress, int endAddress) {
        List<String> headers = Arrays.asList("Address", "Value");

        List<List<String>> rows = new ArrayList<>();
        for (int i = startAddress; i <= endAddress; i++) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(i));
            row.add(String.valueOf(memory.loadData(i)));
            rows.add(row);
        }

        return GenerateTableService.execute(headers, rows);
    }
}
