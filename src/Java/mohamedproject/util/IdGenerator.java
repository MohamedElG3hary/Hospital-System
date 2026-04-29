package Java.mohamedproject.IdGenerator;

import java.io.*;

public class IdGenerator implements Serializable {
    private static final String FILE_NAME = "src/resources/last_id.txt";
    private static int counter = loadLastId();


    public static int getNextId() {
        int id = counter;
        counter++;
        saveLastId(counter);
        return id;
    }
    private static int loadLastId() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return 1;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();

            if (line != null && !line.isEmpty()) {
                return Integer.parseInt(line);
            }

        } catch (Exception e) {
            System.out.println("Error reading ID file: " + e.getMessage());
        }

        return 1;
    }

    private static void saveLastId(int id) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            writer.write(String.valueOf(id));
        } catch (Exception e) {
            System.out.println("Error saving ID file: " + e.getMessage());
        }
    }



}
