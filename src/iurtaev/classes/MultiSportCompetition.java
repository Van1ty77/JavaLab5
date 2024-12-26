package iurtaev.classes;

import java.io.*;
import java.util.*;

public class MultiSportCompetition {

    // Класс для представления спортсмена
    static class Athlete {
        String surname;
        String name;
        int[] scores;
        int totalScore;

        // Конструктор для спортсмена
        public Athlete(String surname, String name, int[] scores) {
            this.surname = surname;
            this.name = name;
            this.scores = scores;
            this.totalScore = Arrays.stream(scores).sum(); // Сумма баллов
        }

        @Override
        public String toString() {
            return surname + " " + name + " " + totalScore;
        }
    }

    // Метод для загрузки данных из файла
    public static List<Athlete> loadAthletesFromFile(String filename) throws FileNotFoundException {
        List<Athlete> athletes = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            // Чтение количества спортсменов и видов спорта
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            scanner.nextLine();  // Пропускаем остаток строки после чисел

            // Ввод информации о спортсменах из файла
            for (int i = 0; i < N; i++) {
                String[] input = scanner.nextLine().split(" ");
                String surname = input[0];
                String name = input[1];
                int[] scores = new int[M];
                for (int j = 0; j < M; j++) {
                    scores[j] = Integer.parseInt(input[j + 2]);
                }
                athletes.add(new Athlete(surname, name, scores));
            }
        }
        return athletes;
    }

    // Метод для сортировки спортсменов по сумме баллов
    public static Map<Integer, List<Athlete>> sortAthletesByTotalScore(List<Athlete> athletes) {
        Map<Integer, List<Athlete>> scoreMap = new TreeMap<>(Collections.reverseOrder());

        // Сортируем спортсменов по сумме баллов
        for (Athlete athlete : athletes) {
            scoreMap.putIfAbsent(athlete.totalScore, new ArrayList<>());
            scoreMap.get(athlete.totalScore).add(athlete);
        }

        return scoreMap;
    }

    // Метод для вывода результатов
    public static void printResults(Map<Integer, List<Athlete>> scoreMap) {
        int rank = 1;
        for (Map.Entry<Integer, List<Athlete>> entry : scoreMap.entrySet()) {
            for (Athlete athlete : entry.getValue()) {
                System.out.println(athlete + " " + rank);
            }
            rank += entry.getValue().size(); // Учитываем количество спортсменов с одинаковыми баллами
        }
    }

    // Основной метод, который будет выполнять только пользовательский интерфейс
    public static void run() {
        try {
            // Задаем путь к файлу
            String filename = "athletes.txt"; // Путь к файлу с данными

            // Загружаем спортсменов из файла
            List<Athlete> athletes = loadAthletesFromFile(filename);

            // Сортируем спортсменов по сумме баллов
            Map<Integer, List<Athlete>> scoreMap = sortAthletesByTotalScore(athletes);

            // Выводим результаты
            printResults(scoreMap);

        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        }
    }
}
