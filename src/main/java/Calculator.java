import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Calculator {
    private static final Scanner in = new Scanner(System.in);
    private static final HashMap<String, Float> bill = new HashMap<>();

    public static int getCountPerson() {
        int countPerson = in.nextInt();
        while (countPerson <= 1) {
            if (countPerson == 1) {
                System.out.println("Какой смысл считать для 1?Попробуй еще раз");
            } else {
                System.out.println("Вы ввели некоректное число. Количество возможно от 2 и больше");
            }
            countPerson = in.nextInt();
        }
        return countPerson;
    }

    private static String getNamed(Float price) {
        String rub;
        if (price == 1) {
            rub = "рубль";
        } else {
            rub = "рублей";
        }
        return rub;
    }

    private static float getCostToProduct() {
        float cost = in.nextFloat();
        while (cost <= 0) {
            System.out.println("Сумма не может быть <= 0\n Попробуйте снова");
            cost = in.nextFloat();
        }
        return cost;
    }

    public static void calc() {
        int countPerson = getCountPerson();
        System.out.print("""
                Инструкции для работы с калькуятором.
                Команда `Добавить` - добавляет товар в корзину;
                Команда `Показать счет` - выводи счет поделенный между участниками;
                Команда `Завершить` - завершает работу программы;
                ************************************************
                Введите команду:\s""");
        while (true) {
            String com = in.next();
            switch (com.toLowerCase()) {
                case "добавить" -> {
                    System.out.println("Введите название товара.");
                    String nameProduct = in.next();
                    System.out.println("Введите стоимость товара");
                    float price = getCostToProduct();
                    bill.put(nameProduct, price);
                    System.out.println("Товар успешно добавлен!");
                }
                case "счет" -> {
                    Float countSum = 0.0f;
                    Iterator<String> iterator = bill.keySet().iterator();
                    int i = 0;
                    Float cost;
                    String rub;
                    while (iterator.hasNext()) {
                        String key = iterator.next();
                        cost = bill.get(key);
                        i++;
                        rub = getNamed(cost);
                        countSum += cost;
                        System.out.printf("Товар №%d: %s %.2f %s%n", i, key, cost, rub);
                    }
                    rub = getNamed(countSum);
                    System.out.printf("Сумма на каждого человека %.2f %s%n", countSum / countPerson, rub);
                }
                case "завершить" -> {
                    System.out.println("Программа завершает работу");
                    return;
                }
                default -> System.out.println("Команда не распознана. Попробуйте еще раз");
            }
            System.out.println("Введите команду");
        }
    }

}

