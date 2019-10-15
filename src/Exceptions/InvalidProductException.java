package Exceptions;

public class InvalidProductException extends RuntimeException {

    private String productName;

    public InvalidProductException(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return "Продукт \"" + productName + "\" не найден.";
    }

}