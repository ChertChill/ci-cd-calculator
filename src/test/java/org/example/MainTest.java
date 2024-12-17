package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    // Проверка корректного ввода числа
    @Test
    void testGetOperandValidInput() {
        String input = "5.5\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        double result = Main.getOperand(scanner, "Введите число:");
        assertEquals(5.5, result);

        System.setIn(System.in); // Восстановление стандартного ввода
        System.out.println("-- testGetOperandValidInput passed.");
    }

    // Проверка некорректного ввода числа и последующего ввода корректного значения
    @Test
    void testGetOperandInvalidInput() {
        String input = "abc\n10.1\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        double result = Main.getOperand(scanner, "Введите число:");
        assertEquals(10.1, result);

        System.setIn(System.in);
        System.out.println("-- testGetOperandInvalidInput passed.");
    }

    // Проверка корректного ввода операции
    @Test
    void testGetOperationValidInput() {
        String input = "*\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        char result = Main.getOperation(scanner, "Введите операцию:");
        assertEquals('*', result);

        System.setIn(System.in);
        System.out.println("-- testGetOperationValidInput passed.");
    }

    // Проверка некорректного ввода операции и последующего ввода корректного значения
    @Test
    void testGetOperationInvalidInput() {
        String input = "invalid\n+\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        char result = Main.getOperation(scanner, "Введите операцию:");
        assertEquals('+', result);

        System.setIn(System.in);
        System.out.println("-- testGetOperationInvalidInput passed.");
    }

    // Проверка команды "C" для сброса
    @Test
    void testCheckCommandReset() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        boolean isReset = Main.checkCommand("C");

        String output = outputStream.toString();
        assertTrue(output.contains("Вычисление сброшено."));
        assertTrue(isReset);

        System.setOut(System.out);
        System.out.println("-- testCheckCommandReset passed.");
    }

    // Проверка запроса на продолжение работы (ответ R)
    @Test
    void testAskExitContinue() {
        String input = "R\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        boolean result = Main.askExit(scanner);
        assertFalse(result);

        System.setIn(System.in);
        System.out.println("-- testAskExitContinue passed.");
    }

    // Проверка сброса текущего результата (ответ C)
    @Test
    void testAskContinueReset() {
        String input = "C\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        boolean result = Main.askContinue(scanner);
        assertFalse(result);

        System.setIn(System.in);
        System.out.println("-- testAskContinueReset passed.");
    }

    // Проверка продолжения работы с текущим результатом (ответ R)
    @Test
    void testAskContinueProceed() {
        String input = "R\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Scanner scanner = new Scanner(System.in);
        boolean result = Main.askContinue(scanner);
        assertTrue(result);

        System.setIn(System.in);
        System.out.println("-- testAskContinueProceed passed.");
    }
}