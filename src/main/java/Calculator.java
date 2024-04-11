import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Calculator {
    private static Scanner in = new Scanner(System.in);
    private static int countPerson;
    private static HashMap<String, Float> bill = new HashMap<>();

    public static int getCountPerson() {
        int countPerson = in.nextInt();
        while (true) {
            if (countPerson > 1) {
                break;
            } else if (countPerson == 1) {
                System.out.println("Какой смысл считать для 1?Попробуй еще раз");
            } else {
                System.out.println("Вы ввели некоректное число. Количество возможно от 2 и больше");
            }
            countPerson = in.nextInt();
        }
        return countPerson;
    }

    private static String getNamed(float price) {
        String rub;
        if (price == 1) {
            rub = "рубль";
        } else {
            rub = "рублей";
        }
        return rub;
    }

    public static void calc() {
        countPerson = getCountPerson();
        System.out.print("Инструкции для работы с калькуятором.\n" +
                "Команда `Добавить` - добавляет товар в корзину;\n" +
                "Команда `Показать счет` - выводи счет поделенный между участниками;\n" +
                "Команда `Завершить` - завершает работу программы;\n" +
                "************************************************\n\n" +
                "Введите команду: ");
        while (true) {
            String com = in.next();
            switch (com) {
                case "Добавить": {
                    System.out.println("Введите название товара.");
                    String nameProduct = in.next();
                    System.out.println("Введите стоимость товара");
                    float price = in.nextFloat();
                    bill.put(nameProduct, price);
                    System.out.println("Товар успешно добавлен!");
                    break;
                }
                case "Счет": {
                    float countSum = 0;
                    Iterator<String> iterator = bill.keySet().iterator();
                    int i = 0;
                    float cost;
                    String rub;
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        cost = bill.get(key).floatValue();
                        i++;
                        rub = getNamed(cost);
                        countSum += cost;
                        System.out.println(String.format("Товар №%d: %s %.2f %s", i, key, cost, rub));
                    }
                    rub = getNamed(countSum);
                    System.out.println(String.format("Общая сумма %.2f %s", countSum / countPerson, rub));
                    break;
                }
                case "Завершить": {
                    System.out.println("Программа завершает работу");
                    return;
                }
                default: {
                    System.out.println("Команда не распознана. Попробуйте еще раз");
                    break;
                }
            }
            System.out.println("Введите команду");
        }
    }

}

