package org.example;

import  java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double result = 0;
        boolean isFirstInput = true;

        System.out.println("Начало работы калькулятора.");

        while (true) {
            double operand1;

            // Проверка первого ввода, ввод и проверка первого значения
            if (isFirstInput) {
                operand1 = getOperand(scanner, "Введите первое число (для нецелых чисел разделитель - точка)");
                if (Double.isNaN(operand1)) continue;
                isFirstInput = false;
            } else {
                operand1 = result;
                System.out.println("Первое число: " + operand1);
            }

            // Ввод и проверка операции
            char operation = getOperation(scanner, "Введите операцию (+, -, *, /)");
            if (operation == ' ') {
                isFirstInput = true;
                continue;
            }

            // Ввод и проверка второго значения
            double operand2 = getOperand(scanner, "Введите второе число (для нецелых чисел разделитель - точка)");
            if (Double.isNaN(operand2)) {
                isFirstInput = true;
                continue;
            }

            // Выполнение операции
            switch (operation) {
                case '+':
                    result = operand1 + operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '*':
                    result = operand1 * operand2;
                    break;
                case '/':
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        System.out.println("Ошибка - деление на ноль! Попробуйте снова.");
                        continue;
                    }
                    break;
            }

            // Вывод результата
            System.out.println("Операция: " + operand1 + " " + operation + " " + operand2 + " = " + result);
            System.out.println("Результат выполнения: " + result);

            // Запрос на выход из программы и продолжение вычислений с полученным результатом
            if (askExit(scanner)) break;
            isFirstInput = !askContinue(scanner);
        }
    }

    // Метод для обработки команд сброса и выхода
    public static boolean checkCommand(String input) {
        if (input.equalsIgnoreCase("S")) {
            System.out.println("Окончание работы калькулятора.");
            System.exit(0);
        } else if (input.equalsIgnoreCase("C")) {
            System.out.println("Вычисление сброшено.");
            return true;
        }
        return false;
    }

    // Метод для безопасного ввода значения
    public static double getOperand(Scanner scanner, String outputText) {
        while(true) {
            System.out.println(outputText + " или команду (C - сброс, S - выход):");
            String input = scanner.next();

            if (checkCommand(input)) {
                return Double.NaN;
            }

            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Ошибка - введено некорректное значение! Попробуйте снова.");
            }
        }
    }

    // Метод для безопасного ввода операции
    public static char getOperation (Scanner scanner, String outputText) {
        while (true) {
            System.out.println(outputText + " или команду (C - сброс, S - выход):");
            String input = scanner.next();

            if (checkCommand(input)) {
                return ' ';
            }

            if (input.matches("[+\\-*/]")) {
                return input.charAt(0);
            } else {
                System.out.println("Ошибка - введена неподдерживаемая операция! Попробуйте снова.");
            }
        }
    }

    // Метод для запроса на выход из программы
    public static boolean askExit(Scanner scanner) {
        while (true) {
            System.out.println("Хотите продолжить работу с калькулятором? Введите команду (S - выход, R - продолжить):");
            String input = scanner.next();

            if (input.equalsIgnoreCase("S")) {
                System.out.println("Окончание работы калькулятора.");
                return true;
            } else if (input.equalsIgnoreCase("R")) {
                return false;
            } else {
                System.out.println("Ошибка - введен некорректный ответ! Попробуйте снова.");
            }
        }
    }

    // Метод для запроса на продолжение вычислений с полученным результатом
    public static boolean askContinue(Scanner scanner) {
        while (true) {
            System.out.println("Хотите продолжить вычисления с полученным результатом? Введите команду (C - сброс, R - продолжить):");
            String input = scanner.next();

            if (input.equalsIgnoreCase("C")) {
                System.out.println("Текущий результат сброшен.");
                return false;
            } else if (input.equalsIgnoreCase("R")) {
                return true;
            } else {
                System.out.println("Ошибка - введен некорректный ответ! Попробуйте снова.");
            }
        }
    }
}