package Client;

public class Client {

    private String surname;

    private String name;

    private String pathronymic;

    public Client(String surname, String name, String pathronymic) {
        this.surname = surname;
        this.name = name;
        this.pathronymic = pathronymic;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public String getPathronymic() {
        return pathronymic;
    }

}
