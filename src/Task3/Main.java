package Task3;

public class Main {
    public static void main(String[] args) {

        final Shop shop = new Shop();
        final Product product = new Product("Fish", 10.0, 5, "Fish");
        final Purchase purchase = new Purchase(product, 3);

        System.out.println("All products in the shop:");
        final FileService fileService = new FileService();
        fileService.loadFromFile();

        shop.sellProduct("Banana", 36);

        shop.editProduct("Carrot", 18, 40);

        shop.orderProduct("Ivan", "Banana", 1);
        shop.orderProduct("Ivan", "Milk", 2);

        shop.showPurchaseHistory("Ivan");

        purchase.setPaid(true);
        purchase.printReceipt();

        shop.averagePrice();
        shop.sortByPrice();

    }
}
