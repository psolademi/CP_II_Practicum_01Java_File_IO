public class Product {
    private String ID;
    private String name;
    private String description;
    private double cost;

    // constructor
    public Product(String ID, String name, String description, double cost) {
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    // getters and setters
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    // additional methods
    public double calculateDiscountedCost(double discount) {
        return cost - (cost * discount);
    }

    // method to format data for CSV record
    public String toCSVDataRecord() {
        return ID + ", " + name + ", " + description + ", " + cost;
    }
}
