package iurtaev.classes;

import java.util.*;

import java.util.*;

public class UniqueList {

    // Метод для ручного ввода элементов списка
    public static List<String> getInputList() {
        Scanner scanner = new Scanner(System.in);

        // Считываем количество элементов в списке
        System.out.println("Введите количество элементов в списке:");
        int n = scanner.nextInt();
        scanner.nextLine();  // Чтобы очистить буфер от лишнего символа новой строки

        // Считываем элементы списка
        List<String> list = new ArrayList<>();
        System.out.println("Введите элементы списка:");
        for (int i = 0; i < n; i++) {
            String element = scanner.nextLine();
            list.add(element);
        }

        return list;
    }

    // Метод для обработки списка и удаления повторов
    public static List<String> removeDuplicates(List<String> list) {
        List<String> resultList = new ArrayList<>();

        for (String item : list) {
            if (!resultList.contains(item)) {
                resultList.add(item);
            }
        }

        return resultList;
    }

    // Основной метод - дружественный интерфейс
    public static void run() {
        List<String> list = getInputList();  // Получаем список от пользователя
        List<String> resultList = removeDuplicates(list);  // Убираем повторы
        System.out.println("Список после удаления повторов (оставлены только первые вхождения):");
        System.out.println(resultList);  // Выводим результат
    }
}

