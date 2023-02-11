import java.util.Arrays;

public class Basket {
    private String[] products;
    private int[] prices;
    private int[] purchases;

    public Basket(String[] products, int[] prices) throws Exception{
        this.products = products;
        this.prices = prices;
        if (products.length != prices.length) {
            throw new RuntimeException("Длина списка товаров не соответствует длине списка цен");
        }
        purchases = new int[products.length];
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
            System.out.println((i+1) + ". " + products[i] + " " + prices[i] + " руб/шт");
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

    public String[] getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "purchases=" + Arrays.toString(purchases) +
                '}';
    }
}
