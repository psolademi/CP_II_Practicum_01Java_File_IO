import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<String> folks = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        // Define workingDirectory and file outside the main loop
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\pearsonData.txt");

        boolean done = false;

        String personRec = "";
        String ID = "";
        String firstName = "";
        String lastName = "";
        String title = "";
        int YOB = 0;

        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]: ");
            firstName = SafeInput.getNonZeroLenString(in, "Enter the first name: ");
            lastName = SafeInput.getNonZeroLenString(in, "Enter the last name: ");
            title = SafeInput.getNonZeroLenString(in, "Enter the title: ");
            YOB = SafeInput.getRangedInt(in, "Enter the year of birth: ", 1000, 9999);

            personRec = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + YOB;
            folks.add(personRec);

            done = SafeInput.getYNConfirm(in, "Are you done?");
        } while (!done);

        for (String p : folks)
            System.out.println(p);

        try {
            // Wrap BufferedWriter around a lower level BufferedOutputStream
            OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, StandardOpenOption.CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            // Write data to the file
            for (String rec : folks) {
                writer.write(rec);
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
