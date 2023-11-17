package Task3;

import java.util.ArrayList;
import java.util.List;

public class PurchaseHistory {
    private List<Purchase> purchases = new ArrayList<>();
    private boolean isPaid;

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void addPurchase(final Product product, final int amount) {
        purchases.add(new Purchase(product, amount));
    }

    public void showHistory() {
        System.out.println("Purchase History:");
        for (Purchase purchase : purchases) {
            System.out.println(purchase);
        }
    }

    public boolean isProductPaid(final String productName) {
        return purchases.stream()
                .filter(purchase -> purchase.getProduct().getName().equals(productName))
                .anyMatch(Purchase::isPaid);
    }

}
