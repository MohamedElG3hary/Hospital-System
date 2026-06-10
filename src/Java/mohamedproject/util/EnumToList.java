package Java.mohamedproject.util;

import java.util.Scanner;

public class EnumToList {




    public static <T extends Enum<T>> T chooseEnum(Class<T> enumType , Scanner sc) {

        T[] options = enumType.getEnumConstants();

        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + " - " + options[i]);
        }

        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();

        return options[choice - 1];

    }






}
