import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // создание объекта класса Scanner

        StepTracker stepTracker = new StepTracker(); // создание объекта класса stepTracker

        System.out.println("Программа <СЧЁТЧИК ШАГОВ 1.0> запущена");

        printMenu();                            // вызываем меню
        int userInput = scanner.nextInt();      // считываем выбор пользователя

        while (userInput != 0) {
            // обработка разных случаев
            if (userInput == 1) {                 // обработка когда надо ввести кол-во шагов
                int monthN;
                int dayN;
                int stepsN;

                while (true) {
                    System.out.println("Введите номер месяца от 0 (ЯНВАРЬ) до 11 (ДЕКАБРЬ).");
                    monthN = scanner.nextInt();

                    if (monthN < 0) {
                        System.out.println("Ошибка! Указано отрицательное значение! Выберите месяц от 0 (ЯНВАРЬ) до 11 (ДЕКАБРЬ).");
                    } else if (monthN >= 12) {
                        System.out.println("Ошибка! Превышение допустимого значения! Выберите месяц от 0 (ЯНВАРЬ) до 11 (ДЕКАБРЬ).");
                    } else {
                        break;
                    }
                }

                while (true) {
                    System.out.println("Введите номер дня в указанном месяце от 1 до 30.");
                    dayN = scanner.nextInt();
                    if (dayN - 1 < 0) {
                        System.out.println("Ошибка! Указано некорректное значение! Введите номер дня в диапазоне от 1 до 30.");
                    } else if (dayN - 1 > 29) {
                        System.out.println("Ошибка! Превышение допустимого значения! Введите номер дня в диапазоне от 1 до 30.");
                    } else {
                        break;
                    }
                }

                while (true) {
                    System.out.println("Выбрана дата: день:" + dayN + " месяц:" + (monthN + 1));
                    System.out.println("Введите количество пройденных шагов: ");
                    stepsN = scanner.nextInt();
                    if (stepsN < 0) {
                        System.out.println("Ошибка! Указано отрицательное значение! Минимальное значение шагов может быть равно нулю.");
                    } else {
                        break;
                    }
                }

                stepTracker.saveStepsInBase(monthN, dayN, stepsN); // обращение к методу по вводу данных

            } else if (userInput == 2) {          // обработка когда надо напечатать статистику
                int monthN;
                while (true) {
                    System.out.println("Введите номер месяца от 0 (ЯНВАРЬ) до 11 (ДЕКАБРЬ) за который необходимо показать данные: ");
                    monthN = scanner.nextInt();

                    if (monthN < 0) {
                        System.out.println("Ошибка! Указано отрицательное значение! Выберите месяц от 0 (ЯНВАРЬ) до 11 (ДЕКАБРЬ).");
                    } else if (monthN >= 12) {
                        System.out.println("Ошибка! Превышение допустимого значения! Выберите месяц от 0 (ЯНВАРЬ) до 11 (ДЕКАБРЬ).");
                    } else {
                        break;
                    }
                }

                stepTracker.statInMonth(monthN); // обращение к методу по выводу статистики


            } else if (userInput == 3) {          // обработка когда надо изменить цель по кол-ву шагов
                System.out.println("Укажите новое целевое значение шагов в день: ");
                int newTarget = scanner.nextInt();
                while (true) {
                    if (newTarget < 0) {
                        System.out.println("Ошибка! Целевое значение должно быть больше нуля!");
                        System.out.println("Повторно укажите новое целевое значение шагов в день:");
                        newTarget = scanner.nextInt();
                    }
                    stepTracker.setNewTarget(newTarget); // обращение к методу по назначению нового целевого показателя
                    break;
                }

            } else {
                System.out.println("Введена некорректная команда.");
            }
            printMenu(); // печатаем меню ещё раз перед завершением предыдущего действия
            userInput = scanner.nextInt(); // повторное считывание данных от пользователя
        }
        System.out.println("Программа завершена!");
    }

    public static void printMenu() {  // метод для вывода меню
        System.out.println(" ");
        System.out.println("Уточните пожалуйста, что Вы хотите сделать? ");
        System.out.println("1 - Ввести данные по количеству шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить целевое значение количества шагов в день");
        System.out.println("0 - Выйти из приложения");
    }
}


