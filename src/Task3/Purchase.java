package Task3;

import java.util.ArrayList;
import java.util.List;


class Purchase {
    final List<Purchase> purchases = new ArrayList<>();
    final Product product;
    final int amount;
    private boolean isPaid;
    private boolean hasBag;

    public Purchase(final Product product, final int amount) {
        this.product = product;
        this.amount = amount;
        this.hasBag = false;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public String getProductName() {
        return product.getName();
    }

    public boolean hasBag() {
        return hasBag;
    }

    public void addBag() {
        this.hasBag = true;
    }

    public void printReceipt() {
        if (isPaid == true) {
            System.out.println("Product: " + product.getName() + ", Quantity: " + amount);
        } else {
            System.out.println("This purchase is not paid. To view the receipt, pay for it.");
        }
    }

    public void setPaid(final boolean paid) {
        if (!isPaid) {
            this.isPaid = paid;
        } else {
            System.out.println("This purchase receipt is already paid and cannot be edited.");
        }
    }
}

