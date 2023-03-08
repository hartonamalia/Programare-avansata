import java.lang.Math;
public class Road{
    private RoadType type;
    private int length;
    private int speedLimit;
    private Location roadStart;
    private Location roadEnd;
    Road(RoadType type, int length, int speedLimit, Location roadStart, Location roadEnd){
        if(length >= verificareDistanta(roadStart,roadEnd)) {
            this.type = type;
            this.length = length;
            this.speedLimit = speedLimit;
            this.roadStart = roadStart;
            this.roadEnd = roadEnd;
        }
        else
        {
            System.out.println("Lungimea strazii nu trebuie sa fie mai mica decat distanta euclidiana dintre coordonatele locatiilor");
            System.exit(1);
        }

    }
    public double verificareDistanta(Location roadStart,Location roadEnd)
    {
        double distanta = Math.sqrt((roadStart.getX()-roadEnd.getX())*(roadStart.getX()-roadEnd.getX()) + (roadStart.getY()-roadEnd.getY())*(roadStart.getY()-roadEnd.getY()));
        return distanta;
    }
    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setType(RoadType type) {
        this.type = type;
    }

    public RoadType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Road{" +
                "type=" + type +
                ", length=" + length +
                ", speedLimit=" + speedLimit +
                '}';
    }
}
