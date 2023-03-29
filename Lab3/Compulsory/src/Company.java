
public class Company implements Node, Comparable<Company> {
    private String name;

    public Company(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

   @Override
   public int compareTo(Company c) {
       return this.name.compareTo(c.name);
   }
    }

