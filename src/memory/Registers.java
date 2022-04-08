package memory;

public class Registers {
    int[] registers;

    public Registers(int size) {
        registers = new int[0];
    }

    public void setRegister(int index, int value) {
        registers[index] = value;
    }

    public int getRegister(int index) {
        return registers[index];
    }
}
