package logger.services;

import memory.MainMemory;
import memory.Registers;
import utils.Binary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.AddressOutOfRangeException;
import exceptions.InvalidRegisterNumberException;

public class LogEntityService {
    public static String logRegisters(Registers registers, int[] registersToLog) {
        List<String> headers = Arrays.asList("Register", "Value");

        List<List<String>> rows = new ArrayList<>();
        for (int regIdx : registersToLog) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(regIdx));
            try {
                row.add(String.valueOf(registers.getRegister(regIdx)));
            } catch (InvalidRegisterNumberException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            rows.add(row);
        }

        return GenerateTableService.execute(headers, rows);
    }

    public static String logMainMemoryData(MainMemory memory, int startAddress, int endAddress) {
        List<String> headers = Arrays.asList("Address", "Value");

        List<List<String>> rows = new ArrayList<>();
        for (int i = startAddress; i <= endAddress; i++) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(i));
            try {
                row.add(String.valueOf(memory.loadData(i)));
            } catch (AddressOutOfRangeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            rows.add(row);
        }
        return GenerateTableService.execute(headers, rows);

    }

    public static String logMainMemoryInstructions(MainMemory memory, int startAddress, int endAddress) {
        List<String> headers = Arrays.asList("Address", "ValueAsInt", "ValueAsBinary");

        List<List<String>> rows = new ArrayList<>();
        for (int i = startAddress; i <= endAddress; i++) {
            List<String> row = new ArrayList<>();
            row.add(String.valueOf(i));
            try {
                int value = memory.loadInstruction(i);
                row.add(String.valueOf(value));
                String binary = Binary.intToBinaryString(value);
                row.add(binary);
            } catch (AddressOutOfRangeException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            rows.add(row);
        }

        return GenerateTableService.execute(headers, rows);
    }
}
