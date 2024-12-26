package iurtaev.classes;

import java.util.Scanner;

// Интерфейс для работы с дробями
interface FractionOperations {
    double getDecimalValue();
    void setNumerator(int numerator);
    void setDenominator(int denominator);
}

public class Fraction implements Comparable<Fraction>, FractionOperations {
    private int numerator;   // Числитель
    private int denominator; // Знаменатель
    private Double cachedDecimalValue; // Кэшированное вещественное значение

    // Конструктор с проверкой знака знаменателя
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю.");
        }
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
        this.cachedDecimalValue = null; // Сбрасываем кэшированное значение
    }

    // Метод для нахождения НОД
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Геттеры для числителя и знаменателя
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    // Строковое представление дроби
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    // Метод сравнения дробей по их состоянию
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Fraction other = (Fraction) obj;
        return numerator == other.numerator && denominator == other.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }

    // Сравнение дробей (реализация Comparable)
    @Override
    public int compareTo(Fraction other) {
        return Integer.compare(this.numerator * other.denominator, other.numerator * this.denominator);
    }

    // Получение вещественного значения дроби с кэшированием
    @Override
    public double getDecimalValue() {
        if (cachedDecimalValue == null) {
            cachedDecimalValue = (double) numerator / denominator;
        }
        return cachedDecimalValue;
    }

    // Установка числителя
    @Override
    public void setNumerator(int numerator) {
        this.numerator = numerator;
        this.cachedDecimalValue = null; // Сбрасываем кэшированное значение
    }

    // Установка знаменателя
    @Override
    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю.");
        }
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        this.denominator = denominator;
        this.cachedDecimalValue = null; // Сбрасываем кэшированное значение
    }

    // Метод для ручного ввода дроби
    public static FractionOperations inputFraction() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите числитель: ");
        int numerator = scanner.nextInt();

        System.out.print("Введите знаменатель: ");
        int denominator = scanner.nextInt();

        // Создаем объект дроби
        try {
            return new Fraction(numerator, denominator);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return null; // Возвращаем null, если дробь невалидна
        }
    }

    public static void run() {
        // Ввод дроби через интерфейс
        FractionOperations fraction = inputFraction();
        if (fraction != null) {
            System.out.println("Введенная дробь: " + fraction);
            System.out.println("Вещественное значение: " + fraction.getDecimalValue());
        }
    }
}
