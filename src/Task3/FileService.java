package Task3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileService {
    public static void loadFromFile() {
        String filePath = "/Users/uliazapletnuk/Desktop/^_^/ПП/ShopList.txt";

        try (final Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.forEach(line -> {
                String[] parts = line.split(" ");

                if (parts.length >= 3) {
                    final String productName = parts[0];
                    final double price = Double.parseDouble(parts[1]);
                    final int amount = Integer.parseInt(parts[2]);

                    System.out.println("Product: " + productName + ", price: " + price + ", amount: " + amount);
                } else {
                     throw new FileOpenException();
                }
            });
        } catch (final IOException | NumberFormatException e) {
            throw new FileOpenException();
        }

    }
}
