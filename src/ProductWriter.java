import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductWriter {

    public static void main(String[] args) {
        List<String> products = new ArrayList<>();
        Scanner in = new Scanner(System.in);

        boolean done = false;

        do {
            String ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]: ");
            String name = SafeInput.getNonZeroLenString(in, "Enter the product name: ");
            String description = SafeInput.getNonZeroLenString(in, "Enter the product description: ");
            double cost = SafeInput.getDouble(in, "Enter the product cost: ");

            String productRec = String.format("%s, %s, %s, %.2f", ID, name, description, cost);
            products.add(productRec);

            done = SafeInput.getYNConfirm(in, "Are you done?");
        } while (!done);

        writeToFile(products);
        System.out.println("Product file written!");
    }

    private static void writeToFile(List<String> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ProductTestData.txt"))) {
            for (String rec : products) {
                writer.write(rec);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
