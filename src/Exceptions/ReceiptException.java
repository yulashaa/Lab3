package Exceptions;

public class ReceiptException extends Exception {
    public ReceiptException() {
        super("Can`t generate receipt file, pay receipt first");
    }
}