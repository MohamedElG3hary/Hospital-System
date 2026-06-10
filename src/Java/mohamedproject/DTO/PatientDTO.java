package Java.mohamedproject.DTO;

public class PatientDTO {


    public int patientId;
    public String name;
    public String city;
    public String nationality;
    public String bloodType;
    public String disease ;

    @Override
    public String toString() {
        return "PatientDTO{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", nationality='" + nationality + '\'' +
                ", bloodType='" + bloodType + '\'' +
                ", disease='" + disease + '\'' +
                '}';
    }
}
