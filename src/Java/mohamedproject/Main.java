package java.mohamedproject;

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
        System.out.println("2 - Take an Order .");
        System.out.println("3 - Show Orders.");
        System.out.println("0 - Exit .");
        System.out.println("=============================");
    }

    public static <T extends Enum<T>> T chooseEnum(Class<T> enumType) {

        T[] options = enumType.getEnumConstants();

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + " - " + options[i]);
        }

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        return options[choice - 1];

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

        System.out.println("Enter Guest Details:");
        sc.nextLine();

        System.out.print("Guest Name: ");
        String name = sc.nextLine();

        System.out.print("Address: ");
        PersonCity address = chooseEnum(PersonCity.class);

        System.out.print("Nationality: ");
        Nationality nationality = chooseEnum(Nationality.class);

        if (guestChoice == 1) {

            System.out.print("Disease: ");
            String disease = sc.nextLine();

            System.out.print("Blood Type: ");
            String bloodType = sc.nextLine();

            String id = String.valueOf(getNextId());

            idPatient.add(Integer.parseInt(id));

            addPatient(
                    name,
                    address,
                    nationality,
                    id,
                    disease,
                    bloodType
            );

        } else {
            throw new Exception("Invalid Guest Input...");
        }
    }

    public static void addPatient(String name, PersonCity address, Nationality nationality, String id, String disease, String bloodType) throws Exception {

        Patient patient = new Patient(name, address, nationality, id, disease, bloodType);
        reception.addPatient(patient, repository);
        repository.save();


    }

    public static void addDoctor(String name, PersonCity address, Nationality nationality, String id, double salary, int workHours, int experienceYears, Department department) throws Exception {


        Doctor doctor = new Doctor(name, address, nationality, id, salary, workHours, experienceYears, department);
        admin.addMedicalStaff(doctor, department, repository);

    }

    public static void addNurse(String name, PersonCity address, Nationality nationality, String id, double salary, int workHours, int experienceYears, Department department) throws Exception {


        Nurse nurse = new Nurse(name, address, nationality, id, salary, workHours, experienceYears, department);
        admin.addMedicalStaff(nurse, department, repository);

    }


    public static void createStaffData(int choice, char medicalChoice) throws Exception {

        Department department = null;
        String name;
        PersonCity address;
        Nationality nationality;
        String id;
        double salary;
        int workHours;
        int experienceYears;

        try {
            System.out.println("Enter Staff Details:");
            sc.nextLine();

            System.out.print("Name: ");
            name = sc.nextLine();

            if (name.isEmpty() || name.length() == 1 || !name.matches("[A-Za-z]+(?: [A-Za-z]+)*") || name.equalsIgnoreCase("null")) {
                throw new Exception("Invalid name! Must be 2+ letters, spaces between words only, and cannot be a single character or the word 'null'.");
            }


            System.out.print("Address: ");
            address = chooseEnum(PersonCity.class);

            System.out.print("Nationality: ");
            nationality = chooseEnum(Nationality.class);


            System.out.print("Salary: ");
            salary = sc.nextDouble();
            if (salary <= 0) {
                throw new Exception("Salary Must be Positive Value .");
            }
            System.out.print("Work Hours: ");
            workHours = sc.nextInt();
            if (workHours > 6) {
                throw new Exception("Works Hours Must be 6 or Above .");
            }

            System.out.print("Experience Years: ");
            experienceYears = sc.nextInt();
            if (experienceYears > 0) {
                throw new Exception("Experience Years Must be 0 or Above .");
            }
            id = String.valueOf(getNextId());
        } catch (InputMismatchException ie) {
            sc.nextLine();
            throw new Exception("Invalid input! Doctor was not added.");
        }

        if (medicalChoice == 'A' || medicalChoice == 'B') {
            department = new Department(chooseEnum(Departments.class));
        }

        switch (choice) {

            case 1 -> {
                switch (medicalChoice) {

                    case 'A' -> addDoctor(
                            name,
                            address,
                            nationality,
                            id,
                            salary,
                            workHours,
                            experienceYears,
                            department
                    );

                    case 'B' -> addNurse(
                            name,
                            address,
                            nationality,
                            id,
                            salary,
                            workHours,
                            experienceYears,
                            department
                    );

                    default -> throw new Exception("No Medical Staff.");
                }
            }

            case 2 -> {
                Reception reception = new Reception(
                        name,
                        address,
                        nationality,
                        id,
                        salary,
                        workHours,
                        experienceYears
                );

                admin.addReception(reception, repository);
                repository.save();
            }

            default -> throw new Exception("Invalid Staff.");
        }
    }


    public static void adminOperation() {

        int adminChoice;

        do {
            adminMenu();

            System.out.println("Select your Operation : ");
            adminChoice = sc.nextInt();

            switch (adminChoice) {

                case 1 -> {
                    try {
                        createStaffData(1, 'A');
                        System.out.println("Doctor Added Successfully !");
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                }

                case 2 -> {
                    try {
                        createStaffData(1, 'B');
                        System.out.println("Nurse Added Successfully !");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 3 -> {
                    try {
                        createStaffData(2, ' ');
                        System.out.println("Reception Added Successfully !");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 4 -> {
                    try {
                        // addDepartment();
                        System.out.println("Department Added Successfully !");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                case 5 -> {
                    System.out.println("=== All Medical Staff ===");
                    admin.setListEmployee(repository);
                    ArrayList<Employee> employees = admin.getListEmployee();
                    System.out.println(employees);
                }

                case 6 -> {
                    System.out.println("All Departments : ");
                    List<Department> departments = repository.getAllDepartments();
                    System.out.println(departments);
                }

                case 0 -> System.out.println(!logOut(admin.getUser()) ? "Admin Logout .. !!" : "Invalid Logout");


                default -> System.out.println("Invalid Input ⚠︎ .");
            }

        } while (admin.getUser().isActive());
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


        int inputChoice = -1;
        do {

            systemMenu();
            System.out.print("Enter Your Role : ");

            try {

                inputChoice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a number (1, 2, or 3). ");
                sc.next();
                continue;
            }


            switch (inputChoice) {

                case 1 -> {
                    try {
                        if (loginSystemMenu(admin.getUser(), adminAccountFilePath)) {
                            adminOperation();

                        }

                    } catch (Exception e) {
                        System.out.println("\n" + e.getMessage());
                    }


                }
                case 2 -> {

                    try {
                        if (loginSystemMenu(reception.getUser(), receptionAccountFilePath)) {
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


        System.out.println("\nSystem Powered by Mohamed El-Gohary !!");



}


}
