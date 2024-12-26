package iurtaev.classes;

import java.io.*;
import java.util.*;

public class UniqueCharacters {

    // Метод для чтения текста из файла
    public static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    // Метод для обработки текста и получения символов, встречающихся только в одном слове
    public static Set<Character> getUniqueCharacters(List<String> lines) {
        Set<Character> uniqueChars = new TreeSet<>();
        Set<Character> repeatedChars = new TreeSet<>();

        // Чтение и обработка строк
        for (String line : lines) {
            // Разбиваем строку на слова
            String[] words = line.split("\\s+");

            // Сет для хранения символов текущего слова
            Set<Character> wordChars;
            // Сет для хранения повторяющихся символов в текущем слове
            Set<Character> wordRepeatedChars;

            // Обрабатываем каждое слово
            for (String word : words) {
                wordChars = new HashSet<>();
                wordRepeatedChars = new HashSet<>();

                // Обрабатываем каждый символ в слове
                for (char c : word.toCharArray()) {
                    if (Character.isLetter(c)) {  // Учитываем только буквы
                        if (wordChars.contains(c)) {
                            wordRepeatedChars.add(c);
                        } else {
                            wordChars.add(c);
                        }
                    }
                }

                // Добавляем символы, которые встретились только один раз в слове
                uniqueChars.addAll(wordChars);
                // Убираем символы, которые встретились более одного раза в слове
                uniqueChars.removeAll(wordRepeatedChars);

                // Добавляем символы, которые встретились более одного раза в слове
                repeatedChars.addAll(wordRepeatedChars);
            }
        }

        // Убираем символы, которые встретились более чем в одном слове
        uniqueChars.removeAll(repeatedChars);

        return uniqueChars;
    }

    // Метод для вывода результата
    public static void printResult(Set<Character> uniqueChars) {
        System.out.println("Символы, встречающиеся только в одном слове: " + uniqueChars);
    }

    // Основной метод, выполняющий только пользовательский интерфейс
    public static void run() {
        // Задаем имя файла
        String filename = "text.txt";

        // Чтение файла
        List<String> lines = readFile(filename);

        // Получаем символы, которые встречаются только в одном слове
        Set<Character> uniqueChars = getUniqueCharacters(lines);

        // Выводим результат
        printResult(uniqueChars);
    }

    // Точка входа
    public static void main(String[] args) {
        run();
    }
}
