package Homework4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        double earnings = 0;
        double spendings = 0;
        while (true) {
            System.out.println(
                    "Выберите операцию и введите её номер\n" +
                            "1. Добавить новый доход.\n" +
                            "2. Добавить новый расход.\n" +
                            "3. Выбрать систему налогообложения.");
            String inputOperation = scan.nextLine();
            if ("end".equals(inputOperation)) {
                break;
            }
            int operation = Integer.parseInt(inputOperation);
            switch (operation) {
                case 1:
                    earnings = addEarnings(earnings, scan);
                    Thread.sleep(300);
                    break;
                case 2:
                    spendings = addSpendings(spendings, scan);
                    Thread.sleep(300);
                    break;
                case 3:
                    taxCalculation(earnings, spendings, scan);
                    Thread.sleep(300);
                    break;
                default:
                    exceptionPrint();
                    break;
            }
        }
        System.out.println("\n Программа завершена!\n");
    }

    public static void taxCalculation(double earnings, double spendings, Scanner scan) throws Exception {
        int usnEarnings = taxEarnings(earnings);
        int usnEarningsMinusSpendings = taxEarningsMinusSpendings(earnings, spendings);
        Thread.sleep(300);
        if (usnEarnings < usnEarningsMinusSpendings) {
            System.out.println(
                    "\nМы советуем вам УСН доходы \n" +
                            "Ваш налог составит: " + usnEarnings + " рублей \n" +
                            "Налог на другой системе: " + usnEarningsMinusSpendings + " рублей \n" +
                            "Экономия: " + (usnEarningsMinusSpendings - usnEarnings) + " рублей \n");
        } else if (usnEarnings > usnEarningsMinusSpendings) {
            System.out.println(
                    "\nМы советуем вам УСН доходы минус рассходы \n" +
                            "Ваш налог составит: " + usnEarningsMinusSpendings + " рублей \n" +
                            "Налог на другой системе: " + usnEarnings + " рублей \n" +
                            "Экономия: " + (usnEarnings - usnEarningsMinusSpendings) + " рублей\n");
        } else {
            System.out.println("\nМожете выбрать любую систему налогообложения\n");
        }
    }

    public static double addEarnings(double earnings, Scanner scan) throws Exception {
        Thread.sleep(300);
        System.out.println("\nВведите сумму дохода:");
        String moneyStr = scan.nextLine();
        double money = Double.parseDouble(moneyStr);
        earnings += money;
        return earnings;
    }

    public static double addSpendings(double spendings, Scanner scan) throws Exception {
        Thread.sleep(300);
        System.out.println("\nВведите сумму расходов:");
        String moneyStr = scan.nextLine();
        double money = Double.parseDouble(moneyStr);
        spendings += money;
        return spendings;
    }

    public static int taxEarningsMinusSpendings(double earnings, double spendings) {
        int tax = (int) (earnings - spendings) * 15 / 100;
        if (tax >= 0) {
            return tax;
        } else {
            return 0;
        }
    }

    public static int taxEarnings(double earnings) {
        int tax = (int) earnings * 6 / 100;
        if (tax >= 0) {
            return tax;
        } else {
            return 0;
        }
    }

    public static void exceptionPrint() throws Exception {
        Thread.sleep(300);
        System.out.println("\nОшибка: Введены некоректные данные! Пожалуйста попробуйте ещё раз.\n");
    }
}
