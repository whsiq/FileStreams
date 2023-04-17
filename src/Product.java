public class Product {
    private String name;
    private String description;
    private String ID;
    private double cost;

    private static final int NAME_LENGTH = 35;
    private static final int DESCRIPTION_LENGTH = 75;
    private static final int ID_LENGTH = 6;

    public Product(String name, String description, String ID, double cost) {
        this.name = name;
        this.description = description;
        this.ID = ID;
        this.cost = cost;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }

    // all setters will use StringBuilder to either
    // add spaces to the specified length or
    // truncate the string to an acceptable length
    public void setName(String name) {
        StringBuilder paddedName = new StringBuilder(name);
        while (paddedName.length() < NAME_LENGTH) {
            paddedName.append(" ");
        }
        this.name = paddedName.substring(0, NAME_LENGTH);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        StringBuilder paddedName = new StringBuilder(description);
        while (paddedName.length() < DESCRIPTION_LENGTH) {
            paddedName.append(" ");
        }
        this.description = paddedName.substring(0, DESCRIPTION_LENGTH);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        StringBuilder paddedName = new StringBuilder(ID);
        while (paddedName.length() < ID_LENGTH) {
            paddedName.append(" ");
        }
        this.ID = paddedName.substring(0, ID_LENGTH);
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String toCSVDataRecord() {
        return ID + ", " + name + ", " + description + ", " + cost;
    }
}
