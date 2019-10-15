package Product;

import Exceptions.InvalidProductException;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ProductDirectory {

    private HashMap<Product, Double> products;

    public ProductDirectory(HashMap<Product, Double> products) {
        this.products = products;
    }

    public ProductDirectory(File file) throws IOException {
        products = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             Scanner scanner = new Scanner(reader))
        {
            scanner.useDelimiter(Pattern.compile(" |") + System.lineSeparator());
            while (scanner.hasNext()) {
                String productName = scanner.next();
                if (!scanner.hasNextDouble()) {
                    throw new IOException("Некорректный формат файла");
                }
                Double cost = scanner.nextDouble();
                products.put(new Product(productName), cost);
            }
        }
    }

    public boolean contains(Product product) {
        return products.containsKey(product);
    }

    public double getCost(Product product) {
        if (contains(product)) {
            return products.get(product);
        }
        throw new InvalidProductException(product.getName());
    }

}
