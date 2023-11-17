package Exceptions;
 public class FileOpenException extends Exception {
        public FileOpenException(String message) {
            super("Can't open the file");
        }
    }
