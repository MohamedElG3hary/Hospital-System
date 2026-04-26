package Java.mohamedproject.Person;

import Java.mohamedproject.Nationality.Nationality;

import java.io.Serial;
import java.io.Serializable;

public abstract class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private PersonCity address;
    private Nationality nationality;
    protected String id;


    public Person() {
        this.name = "Mohamed ";
        this.address = PersonCity.ALEXANDRIA;
        this.nationality = Nationality.EGYPT;
        this.id = "000";

    }


    public Person(String name, PersonCity address, Nationality enteredNationality, String id) throws Exception {

        this.name = name;
        this.address = address;
        this.nationality = enteredNationality;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public PersonCity getAddress() {
        return address;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public String getId() {
        return id;
    }


    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nationality='" + nationality + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
