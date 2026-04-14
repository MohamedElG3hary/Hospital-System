package Java.mohamedproject.Person;

import Java.mohamedproject.Nationality.Nationality;

import java.io.Serial;
import java.io.Serializable;

public abstract class  Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String address;
    private String nationality;
    protected String id;


    public Person(){
        this.name = "Mohamed ";
        this.address = "Alex";
        this.nationality = "Egyptian";
        this.id = "000";

    }

    public static  <T extends Enum<T>> boolean isValidValue(Class<T> enumType, String input) {
        try {
            Enum.valueOf(enumType,input.toUpperCase().trim());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Person(String name , String address , String enteredNationality, String id)throws Exception{
        if(name.matches("[a-zA-Z]+")){
            this.name = name;
        }else {
            throw new Exception("Name Must be contains Letters Only !!");
        }
        if(isValidValue(PersonCity.class,address)){

            this.address = address.toUpperCase();
        }
        else {
            throw new Exception("Not Valid Address !! ");
        }
        if (isValidValue(Nationality.class,enteredNationality)){

            this.nationality = enteredNationality.toUpperCase();
        }else {
            throw new Exception("Invalid Nationality !! ");
        }


        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }



    public void setAddress(String address) {
        this.address = address;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNationality() {
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
