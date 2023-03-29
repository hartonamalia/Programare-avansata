import java.time.LocalDate;

public class Programmer  extends Person{
    private String programmingLanguage;
    Programmer(String programmingLanguage, String name, LocalDate birthDate)
    {
        super(name,birthDate);
        this.programmingLanguage = programmingLanguage;
    }

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }
}