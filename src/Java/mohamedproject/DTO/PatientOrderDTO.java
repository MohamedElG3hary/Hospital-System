package Java.mohamedproject.DTO;

public class PatientOrderDTO {

    public int patientId;
    public String name;
    public String City;
    public String nationality;
    public String disease;
    public  String blood_type;
    public  int priority_value;


    @Override
    public String toString() {
        return "PatientOrderDTO{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", City='" + City + '\'' +
                ", nationality='" + nationality + '\'' +
                ", disease='" + disease + '\'' +
                ", blood_type='" + blood_type + '\'' +
                ", priority_value=" + priority_value +
                '}';
    }
}
