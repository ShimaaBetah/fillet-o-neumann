package memory;

public class Registers {
    public int[] registers;

    public Registers(int size) {
        registers = new int[size];
    }

    public void setRegister(int index, int value) {
        registers[index] = value;
    }

    public int getRegister(int index) {
        return registers[index];
    }
}
