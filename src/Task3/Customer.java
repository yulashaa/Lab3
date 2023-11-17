package Task3;

import java.util.ArrayList;
import java.util.List;
public class Customer {
    private String username;
    private PurchaseHistory purchaseHistory;

    public Customer(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public PurchaseHistory getPurchaseHistory() {
        return purchaseHistory;
    }
}
