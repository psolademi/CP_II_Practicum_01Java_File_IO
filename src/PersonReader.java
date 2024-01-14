import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PersonReader {

    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Person File");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                String selectedFilePath = fileChooser.getSelectedFile().getPath();
                displayFileContent(selectedFilePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Operation canceled by the user.");
        }
    }

    private static void displayFileContent(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Display header
            System.out.println(String.format("%-10s %-15s %-15s %-10s %-4s",
                    "ID#", "Firstname", "Lastname", "Title", "YOB"));
            System.out.println("=====================================");

            // Display data records
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                System.out.println(String.format("%-10s %-15s %-15s %-10s %-4s",
                        data[0], data[1], data[2], data[3], data[4]));
            }
        }
    }
}
