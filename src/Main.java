import Client.Client;
import ConsumerBasket.ConsumerBasket;
import Product.ProductDirectory;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File file = new File(args[0]);
        ProductDirectory directory;
        try {
            directory = new ProductDirectory(file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        file = new File(args[1]);
        Client client = new Client("Иванов", "Иван", "Иванович");
        ConsumerBasket basket;
        try {
            basket = new ConsumerBasket(file, client, directory, 1);
            basket.update(file);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println(basket);
    }

}
