import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {

    public static void main(String[] args) {
        ArrayList<Person> people = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        // Define workingDirectory and file outside the main loop
        Path file = Paths.get(System.getProperty("user.dir") + "\\src\\personData.txt");

        boolean done = false;

        do {
            String ID = SafeInput.getNonEmptyString("Enter the ID [6 digits]: ");
            String firstName = SafeInput.getNonEmptyString("Enter the first name: ");
            String lastName = SafeInput.getNonEmptyString("Enter the last name: ");
            String title = SafeInput.getNonEmptyString("Enter the title: ");
            int YOB = SafeInput.getRangedInt("Enter the year of birth: ", 1000, 9999);

            Person person = new Person(ID, firstName, lastName, title, YOB);
            people.add(person);

            done = SafeInput.getYNConfirm("Are you done?");
        } while (!done);

        for (Person p : people)
            System.out.println(p);

        try {
            // Wrap BufferedWriter around a lower level BufferedOutputStream
            BufferedWriter writer = new BufferedWriter(new FileWriter(file.toFile(), true));

            // Write data to the file
            for (Person person : people) {
                writer.write(person.toCSVDataRecord());
                writer.newLine();
            }

            writer.close(); // Close the file to seal it and flush the buffer
            System.out.println("Data file written!");
        } catch (FileAlreadyExistsException e) {
            System.err.println("File already exists. Choose a different file name.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
