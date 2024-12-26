package iurtaev.classes;

import java.util.*;

public class ReverseQueue {

    // Метод для ручного ввода элементов в очередь
    public static Queue<Integer> getInputQueue() {
        Scanner scanner = new Scanner(System.in);

        // Считываем количество элементов в очереди
        System.out.println("Введите количество элементов в очереди:");
        int n = scanner.nextInt();

        // Создаем очередь и добавляем элементы
        Queue<Integer> queue = new LinkedList<>();
        System.out.println("Введите элементы очереди:");
        for (int i = 0; i < n; i++) {
            queue.add(scanner.nextInt());
        }

        return queue;
    }

    // Метод для переписывания элементов из L1 в L2 в обратном порядке
    public static void reverseQueue(Queue<Integer> L1, Queue<Integer> L2) {
        int size = L1.size();

        // Переносим элементы из L1 в L2 в обратном порядке
        for (int i = 0; i < size; i++) {
            if (L1.isEmpty()) {
                break; // Проверка на пустую очередь
            }

            Integer element = L1.poll(); // Извлекаем элемент из L1
            if (element != null) {
                // Ожидаем, пока все элементы L1 не окажутся в L2, затем вставляем
                for (int j = 0; j < i; j++) {
                    L2.add(L2.poll());
                }
                L2.add(element); // Вставляем текущий элемент в начало L2
            }
        }
    }

    // Основной метод
    public static void run() {
        // Получаем очередь L1 с помощью метода ручного ввода
        Queue<Integer> L1 = getInputQueue();

        // Создаем пустую очередь L2
        Queue<Integer> L2 = new LinkedList<>();

        // Переписываем элементы из L1 в L2 в обратном порядке
        reverseQueue(L1, L2);

        // Выводим результат
        System.out.println("Очередь L2 (в обратном порядке): " + L2);
    }
}
