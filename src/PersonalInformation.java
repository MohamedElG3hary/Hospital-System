public class PersonalInformation {

    private String name;
    private String address;
    private String nationality;
    protected String id;

    public PersonalInformation(){
        this.name = "Mohamed ";
        this.address = "Alex";
        this.nationality = "Egyptian";
        this.id = "000"; 
    }


    public  PersonalInformation(String name , String address , String nationality, String id){
        this.name = name;
        this.address = address;
        this.nationality = nationality;
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
        return "PersonalInformation{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", nationality='" + nationality + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
