import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductWriter {

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        boolean done = false;

        do {
            String ID = SafeInput.getNonEmptyString("Enter the ID [6 digits]: ");
            String name = SafeInput.getNonEmptyString("Enter the product name: ");
            String description = SafeInput.getNonEmptyString("Enter the product description: ");
            double cost = SafeInput.getRangedDouble("Enter the product cost", 0.01, Double.MAX_VALUE);

            Product product = new Product(ID, name, description, cost);
            products.add(product);

            done = SafeInput.getYNConfirm("Are you done?");
        } while (!done);

        writeToFile(products);
        System.out.println("Product file written!");
    }

    private static void writeToFile(List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ProductTestData.txt"))) {
            for (Product product : products) {
                writer.write(product.toCSVDataRecord());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
