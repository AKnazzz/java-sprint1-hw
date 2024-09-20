import java.util.InputMismatchException;
import java.util.Scanner;

public class StepTrackerApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();
        System.out.println("Программа <СЧЁТЧИК ШАГОВ 1.0> запущена");

        int userInput;
        do {
            printMenu();
            userInput = getValidInteger(scanner);
            switch (userInput) {
                case 1:
                    inputSteps(scanner, stepTracker);
                    break;
                case 2:
                    showStatistics(scanner, stepTracker);
                    break;
                case 3:
                    changeStepTarget(scanner, stepTracker);
                    break;
                case 0:
                    System.out.println("Программа завершена!");
                    break;
                default:
                    System.out.println("Введена некорректная команда.");
            }
        } while (userInput != 0);
    }

    private static void inputSteps(Scanner scanner, StepTracker stepTracker) {
        int monthN = getValidMonth(scanner);
        int dayN = getValidDay(scanner);
        int stepsN = getValidSteps(scanner);
        stepTracker.saveStepsInBase(monthN, dayN, stepsN);
    }

    private static int getValidMonth(Scanner scanner) {
        int monthN;
        while (true) {
            System.out.println("Введите номер месяца от 0 (ЯНВАРЬ) до 11 (ДЕКАБРЬ).");
            monthN = getValidInteger(scanner);
            if (monthN < 0 || monthN >= 12) {
                System.out.println("Ошибка! Выберите месяц от 0 (ЯНВАРЬ) до 11 (ДЕКАБРЬ).");
            } else {
                return monthN;
            }
        }
    }

    private static int getValidDay(Scanner scanner) {
        int dayN;
        while (true) {
            System.out.println("Введите номер дня в указанном месяце от 1 до 30.");
            dayN = getValidInteger(scanner);
            if (dayN < 1 || dayN > 30) {
                System.out.println("Ошибка! Введите номер дня в диапазоне от 1 до 30.");
            } else {
                return dayN;
            }
        }
    }

    private static int getValidSteps(Scanner scanner) {
        int stepsN;
        while (true) {
            System.out.println("Введите количество пройденных шагов: ");
            stepsN = getValidInteger(scanner);
            if (stepsN < 0) {
                System.out.println("Ошибка! Минимальное значение шагов может быть равно нулю.");
            } else {
                return stepsN;
            }
        }
    }

    private static void showStatistics(Scanner scanner, StepTracker stepTracker) {
        int monthN = getValidMonth(scanner);
        stepTracker.statInMonth(monthN);
    }

    private static void changeStepTarget(Scanner scanner, StepTracker stepTracker) {
        int newTarget;
        while (true) {
            System.out.println("Укажите новое целевое значение шагов в день: ");
            newTarget = getValidInteger(scanner);
            if (newTarget < 0) {
                System.out.println("Ошибка! Целевое значение должно быть больше нуля!");
            } else {
                stepTracker.setNewTarget(newTarget);
                break;
            }
        }
    }

    private static int getValidInteger(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Пожалуйста, введите целое число.");
                scanner.next(); // очищаем некорректный ввод
            }
        }
    }

    public static void printMenu() {  // метод для вывода меню
        System.out.println("Уточните, пожалуйста, что Вы хотите сделать:");
        System.out.println("1 - Ввести данные по количеству шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить целевое значение количества шагов в день");
        System.out.println("0 - Выйти из приложения");
    }
}