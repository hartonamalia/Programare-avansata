import java.time.LocalDate;

public class Designer extends Person{
    private String designDomain;
    Designer(String designerHouse, String name, LocalDate birthDate)
    {
        super(name,birthDate);
        this.designDomain = designerHouse;
    }

    public String getDesignDomain() {
        return designDomain;
    }
}