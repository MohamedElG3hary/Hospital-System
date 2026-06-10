package Java.mohamedproject.Console.Implementation;

import Java.mohamedproject.Console.Interfaces.MenuView;


import Java.mohamedproject.Entity.User;
import Java.mohamedproject.Repository.Implementation.*;
import Java.mohamedproject.Repository.Interfaces.*;
import Java.mohamedproject.Services.Implementation.DefaultAdminService;
import Java.mohamedproject.Services.Implementation.DefaultAuthService;
import Java.mohamedproject.Services.Implementation.DefaultReceptionService;
import Java.mohamedproject.Services.Interfaces.AdminService;
import Java.mohamedproject.Services.Interfaces.AuthService;
import Java.mohamedproject.Services.Interfaces.ReceptionService;

import java.util.Scanner;

import static Java.mohamedproject.util.ReadFromUser.readInt;
import static Java.mohamedproject.util.ReadFromUser.readLine;

public class ConsoleApplication {

    private final Scanner scanner;
    private final MenuView menuView;

    private final AdminService adminService;
    private final ReceptionService receptionService;
    private   AuthService authService;


    public ConsoleApplication() {
        this.scanner = new Scanner(System.in);
        this.menuView = new ConsoleMenuView();

        DoctorRepository doctorRepository = new JdbcDoctorRepository();
        NurseRepository nurseRepository = new JdbcNurseRepository();
        ReceptionRepository receptionRepository = new JdbcReceptionRepository();
        DepartmentRepository departmentRepository = new JdbcDepartmentRepository();
        AdminRepository adminRepository = new JdbcAdminRepository();



        PatientRepository patientRepository = new JdbcPatientRepository();
        PatientOrderRepository patientOrderRepository = new JdbcPatientOrderRepository();

       EmployeeRepository employeeRepository = new JdbcEmployeeRepository();

        this.adminService = new DefaultAdminService( doctorRepository, nurseRepository, receptionRepository, departmentRepository, adminRepository, employeeRepository );

        this.receptionService = new DefaultReceptionService(patientRepository, patientOrderRepository);

        AuthRepository authRepository = new JdbcAuthRepository();

         this.authService = new DefaultAuthService(authRepository);


    }



    private void login() {
        try {
            String username = readLine("Username: ",scanner);

            String password = readLine("Password: ",scanner);

            User user = authService.login(username, password);

            if (user.isAdmin()) {
                AdminConsoleController adminConsoleController =
                        new AdminConsoleController(
                                scanner,
                                menuView,
                                adminService,
                                user.getEmployeePersonId()
                        );

                adminConsoleController.run();

            } else if (user.isReception()) {
                ReceptionConsoleController receptionConsoleController =
                        new ReceptionConsoleController(
                                scanner,
                                menuView,
                                receptionService,
                                user.getEmployeePersonId()
                        );

                receptionConsoleController.run();

            } else {
                System.out.println("Unknown role.");
            }

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }


    public void run() throws Exception {
        int inputChoice;

        do {
            menuView.showLoginMenu();

            inputChoice  = readInt("Enter Your Choice: ", scanner);

            switch (inputChoice) {
                case 1 ->  login();
                case 0 -> System.out.println("System Closing ...");
                default -> System.out.println("Invalid Input.");
            }

        } while (inputChoice != 0);

        System.out.println("\nSystem Powered by Mohamed El-Gohary !!");
    }

}