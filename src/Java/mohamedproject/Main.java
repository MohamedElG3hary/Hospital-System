package Java.mohamedproject;


import Java.mohamedproject.Admin.Administrators;
import Java.mohamedproject.Department.Department;
import Java.mohamedproject.Doctor.Doctor;
import Java.mohamedproject.Employee.Employee;
import Java.mohamedproject.FileHospitalRepository.FileHospitalRepository;
import Java.mohamedproject.HospitalRepository.HospitalRepository;
import Java.mohamedproject.Nurse.Nurse;
import Java.mohamedproject.Order.Order;
import Java.mohamedproject.Patient.Patient;
import Java.mohamedproject.Reception.Reception;
import Java.mohamedproject.SearchValues.Search;

import static LoginService.HospitalLogin.loginSystemMenu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static final Administrators admin = new Administrators();
    public static final Reception reception = new Reception();

    public static HospitalRepository repository;

    public static String adminAccountFilePath = "src/resources/Admin Account.txt";
    public static String receptionAccountFilePath = "src/resources/receptionAccounts.txt";


    public static void systemMenu() {
        System.out.println("=============================");
        System.out.println("Hello To Hospital System : ");
        System.out.println("1 - Sign in As Admin .");
        System.out.println("2 - Sign in As Reception .");
        System.out.println("3 - Exit .");
        System.out.println("=============================");
    }

    public static void adminMenu() {
        System.out.println("=============================");
        System.out.println("Hello To Admin Role : ");
        System.out.println("1 - Add Doctor .");
        System.out.println("2 - Add Nurse .");
        System.out.println("3 - Add Reception .");
        System.out.println("4 - Add Department .");
        System.out.println("5 - Know all Employees .");
        System.out.println("6 - Know all Departments .");
        System.out.println("0 - Exit .");
        System.out.println("=============================");
    }

    public static void receptionMenu() {
        System.out.println("=============================");
        System.out.println("Hello To Reception Role : ");
        System.out.println("1 - Patient Management .");
        System.out.println("0 - Exit .");
        System.out.println("=============================");
    }

    public static void patientManagement() {
        System.out.println("=============================");
        System.out.println("--- Patient Management : ---");
        System.out.println("1 - Already Registered  .");
        System.out.println("2 - Don't Registered Before .");
        System.out.println("3 - Show All Patients .");
        System.out.println("0 - Exit .");
        System.out.println("=============================");
    }

    public static void patientAlreadyMenu() {
        System.out.println("=============================");
        System.out.println(" Patient Management - Existing Patient : ");
        System.out.println("1 - Search Your Patient By Id : ");
        System.out.println("2 - Take an Order.Order.");
        System.out.println("3 - Show Orders.");
        System.out.println("0 - Exit .");
        System.out.println("=============================");
    }


    public static void patientAlreadyOperation(ArrayList<Patient> patients, ArrayList<Integer> ids) throws Exception {
        ArrayList<Order> orders = reception.getOrders();
        int choice;
        do {
            patientAlreadyMenu();
            System.out.print("Enter Your Choice : ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Your Patient Id : ");
                    int id = sc.nextInt();

                    if (repository.getAllPatients().isEmpty()) {
                        System.out.println("Patient Cannot be found ..");
                        createGuestData(1, ids);
                    } else {


                        int location = Search.binarySearch(id, ids, 0, patients.size() - 1);
                        if (location != -1) {
                            System.out.println("Your Patient is " + patients.get(location).toString());
                        } else {
                            System.out.println("Patient is not Exist , Please Enter your Data .");
                        }

                    }
                }
                case 2 -> {
                    System.out.println(" Order Process : ");
                    System.out.println("Enter Patient Id him takes order : ");
                    int id = sc.nextInt();
                    System.out.println("Enter Order Value : ");
                    int order = sc.nextInt();


                    int location = Search.binarySearch(id, ids, 0, patients.size() - 1);
                    if (location != -1) {
                        Order obj = new Order(order, patients.get(location));
                        reception.addOrderedPatient(obj);
                    } else {
                        System.out.println("Please Check for Patient Details !!! ");
                    }


                }
                case 3 -> System.out.println(orders);

                case 0 -> System.out.println(" Already Patient Menu Closing .");

                default -> System.out.println("Invalid Choice ... ");
            }


        } while (choice != 0);


    }


    public static void newPatientOperation(ArrayList<Integer> idPatient) throws Exception {
        int newChoice;
        do {
            newPatientMenu();
            System.out.print("Enter Your Choice : ");
            newChoice = sc.nextInt();
            switch (newChoice) {
                case 1 -> createGuestData(1, idPatient);


                case 0 -> {
                    System.out.println("GO TO EXISTING MENU TO COMPLETE OPERATION !!!!");
                    System.out.println(" New Patient Menu Closing .");
                }
                default -> System.out.println("Invalid Choice ...!!");
            }


        } while (newChoice != 0);


    }

    public static void newPatientMenu() {
        System.out.println("=============================");
        System.out.println(" Patient Management - New Patient : ");
        System.out.println("1 - Enter Data Of Patient  .");
        System.out.println("0 - Exit .");
        System.out.println("=============================");
    }


    public static void patientOperation() throws Exception {
        ArrayList<Patient> patients = repository.getAllPatients();
        ArrayList<Integer> ids = new ArrayList<>();

        for (Patient i : patients) {
            ids.add(Integer.parseInt(i.getId()));
        }


        int selectMode;
        do {

            patientManagement();
            System.out.print(" Select Status Your Patient : ");
            selectMode = sc.nextInt();

            switch (selectMode) {
                case 1 -> patientAlreadyOperation(patients, ids);
                case 2 -> newPatientOperation(ids);
                case 3 -> {
                    System.out.println("All Patients Has Been Existing :");
                    System.out.println(patients);
                    System.out.println(ids);
                }

                default -> System.out.println(" Invalid Patient  Status !!");
            }
        } while (selectMode != 0);


    }

    public static void createGuestData(int guestChoice, ArrayList<Integer> idPatient) throws Exception {


        System.out.println("Enter Guest Details: ");
        sc.nextLine();
        System.out.print("Guest Name: ");
        String name = sc.nextLine();

        System.out.print("Address: ");
        String address = sc.nextLine();

        System.out.print("Nationality: ");
        String nationality = sc.nextLine();

        System.out.print("ID: ");
        String id = sc.nextLine();

        idPatient.add(Integer.parseInt(id));


        if (guestChoice == 1) {
            System.out.print("Disease : ");
            String disease = sc.nextLine();

            System.out.print("Blood Type : ");
            String bloodType = sc.nextLine();

            addPatient(name, address, nationality, id, disease, bloodType);
        } else {
            System.out.println("InValid Input ...");
        }


    }

    public static void addPatient(String name, String address, String nationality, String id, String disease, String bloodType) throws Exception {
        try {
            Patient patient = new Patient(name, address, nationality, id, disease, bloodType);
            reception.addPatient(patient, repository);
            repository.save();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addDoctor(String name, String address, String nationality, String id, double salary, int workHours, int experienceYears, Department department) throws Exception {

        try {

            Doctor doctor = new Doctor(name, address, nationality, id, salary, workHours, experienceYears, department);
            admin.addMedicalStaff(doctor, repository);

            department.addEmployee(doctor);
            repository.save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public static void addNurse(String name, String address, String nationality, String id, double salary, int workHours, int experienceYears, Department department) throws Exception {
        try {

            Nurse nurse = new Nurse(name, address, nationality, id, salary, workHours, experienceYears, department);
            admin.addMedicalStaff(nurse, repository);

            department.addEmployee(nurse);
            repository.save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public static void createStaffData(int choice, char medicalChoice) throws Exception {
        try {
            Department department = null;
            System.out.println("Enter Staff Details:");
            sc.nextLine();
            System.out.print("Name: ");
            String name;
            name = sc.nextLine();

            System.out.print("Address: ");
            String address = sc.nextLine();

            System.out.print("Nationality: ");
            String nationality = sc.nextLine();

            System.out.print("ID: ");
            String id = sc.nextLine();

            System.out.print("Salary: ");
            double salary = sc.nextDouble();

            System.out.print("Work Hours: ");
            int workHours = sc.nextInt();

            System.out.print("Experience Years: ");
            int experienceYears = sc.nextInt();
            sc.nextLine();
            if (Character.isLetter(medicalChoice)) {
                department = createDepartment();
            }


            switch (choice) {
                case 1 -> {
                    switch (medicalChoice) {

                        case 'A' ->
                                addDoctor(name, address, nationality, id, salary, workHours, experienceYears, department);

                        case 'B' ->
                                addNurse(name, address, nationality, id, salary, workHours, experienceYears, department);
                        default -> System.out.println("NO Medical Staff .");

                    }


                }
                case 2 -> {
                    Reception reception = new Reception(name, address, nationality, id, salary, workHours, experienceYears);
                    admin.addReception(reception, repository);

                    repository.save();
                }
                case 3 -> {
                }
                default -> System.out.println(" Invalid Staff .");

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


    public static Department createDepartment() throws Exception {
        System.out.println("Enter Department Details:");
        System.out.print("Name: ");
        String name = sc.nextLine();

        return new Department(name);
    }

    public static void addDepartment() throws Exception {
        try {
            Department department = createDepartment();
            admin.addDepartment(department, repository);
            repository.save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public static void adminOperation() throws Exception {
        admin.setListEmployee(repository);
        int adminChoice;
        do {
            adminMenu();
            System.out.println("Select your Operation : ");
            adminChoice = sc.nextInt();

            switch (adminChoice) {
                case 1 -> {
                    try {

                        createStaffData(1, 'A');
                        System.out.println("Doctor Added Successfully ! ");
                    } catch (Exception e) {
                        System.out.println(e.getMessage() + " ");
                    }

                }
                case 2 -> {
                    try {

                        System.out.println("Nurse Added Successfully ! ");
                        createStaffData(1, 'B');
                    } catch (Exception e) {
                        System.out.println(e.getMessage() + " ");

                    }
                }
                case 3 -> {
                    createStaffData(2, ' ');
                    System.out.println("Reception Added Successfully ! ");
                }
                case 4 -> {
                    try {
                        addDepartment();
                        System.out.println("Department Added Successfully ! ");
                    } catch (Exception e) {
                        System.out.println(e.getMessage() + " ");
                    }


                }
                case 5 -> {
                    System.out.println("=== All Medical Staff ===");
                    ArrayList<Employee> employees = admin.getListEmployee();
                    System.out.println(employees);

                }
                case 6 -> {
                    System.out.println("All Departments : ");

                    List<Department> departments = repository.getAllDepartments();
                    System.out.println(departments);


                }
                case 0 -> System.out.println("Admin Exiting !!");
                default -> System.out.println("Invalid Input ⚠︎ . ");
            }
        } while (adminChoice != 0);
    }

    public static void receptionOperation() throws Exception {
        int receptionChoice;

        do {
            receptionMenu();
            System.out.print("Select your Operation : ");
            receptionChoice = sc.nextInt();

            switch (receptionChoice) {
                case 1 -> patientOperation();

                case 0 -> System.out.println("Reception Exiting !!");

                default -> System.out.println("Invalid Input ⚠︎ . ");

            }
        } while (receptionChoice != 0);
    }


    public static void main(String[] args) throws Exception {
        final String PATH_FILE = "src/resources/HospitalData.ser";

        repository = new FileHospitalRepository(PATH_FILE);
        repository.load();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                repository.save();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }));

        try {
            int inputChoice;
            do {

                systemMenu();
                System.out.print("Enter Your Role : ");
                inputChoice = sc.nextInt();
                switch (inputChoice) {

                    case 1 -> {
                        try {
                            if (loginSystemMenu(admin, adminAccountFilePath)) {

                                adminOperation();

                            }

                        } catch (Exception e) {
                            System.out.println("\n" + e.getMessage());
                        }


                    }
                    case 2 -> {

                        try {
                            if (loginSystemMenu(reception, receptionAccountFilePath)) {
                                receptionOperation();

                            }
                        } catch (Exception e) {
                            System.out.println("\n" + e.getMessage());
                        }

                    }
                    case 3 -> System.out.println("System Closing ...");
                    default -> System.out.println("Invalid Input ⚠︎ . ");


                }

            } while (inputChoice != 3);

        } catch (InputMismatchException mismatchException) {
            System.out.println("\n" + "Invalid InputType");
        } finally {
            System.out.println("\nSystem Powered by Mohamed El-Gohary !!");
        }


    }
}
