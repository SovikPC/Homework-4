package Homework4;

import java.util.Scanner;

public class Homework4 {
    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        double earnings = 0;
        double spendings = 0;
        while (true) {
            System.out.println(
                "Выберите операцию и введите её номер\n" +
                "1. Добавить новый доход.\n" + 
                "2. Добавить новый расход.\n" + 
                "3. Выбрать систему налогообложения."
            );
            boolean isNumber = false;
            String inputOperation = scan.nextLine();
            if ("end".equals(inputOperation)){                
                break;
            }
            int operation = 0;
            try {
                operation = Integer.parseInt(inputOperation);
                isNumber = true;
            } catch (NumberFormatException e) {
                isNumber = false;
            }            
            if (isNumber) {
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
                        int usnEarnings = taxEarnings(earnings);
                        int usnEarningsMinusSpendings = taxEarningsMinusSpendings(earnings, spendings);
                        if(usnEarnings < usnEarningsMinusSpendings){
                            System.out.println(
                                "\nМы советуем вам УСН доходы \n" +
                                "Ваш налог составит: " + usnEarnings +" рублей \n" +
                                "Налог на другой системе: " + usnEarningsMinusSpendings + " рублей \n" +
                                "Экономия: " + (usnEarningsMinusSpendings - usnEarnings) + " рублей \n"
                            );
                        } else if (usnEarnings > usnEarningsMinusSpendings) {
                            System.out.println(
                                "\nМы советуем вам УСН доходы минус рассходы \n" +
                                "Ваш налог составит: " + usnEarningsMinusSpendings +" рублей \n" +
                                "Налог на другой системе: " + usnEarnings + " рублей \n" +
                                "Экономия: " + (usnEarnings - usnEarningsMinusSpendings) + " рублей\n"
                            );
                        } else {
                            System.out.println(
                                "\nМожете выбрать любую систему налогообложения\n"
                            );
                        }
                        Thread.sleep(300);
                        break;                                
                    default:
                        exceptionPrint();
                        break;                    
                }
                Thread.sleep(300);
            }  else {
                exceptionPrint();
            }          
        }
        System.out.println("\n Программа завершена!\n");
    }

    public static double addEarnings(double earnings, Scanner scan) throws Exception {
        boolean summ = false;
        System.out.println("\nВведите сумму дохода:");
        String moneyStr = scan.nextLine();        
        double money = 0;
        try {
            money = Double.parseDouble(moneyStr);
            summ = true;                            
        } catch (NumberFormatException e) {
            summ = false;
        }
        if(summ){            
            earnings += money;
        } else {
            exceptionPrint();
        }    
        return earnings;
    }

    public static double addSpendings(double spendings, Scanner scan) throws Exception {
        boolean summ = false;
        System.out.println("\nВведите сумму расходов:");
        String moneyStr = scan.nextLine();
        double money = 0;
        try {
            money = Double.parseDouble(moneyStr);
            summ = true;                            
        } catch (NumberFormatException e) {
            summ = false;
        }
        if(summ){            
            spendings += money;
        } else {
            exceptionPrint();
        }    
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
        System.out.println("\nОшибка: Введены некоректные данные! Пожалуйста попробуйте ещё раз.\n");
        Thread.sleep(300);
    }
}
