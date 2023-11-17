package Exceptions;

import Task3.Product;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(Product product) {
        super("Your product " + product.getName() + "not found!" );
    }
}
