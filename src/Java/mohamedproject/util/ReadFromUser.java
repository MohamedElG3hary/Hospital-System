package Java.mohamedproject.util;

import Java.mohamedproject.Entity.Patient;
import Java.mohamedproject.Enums.BloodTypes;
import Java.mohamedproject.Enums.Diseases;
import Java.mohamedproject.Enums.Nationality;
import Java.mohamedproject.Enums.PersonCity;

import java.util.Scanner;

import static Java.mohamedproject.util.EnumToList.chooseEnum;

public class ReadFromUser {

    public static int readInt(String message , Scanner scanner) {
        System.out.print(message);

        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Invalid number. Try again: ");
        }

        int value = scanner.nextInt();
        scanner.nextLine();

        return value;
    }

    public static double readDouble(String message , Scanner scanner ) {
        System.out.print(message);

        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.print("Invalid number. Try again: ");
        }

        double value = scanner.nextDouble();
        scanner.nextLine();

        return value;
    }

    public static String readLine(String message , Scanner scanner) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public static boolean isValidName(String name) {
        return name != null
                && name.trim().length() > 1
                && name.matches("[A-Za-z]+(?: [A-Za-z]+)*")
                && !name.equalsIgnoreCase("null");
    }


    public static Patient readPatientInput(Scanner scanner) throws Exception {
        System.out.println("Enter Patient Details:");

        String name = readLine("Patient Name: ", scanner);

        if (!isValidName(name)) {
            throw new Exception("Invalid name! Must be 2+ letters, spaces between words only.");
        }

        System.out.println("Diseases : ");
        Diseases disease = chooseEnum(Diseases.class,scanner);

        System.out.println("Blood Type: ");
        BloodTypes bloodType = chooseEnum(BloodTypes.class,scanner);


        System.out.println("City:");
        PersonCity city = chooseEnum(PersonCity.class, scanner);

        System.out.println("Nationality:");
        Nationality nationality = chooseEnum(Nationality.class, scanner);





        return new Patient(
                name,
                city,
                nationality,
                null,
                disease.name(),
                bloodType.name()
        );
    }















}
