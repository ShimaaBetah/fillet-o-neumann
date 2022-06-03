package fillet.signals;

public class Signals {

    private boolean haltSignal;

    private static Signals instance = new Signals();

    private Signals() {
        this.haltSignal = false;
    }

    public static Signals getInstance() {
        return instance;
    }

    public void setHaltSignal(boolean haltSignal) {
        this.haltSignal = haltSignal;
    }

    public boolean getHaltSignal() {
        return this.haltSignal;
    }
}
