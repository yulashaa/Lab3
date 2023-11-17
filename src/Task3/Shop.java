package Task3;

import java.util.*;
import java.util.stream.Collectors;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class Shop {
    private List<Product> products = new ArrayList<>();
    private Map<String, Customer> customers = new HashMap<>();
    private List<Purchase> purchases = new ArrayList<>();

    public void addProduct(final Product product) {
        products.add(product);
    }

    public void sellProduct(final String productName, final int amount) {
        products.stream()
                .filter(p -> p.getName().equals(productName))
                .findFirst()
                .ifPresent(p -> p.setAmount(p.getAmount() - amount));
    }

    public void editProduct(final String productName, final double newPrice, final int newAmount) {
        products.stream()
                .filter(p -> p.getName().equals(productName))
                .findFirst()
                .ifPresent(p -> {
                    p.setPrice(newPrice);
                    p.setAmount(newAmount);
                });
    }

    public void orderProduct(final String username, final String productName, final int amount) {
        products.stream()
                .filter(p -> p.getName().equals(productName) && p.getAmount() >= amount)
                .findFirst()
                .ifPresentOrElse(
                        product -> {
                            customers.get(username).getPurchaseHistory().addPurchase(product, amount);
                            product.setAmount(product.getAmount() - amount);
                            System.out.println(username + " ordered " + amount + " " + productName);
                        },
                        () -> System.out.println("Product not available.")
                );
    }

    public void showPurchaseHistory(final String username) {
        System.out.println("Purchase History for " + username + ":");
        customers.get(username).getPurchaseHistory().showHistory();
    }

    public void generateReceipt(String username) {
        Customer customer = customers.get(username);
        PurchaseHistory history = customer.getPurchaseHistory();

        String fileName = "receipt_" + username + "_"  + ".txt";

        boolean isPaid = history.getPurchases().stream()
                .allMatch(Purchase::isPaid);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Purchase Receipt for " + username + ":\n");

            if (isPaid) {
                writer.write("This receipt is paid and cannot be edited.\n");
            } else {
                System.out.println("Invalid format");
            }
        }  catch (ReceiptException e) {
            throw new RuntimeException(e);
        }
    }
    public void addBagForFruitsAndVegetables() {
        purchases.stream()
                .filter(purchase -> {
                    String type = purchase.getProduct().getType();
                    return type.equals("fruits") || type.equals("vegetables");
                })
                .forEach(Purchase::addBag);
    }

      public List<String> FishAndMeat() {
        return products.stream()
                .filter(e -> (e.getName().equals("Fish") || e.getName().equals("Meat") ))
                .map(e -> "Save " + e.getName() + " in the fridge")
                .collect(Collectors.toList());
    }

    public void sortByPrice() {
        products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice))
                .forEach(e -> System.out.println(e.getName() + " " + e.getPrice()));
    }

    public int countProducts() {
        return products.stream()
                .mapToInt(Product::getAmount)
                .sum();
    }
    public void averagePrice() {
        final double averagePrice = products.stream()
                .mapToDouble(e -> e.getPrice() * e.getAmount())
                .sum() / countProducts();
        System.out.println("Average price: " + averagePrice);
    }

    public double findMaxDailyIncome() {
        Map<LocalDate, Double> dailyIncome = purchases.stream()
                .collect(Collectors.groupingBy(
                        Purchase::getDate,
                        Collectors.summingDouble(purchase -> purchase.getProduct().getPrice() * purchase.getAmount())
                ));

        return dailyIncome.values().stream()
                .max(Double::compareTo)
                .orElse(0.0);
    }

        public double calculateUserExpenses(final String username) {
            List<Purchase> userPurchases = purchases.stream()
                    .filter(purchase -> purchase.getUsername().equals(username))
                    .collect(Collectors.toList());

            double totalExpenses = userPurchases.stream()
                    .mapToDouble(purchase -> purchase.getProduct().getPrice() * purchase.getAmount())
                    .sum();

            return totalExpenses;
        }
    private static double calculateTotalPrice(final List<Product> products) {
        return products.stream()
                .mapToDouble(product -> product.getPrice() * product.getAmount())
                .sum();
    }

    public Product findMostPopularProduct() {
        Map<Product, Long> productFrequency = purchases.stream()
                .map(Purchase::getProduct)
                .collect(Collectors.groupingBy(p -> p, Collectors.counting()));

        Optional<Map.Entry<Product, Long>> mostPopularEntry = productFrequency.entrySet().stream()
                .max(Map.Entry.comparingByValue());

        return mostPopularEntry.map(Map.Entry::getKey).orElse(null);
    }
}
