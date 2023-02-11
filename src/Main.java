import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] products = {"Молоко", "Сок", "Сыр", "Творог", "Хлеб"};
        int[] prices = {60, 80, 300, 150, 50};
        int[] purchases = new int[products.length];

        System.out.println("Список доступных для покупки товаров:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i+1) + ". " + products[i] + " " + prices[i] + " руб/шт");
        }
        System.out.println("Входим в режим покупок!");

        while (true) {
            System.out.println("Введите через пробел номер продукта и его количество или введите 'end'");
            String input = scanner.nextLine();

            if ("end".equals(input)) {
                System.out.println("Процесс покупок завершен. Идет подсчет корзины...................");
                break;
            }

            String[] productAndPrice = input.split(" ");
            int productNumber = Integer.parseInt(productAndPrice[0]) - 1;
            int productCount = Integer.parseInt(productAndPrice[1]);
            purchases[productNumber] += productCount;
        }

        System.out.println("Ваша корзина:");
        int sumTotal = 0;
        for (int i = 0; i < products.length; i++) {
            if (purchases[i] > 0) {
                int productCost = purchases[i] * prices[i];
                System.out.println(products[i] + " " + purchases[i] + " шт - " + productCost + " руб");
                sumTotal += productCost;
            }
        }
        System.out.println("Итоговая сумма: " + sumTotal + " руб");
    }
}
