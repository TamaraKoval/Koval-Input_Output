import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);

        Basket basket = new Basket(new String[]{"Молоко", "Сок", "Сыр", "Творог", "Хлеб"},
                new int[]{60, 80, 300, 150, 50});

        basket.printProducts();
        System.out.println("Входим в режим покупок!");

        while (true) {
            System.out.println("Введите через пробел номер продукта и его количество или введите 'end'");
            String input = scanner.nextLine();

            if (ending(input)) break;

            String[] productAndPrice = input.split(" ");
            if (inputError(productAndPrice)) continue;

            try {
                int productNum = Integer.parseInt(productAndPrice[0]) -1;
                if (productNumError(basket, productNum)) continue;

                int amount = Integer.parseInt(productAndPrice[1]);
                if (amountError(amount)) continue;

                if (basket.addToCart(productNum, amount)) {
                    System.out.println("Товар успешно добавлен");
                }

            } catch (NumberFormatException exception) {
                System.out.println("Вы вводите не цифры, а что-то другое! Повторите попытку");
            }
        }
        basket.printCart();
        System.out.println("Итоговая сумма: " + basket.sumTotal() + " руб");
        System.out.println(basket); // для проверки

        File basketFile = new File("basket.txt");
        basket.saveTxt(basketFile);
    }

    private static boolean ending(String str) {
        if ("end".equals(str)) {
            System.out.println("Процесс покупок завершен. Идет подсчет корзины...................");
            return true;
        }
        return false;
    }

    private static boolean inputError(String[] input) {
        if (input.length != 2) {
            System.out.println("Данные введены не корректно, повторите попытку");
            return true;
        }
        return false;
    }

    private static boolean productNumError(Basket basket, int productNum) {
        if (productNum >= basket.getProducts().length || productNum < 0) {
            System.out.println("Нет продукта под номером " + (productNum + 1) + "!");
            return true;
        }
        return false;
    }

    private static boolean amountError(int amount) {
        if (amount < 0) {
            System.out.println("Нельзя купить отрицательное число товаров. Или вы хотите нам его подарить?=)))");
            return true;
        }
        return false;
    }
}
