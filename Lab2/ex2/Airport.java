public class Airport extends Location {
    private int numTerminals;

    public Airport(String name, int numTerminals) {
        super(name);
        this.numTerminals = numTerminals;
    }

    public int getNumTerminals() {
        return numTerminals;
    }
}
