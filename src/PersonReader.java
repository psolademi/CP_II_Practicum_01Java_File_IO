import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PersonReader {

    public static void main(String[] args) {
        List<Person> people = readFromFile();
        displayPeople(people);
    }

    private static List<Person> readFromFile() {
        List<Person> people = new ArrayList<>();
        Path file = Paths.get(System.getProperty("user.dir") + "\\src\\personData.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(", ");
                if (data.length == 5) {
                    String ID = data[0];
                    String lastName = data[1];
                    String firstName = data[2];
                    String title = data[3];
                    int YOB = Integer.parseInt(data[4]);

                    Person person = new Person(ID, firstName, lastName, title, YOB);
                    people.add(person);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return people;
    }

    private static void displayPeople(List<Person> people) {
        if (people.isEmpty()) {
            System.out.println("No people to display.");
        } else {
            System.out.println("People:");
            for (Person person : people) {
                System.out.println(person);
            }
        }
    }
}
