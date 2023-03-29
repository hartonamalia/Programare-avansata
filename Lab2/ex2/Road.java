public class Road {
    private Location start;
    private Location end;

    public Road(Location start, Location end) {
        this.start = start;
        this.end = end;
    }

    public Location getStart() {
        return start;
    }

    public Location getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Road) {
            Road other = (Road) obj;
            return (this.start.equals(other.start) && this.end.equals(other.end)) ||
                    (this.start.equals(other.end) && this.end.equals(other.start));
        }
        return false;
    }
}
