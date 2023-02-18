import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        File settings = new File("shop.xml");
        File basketFile = new File("basket.json");
        ClientLog basketLog = new ClientLog();

        Basket basket;
        if (loadSettings(settings, "load") && basketFile.exists()) {
            basket = Basket.loadFromTxtFile(basketFile);
        } else {
            basket = new Basket(new String[]{"Молоко", "Сок", "Сыр", "Творог", "Хлеб"},
                    new int[]{60, 80, 300, 150, 50});
        }
        intro(basket);
        while (true) {
            System.out.println("Введите через пробел номер продукта и его количество или введите 'end'");
            String input = scanner.nextLine();

            if (ending(input)) break;

            String[] productAndPrice = input.split(" ");
            if (inputError(productAndPrice)) continue;

            try {
                int productNum = Integer.parseInt(productAndPrice[0]) - 1;
                if (productNumError(basket, productNum)) continue;

                int amount = Integer.parseInt(productAndPrice[1]);
                if (amountError(amount)) continue;

                if (basket.addToCart(productNum, amount)) {
                    System.out.println("Товар успешно добавлен");
                    basketLog.log((productNum+1), amount);
                }
            } catch (NumberFormatException exception) {
                System.out.println("Вы вводите не цифры, а что-то другое! Повторите попытку");
            }
        }
        basket.printCart();
        System.out.println("Итоговая сумма: " + basket.sumTotal() + " руб");

        if (loadSettings(settings, "save")) {
            basket.saveTxt(basketFile);
        }
        if (loadSettings(settings, "log")) {
            saveLog(basketLog);
        }
    }

    public static boolean loadSettings(File settings, String config) throws ParserConfigurationException, SAXException, IOException {
        Node root = getRoot(settings);
        Node loadJson = ((Element) root).getElementsByTagName(config).item(0);
        String needConfig = ((Element) loadJson).getElementsByTagName("enabled").item(0).getTextContent();
        return needConfig.equals("true");
    }

    private static Node getRoot(File settings) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(settings);
        return doc.getDocumentElement();
    }

    public static void saveLog(ClientLog basketLog) throws IOException {
        File csvLog = new File("basketLog.csv");
        basketLog.exportAsCSV(csvLog);
    }

    public static void intro(Basket basket) {
        basket.printProducts();
        System.out.println("Входим в режим покупок!");
        basket.printCart();
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
