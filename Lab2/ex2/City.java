public class City extends Location {
    private int population;

    public City(String name, int population) {
        super(name);//trim arg nume catre constr cl parinte
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }
}
