package ConsumerBasket;

import Client.Client;
import Product.Product;
import Product.ProductDirectory;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ConsumerBasket {

    private Client client;

    private HashMap<Product, Integer> products;

    private ProductDirectory directory;

    private int orderID;

    public ConsumerBasket(Client client, ProductDirectory directory, int orderID) {
        this.client = client;
        this.directory = directory;
        this.orderID = orderID;
    }

    public ConsumerBasket(File file, Client client, ProductDirectory directory, int orderID) throws IOException {
        this.client = client;
        this.directory = directory;
        this.orderID = orderID;

        products = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             Scanner scanner = new Scanner(reader))
        {
            scanner.useDelimiter(Pattern.compile(" |") + System.lineSeparator());
            while (scanner.hasNext()) {
                String productName = scanner.next();
                if (!scanner.hasNextInt()) {
                    throw new IOException("Некорректный формат файла");
                }
                Integer num = scanner.nextInt();
                products.put(new Product(productName), num);
            }
        }
    }

    public void update() throws IOException {
        products = new HashMap<>();
        System.out.println("Введите вашу корзину");
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            String str = reader.readLine();
            while (!str.equals("")) {
                String[] splitedString = str.split(" ");
                products.put(new Product(splitedString[0]), Integer.parseInt(splitedString[1]));
                str = reader.readLine();
            }
        }
    }

    public void update(File file) throws IOException {
        update();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<Product, Integer> entry: products.entrySet()) {
                writer.write(entry.getKey().getName() + " " + entry.getValue() + System.lineSeparator());
            }
        }
    }

    @Override
    public String toString() {
        String result = "Заказ N" + orderID + " " + client.getSurname() + " " + client.getName() + " " +
                client.getPathronymic() + " ";
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        result += date + System.lineSeparator().repeat(2);

        result += "Название" + "\t" + "Цена" + "\t"  + "Количество" + "\t" + "Сумма" + System.lineSeparator().repeat(2);

        double overallSum = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            String name = entry.getKey().getName();
            int number = entry.getValue();
            double cost = directory.getCost(entry.getKey()), sum = cost * number;
            result += String.format("%s\t%f\t%d\t%f", name, cost, number, sum) + System.lineSeparator().repeat(2);

            overallSum += sum;
        }

        result += "Итого: " + overallSum;

        return result;
    }

}
