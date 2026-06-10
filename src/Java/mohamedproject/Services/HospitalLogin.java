package Java.mohamedproject.Services;

import java.io.BufferedReader;
import java.io.FileReader;
import Java.mohamedproject.Entity.User;
import java.util.Scanner;

public class HospitalLogin {


    public static boolean verifyUserLogin(User user, String filePath, String delimiter) {
        String currentLine;
        String data[];

        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                data = currentLine.trim().split(delimiter);
                if (data[0].trim().equals(user.getUserName().trim()) && data[1].trim().equals(user.getHashPassword().trim())) {
                    user.setActive(true);
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;

    }


    public static boolean loginSystemMenu(User user, String filePath , Scanner sc) throws Exception {
        System.out.println("=============================");
        System.out.print("Enter Your Username : ");
        sc.nextLine();
        String userName = sc.nextLine();
        user.setUserName(userName.trim());
        System.out.print("Enter Your Password : ");

        String password = sc.nextLine();
        if (password.trim().length() != 8) {
            throw new Exception("Password Must be 8 Character ");
        } else {

            user.setHashPassword(password.trim());
        }
        System.out.print("=============================");

        if (verifyUserLogin(user, filePath, ",")) {
            return true;
        }
        throw new Exception("Error in Entered Fields ..");
    }

    public static boolean logOut(User user) {
        user.setUserName(null);
        user.setHashPassword(null);
        user.setActive(false);

        return user.isActive();


    }


}
