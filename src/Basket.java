import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Basket {
    private String[] products;
    private int[] prices;
    private int[] purchases;

    public Basket(String[] products, int[] prices) throws Exception {
        this.products = products;
        this.prices = prices;
        if (products.length != prices.length) {
            throw new RuntimeException("Длина списка товаров не соответствует длине списка цен");
        }
        purchases = new int[products.length];
    }

    public Basket(String[] products, int[] prices, int[] purchases) throws Exception {
        this.products = products;
        this.prices = prices;
        this.purchases = purchases;
        if (products.length != prices.length || prices.length != purchases.length) {
            throw new RuntimeException("Ошибка создания корзины");
        }
        purchases = new int[products.length];
    }

    @Override
    public String toString() {
        return "Basket{" +
                "purchases=" + Arrays.toString(purchases) +
                '}';
    }

    public boolean addToCart(int productNum, int amount) {
        if (productNum >= products.length) {
            return false;
        } else {
            purchases[productNum] += amount;
            return true;
        }
    }

    public void printProducts() {
        System.out.println("Список доступных для покупки товаров:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }
    }

    public void printCart() {
        System.out.println("В вашей корзине:");
        int k = 0;
        for (int i = 0; i < products.length; i++) {
            if (purchases[i] > 0) {
                int productCost = purchases[i] * prices[i];
                System.out.println(products[i] + " " + purchases[i] + " шт - " + productCost + " руб");
                k++;
            }
        }
        if (k == 0) {
            System.out.println("Пока ничего нет");
        }
    }

    public int sumTotal() {
        int sumTotal = 0;
        for (int i = 0; i < products.length; i++) {
            if (purchases[i] > 0) {
                int productCost = purchases[i] * prices[i];
                sumTotal += productCost;
            }
        }
        return sumTotal;
    }

    public void saveTxt(File textFile) throws IOException {
        try (PrintWriter out = new PrintWriter(textFile);) {
            if (textFile.createNewFile()) {
                System.out.println("Вы у нас первый раз, мы подготовили для вас свой архивный файл!");
            }
            for (String product : products) {
                out.print(product + " ");
            }
            out.print('\n');
            for (int price : prices) {
                out.print(price + " ");
            }
            out.print('\n');
            for (int purchase : purchases) {
                out.print(purchase + " ");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static Basket loadFromTxtFile(File textFile) throws Exception {
        Basket basketFromTxt = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {
            String line;
            List<String> strArray = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                strArray.add(line);
            }
            String[] currentProducts = strArray.get(0).split(" ");

            String[] strPrices = strArray.get(1).split(" ");
            int[] currentPrices = Arrays.stream(strPrices)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            String[] strPurchases = strArray.get(2).split(" ");
            int[] currentPurchases = Arrays.stream(strPurchases)
                    .mapToInt(Integer::parseInt)
                    .toArray();

            basketFromTxt = new Basket(currentProducts, currentPrices, currentPurchases);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return basketFromTxt;
    }

    public String[] getProducts() {
        return products;
    }
}
