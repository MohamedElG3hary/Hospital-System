package Java.mohamedproject.Console.Implementation;

import Java.mohamedproject.Console.Interfaces.MenuView;
import Java.mohamedproject.DTO.EmployeeDTO;
import Java.mohamedproject.DTO.EmployeeDetails;
import Java.mohamedproject.Entity.Department;
import Java.mohamedproject.Entity.Doctor;
import Java.mohamedproject.Entity.Nurse;
import Java.mohamedproject.Entity.Reception;
import Java.mohamedproject.Enums.Departments;
import Java.mohamedproject.Enums.Nationality;
import Java.mohamedproject.Enums.PersonCity;
import Java.mohamedproject.Services.Interfaces.AdminService;

import java.util.List;
import java.util.Scanner;

import static Java.mohamedproject.util.EnumToList.chooseEnum;
import static Java.mohamedproject.util.ReadFromUser.*;

public class AdminConsoleController {

    private final MenuView menuView;
    private final Scanner scanner;
    private final AdminService adminService;

    private final int currentAdminId ;

    public AdminConsoleController(
            Scanner scanner,
            MenuView menuView,
            AdminService adminService, int currentAdminId
    ) {
        this.scanner = scanner;
        this.menuView = menuView;
        this.adminService = adminService;
        this.currentAdminId = currentAdminId;
    }

    public void run() {
        int adminChoice;

        do {
            menuView.showAdminMenu();
            adminChoice = readInt("Select your Operation: ",scanner);

            switch (adminChoice) {
                case 1 -> addDoctorFromConsole();
                case 2 -> addNurseFromConsole();
                case 3 -> addReceptionFromConsole();

                case 4 -> System.out.println("Add Department will be implemented later.");

                case 5 -> showAllEmployees();
                case 6 -> showDoctors();
                case 7 -> showNurses();
                case 8 -> showReceptionists();
                case 9 -> showAdmins();

                case 0 -> System.out.println("Admin Menu Closing.");

                default -> System.out.println("Invalid Input.");
            }

        } while (adminChoice != 0);
    }

    private void addDoctorFromConsole() {
        try {
            EmployeeDetails input = readStaffInput();

            System.out.println("Doctor Department:");
            Department department = new Department(chooseEnum(Departments.class,scanner));

            Doctor doctor = new Doctor(
                    input.name,
                    input.city,
                    input.nationality,
                    null,
                    input.salary,
                    input.workHours,
                    input.experienceYears,
                    department
            );

            int doctorId = adminService.addDoctor(currentAdminId, doctor);

            System.out.println("Doctor Added Successfully with ID: " + doctorId);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void addNurseFromConsole() {
        try {
            EmployeeDetails input = readStaffInput();

            System.out.println("Nurse Department:");
            Department department = new Department(chooseEnum(Departments.class,scanner));

            Nurse nurse = new Nurse(
                    input.name,
                    input.city,
                    input.nationality,
                    null,
                    input.salary,
                    input.workHours,
                    input.experienceYears,
                    department
            );

            int nurseId = adminService.addNurse(currentAdminId, nurse);

            System.out.println("Nurse Added Successfully with ID: " + nurseId);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void addReceptionFromConsole() {
        try {
            EmployeeDetails input = readStaffInput();


            Department department = new Department(Departments.RECEPTION);

            Reception reception = new Reception(input.name, input.city , input.nationality,null,  input.salary, input.workHours, input.experienceYears , department);

            int receptionId = adminService.addReception(
                    currentAdminId,
                    reception,
                    department
            );

            System.out.println("Reception Added Successfully with ID: " + receptionId);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void showAllEmployees() {
        try {
            List<EmployeeDTO> employees = adminService.getAllEmployees();
            printEmployees(employees);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void showDoctors() {
        try {
            List<EmployeeDTO> doctors = adminService.getAllDoctors();
            printEmployees(doctors);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void showNurses() {
        try {
            List<EmployeeDTO> nurses = adminService.getAllNurses();
            printEmployees(nurses);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void showReceptionists() {
        try {
            List<EmployeeDTO> receptionists = adminService.getAllReceptionists();
            printEmployees(receptionists);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void showAdmins() {
        try {
            List<EmployeeDTO> admins = adminService.getAllAdmins();
            printEmployees(admins);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void printEmployees(List<EmployeeDTO> employees) {
        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("======================================");

        for (EmployeeDTO employee : employees) {
            System.out.println(employee);
        }

        System.out.println("======================================");
    }

    private EmployeeDetails readStaffInput() throws Exception {
        System.out.println("Enter Staff Details:");

        String name = readLine("Name: " ,scanner);

        if (!isValidName(name)) {
            throw new Exception("Invalid name! Must be 2+ letters, spaces between words only.");
        }

        System.out.println("City:");
        PersonCity address = chooseEnum(PersonCity.class,scanner);

        System.out.println("Nationality:");
        Nationality nationality = chooseEnum(Nationality.class,scanner);

        System.out.println("Department : ");
        Departments department = chooseEnum(Departments.class,scanner);


        double salary = readDouble("Salary: ",scanner);

        if (salary <= 0) {
            throw new Exception("Salary must be positive.");
        }

        int workHours = readInt("Work Hours: " , scanner);

        if (workHours <= 0) {
            throw new Exception("Work hours must be positive.");
        }

        int experienceYears = readInt("Experience Years: " , scanner);

        if (experienceYears < 0) {
            throw new Exception("Experience years must be 0 or above.");
        }

        return new EmployeeDetails(name,address,nationality,department,salary,workHours,experienceYears);
    }











}