public class GasStation extends Location {
    private double gasPrice;

    public GasStation(String name, double gasPrice) {
        super(name);
        this.gasPrice = gasPrice;
    }

    public double getGasPrice() {
        return gasPrice;
    }
}
