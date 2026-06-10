package Java.mohamedproject.Console.Implementation;

import Java.mohamedproject.Console.Interfaces.MenuView;

public class ConsoleMenuView implements MenuView {

    @Override
    public void showSystemMenu() {
        System.out.println("=============================");
        System.out.println("Hello To Hospital System : ");
        System.out.println("1 - Sign in As Admin .");
        System.out.println("2 - Sign in As Reception .");
        System.out.println("3 - Exit .");
        System.out.println("=============================");
    }

    @Override
    public void showAdminMenu() {
        System.out.println("=============================");
        System.out.println("Hello To Admin Role : ");
        System.out.println("1 - Add Doctor .");
        System.out.println("2 - Add Nurse .");
        System.out.println("3 - Add Reception .");
        System.out.println("4 - Add Department .");
        System.out.println("5 - Show All Employees .");
        System.out.println("6 - Show Doctors .");
        System.out.println("7 - Show Nurses .");
        System.out.println("8 - Show Receptionists .");
        System.out.println("9 - Show Admins .");
        System.out.println("0 - Exit .");
        System.out.println("=============================");
    }

    @Override
    public void showReceptionMenu() {
        System.out.println("=============================");
        System.out.println("Hello To Reception Role : ");
        System.out.println("1 - Patient Management .");
        System.out.println("0 - Exit .");
        System.out.println("=============================");
    }

    @Override
    public void   showLoginMenu(){

        System.out.println("=============================");
        System.out.println("Hospital System");
        System.out.println("1 - Login");
        System.out.println("0 - Exit");
        System.out.println("=============================");


    }


    @Override
    public void showPatientManagementMenu() {
        System.out.println("=============================");
        System.out.println("--- Patient Management : ---");
        System.out.println("1 - Already Registered  .");
        System.out.println("2 - Don't Registered Before .");
        System.out.println("3 - Show All Patients .");
        System.out.println("0 - Exit .");
        System.out.println("=============================");
    }

    @Override
    public void showPatientAlreadyMenu() {
        System.out.println("=============================");
        System.out.println(" Patient Management - Existing Patient : ");
        System.out.println("1 - Search Your Patient By Id : ");
        System.out.println("2 - Take an Order .");
        System.out.println("3 - Show Orders.");
        System.out.println("0 - Exit .");
        System.out.println("=============================");
    }

    @Override
    public void showNewPatientMenu() {
        System.out.println("=============================");
        System.out.println(" Patient Management - New Patient : ");
        System.out.println("1 - Enter Data Of Patient  .");
        System.out.println("0 - Exit .");
        System.out.println("=============================");
    }
}