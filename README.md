## Программа "Покупательская корзина" ##
### (Программа выполнена для netology.ru) ###

Состоит из 2-х файлов: Main.java и Basket.java

Basket - класс, объекты которого представляют из себя покупательскую корзину.

Имеет:
1) Конструктор, принимающий массивы цен и названий продуктов
2) Метод добавления продуктов  корзину ```boolean addToCart(int productNum, int amount)```
3) Метод вывода на экран ассортимента магазина ```void printProducts()```
4) Метод вывода на экран покупательской корзины ```void printCart()```
5) Метод подсчета суммы товаро в корзине ```int sumTotal()```
6) Метод сохранения корзины в текстовый файл ```void saveBin(File file)```
7) Метод восстановления объекта корзины из текстового файла ```static Basket loadFromBinFile(File file)```