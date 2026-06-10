package Java.mohamedproject.Console.Implementation;

import Java.mohamedproject.Console.Interfaces.MenuView;
import Java.mohamedproject.DTO.PatientDTO;
import Java.mohamedproject.DTO.PatientOrderDTO;
import Java.mohamedproject.Entity.Order;
import Java.mohamedproject.Entity.Patient;

import Java.mohamedproject.Services.Interfaces.ReceptionService;
import Java.mohamedproject.Services.Results.PatientWithOrderResult;

import java.util.ArrayList;
import java.util.Scanner;

import static Java.mohamedproject.util.ReadFromUser.*;

public class ReceptionConsoleController {

    private final MenuView menuView;
    private final Scanner scanner;
    private final ReceptionService receptionService;

    private final int currentReceptionId ;

    public ReceptionConsoleController(
            Scanner scanner,
            MenuView menuView,
            ReceptionService receptionService, int currentReceptionId
    ) {
        this.scanner = scanner;
        this.menuView = menuView;
        this.receptionService = receptionService;
        this.currentReceptionId = currentReceptionId;
    }

    private void showAllPatients() {
        try {
            ArrayList<PatientDTO> patients = receptionService.getAllPatients();

            if (patients == null || patients.isEmpty()) {
                System.out.println("No patients found.");
                return;
            }

            System.out.println("======================================");

            for (PatientDTO patient : patients) {
                System.out.println(patient);
            }

            System.out.println("======================================");

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void run() throws Exception {
        int receptionChoice;

        do {
            menuView.showReceptionMenu();

            receptionChoice = readInt("Select your Operation: ", scanner);

            switch (receptionChoice) {
                case 1 -> patientOperation();

                case 0 -> System.out.println("Reception Menu Closing.");

                default -> System.out.println("Invalid Input.");
            }

        } while (receptionChoice != 0);
    }

    private void patientOperation() throws Exception {
        int selectMode;

        do {
            menuView.showPatientManagementMenu();

            selectMode = readInt("Select Status Your Patient: ", scanner);

            switch (selectMode) {
                case 1 -> patientAlreadyOperation();

                case 2 -> newPatientOperation();

                case 3 -> showAllPatients();

                case 0 -> System.out.println("Patient Management Closing.");

                default -> System.out.println("Invalid Patient Status.");
            }

        } while (selectMode != 0);
    }


    private void printOrdersTable(ArrayList<PatientOrderDTO> orders) {
        if (orders == null || orders.isEmpty()) {
            System.out.println("No orders found.");
            return;
        }

        System.out.println("===============================================================================================================");
        System.out.printf(
                "%-10s %-20s %-15s %-15s %-12s %-20s %-10s%n",
                "ID",
                "Name",
                "City",
                "Nationality",
                "Blood Type",
                "Disease",
                "Priority"
        );
        System.out.println("===============================================================================================================");

        for (PatientOrderDTO order : orders) {
            System.out.printf(
                    "%-10d %-20s %-15s %-15s %-12s %-20s %-10d%n",
                    order.patientId,
                    order.name,
                    order.City,
                    order.nationality,
                    order.blood_type,
                    order.disease,
                    order.priority_value
            );
        }

        System.out.println("===============================================================================================================");
    }


    private void patientAlreadyOperation() throws Exception {
        int choice;

        do {
            menuView.showPatientAlreadyMenu();

            choice = readInt("Enter Your Choice: ", scanner);

            switch (choice) {
                case 1 -> System.out.println("Search Patient By ID will be implemented later.");

                case 2 -> addOrderToExistingPatient();

                case 3 -> printOrdersTable(receptionService.getAllOrders());

                case 0 -> System.out.println("Already Patient Menu Closing.");

                default -> System.out.println("Invalid Choice.");
            }

        } while (choice != 0);
    }

    private void newPatientOperation() {
        int newChoice;

        do {
            menuView.showNewPatientMenu();

            newChoice = readInt("Enter Your Choice: ", scanner);

            switch (newChoice) {
                case 1 -> addPatientFromConsole();

                case 2 -> addPatientWithOrderFromConsole();

                case 0 -> System.out.println("New Patient Menu Closing.");

                default -> System.out.println("Invalid Choice.");
            }

        } while (newChoice != 0);
    }

    private void addPatientFromConsole() {
        try {
            Patient patient = readPatientInput(scanner);

            int patientId = receptionService.addPatient(currentReceptionId, patient);

            System.out.println("Patient Added Successfully with ID: " + patientId);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void addOrderToExistingPatient() {
        try {
            int patientId = readInt("Enter Patient ID: ", scanner);

            int orderValue = readInt("Enter Order Priority Value From 1 To 5: ", scanner);

            if (orderValue < 1 || orderValue > 5) {
                throw new Exception("Order priority must be between 1 and 5.");
            }

            Order order = new Order(orderValue, null);

            int orderId = receptionService.addOrder(patientId, order);

            System.out.println("Order Added Successfully with ID: " + orderId);

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void addPatientWithOrderFromConsole() {
        try {
            Patient patient = readPatientInput(scanner);

            int orderValue = readInt("Enter Order Priority Value From 1 To 5: ", scanner);

            if (orderValue < 1 || orderValue > 5) {
                throw new Exception("Order priority must be between 1 and 5.");
            }

            Order order = new Order(orderValue, patient);

            PatientWithOrderResult result =
                    receptionService.addPatientWithOrder(currentReceptionId, patient, order);

            System.out.println("Patient Added Successfully with ID: " + result.getPatientId());
            System.out.println("Order Added Successfully with ID: " + result.getOrderId());

        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }


}