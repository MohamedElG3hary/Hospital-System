package LoginService;

import Java.mohamedproject.Employee.Employee;
import Java.mohamedproject.HospitalRepository.HospitalRepository;
import Java.mohamedproject.Person.Person;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import static Java.mohamedproject.Main.adminAccountFilePath;
import static Java.mohamedproject.Main.sc;

public class HospitalLogin {


    public static boolean verifyUserLogin(Employee user, String filePath, String delimiter) {
        String currentLine;
        String data[];

        try {
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            while ((currentLine = br.readLine()) != null) {
                data = currentLine.trim().split(delimiter);
                if (data[0].trim().equals(user.getName().trim()) && data[1].trim().equals(user.getPassword().trim())) {
                    return true;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;

    }


    public static boolean loginSystemMenu(Employee user , String filePath)throws Exception {
        System.out.println("=============================");
        System.out.print("Enter Your Username : ");
        sc.nextLine();
        String userName = sc.nextLine();
        user.setName(userName.trim());
        System.out.print("Enter Your Password : ");

        String password = sc.nextLine();
        if(password.trim().length()!=8){
            throw new Exception("Password Must be 8 Character ");
        }else {

            user.setPassword(password.trim());
        }
        System.out.print("=============================");

        if (verifyUserLogin(user, filePath, ",")) {
            return true;
        }
        throw new Exception("Error in Entered Fields ..");
    }


}
