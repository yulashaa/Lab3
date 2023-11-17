package Task3;

public class Product {
    private String name;
    private double price;
    private int amount;
    private String type;

    public Product(final String name, double price, final int amount, final String type) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public void setAmount(final int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }


}
