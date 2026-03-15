public class Patient extends Person{

    private String disease;
    private String bloodType;



    public Patient(){
        super();
        this.disease = "Empty";
        this.bloodType = "NO DATA !!";


    }

    public Patient(String name , String address , String nationality, String id ,String disease , String bloodType ){
        super(name ,  address ,  nationality, id);
        this.disease = disease;
        this.bloodType = bloodType;


    }

//    public String getId(){
//        return 1;
//    }

    public String getDisease() {
        return disease;
    }

    public String getBloodType() {
        return bloodType;
    }

    @Override
    public String toString() {
        return "Patient{" +
                super.toString() +
                "disease='" + disease + '\'' +
                ", bloodType='" + bloodType + '\'' +
                '}';
    }
}
