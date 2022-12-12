import  java.util.Scanner; // импортируем сканер
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StepTracker stepTracker = new StepTracker();

        printMenu();                            // вызываем меню
        int userInput = scanner.nextInt();      // считываем выбор пользователя

        while (userInput != 0) {
            // обработка разных случаев
            if (userInput == 1) {                 // обработка когда надо ввести кол-во шагов
                stepTracker.saveStepsInBase();
            } else if (userInput == 2) {          // обработка когда надо напечатать статистику
                stepTracker.statInMonth();
            } else if (userInput == 3) {          // обработка когда надо изменить цель по кол-ву шагов
                stepTracker.setNewTarget();
            } else if (userInput == 0) {
                break;
            } else {
                System.out.println("Такой команды нет!");
            }
            printMenu(); // печатаем меню ещё раз перед завершением предыдущего действия
            userInput = scanner.nextInt(); // повторное считывание данных от пользователя
        }
        System.out.println("Программа завершена");
    }

    public static void printMenu() {  // метод для вывода меню
        System.out.println("Программа <СЧЁТЧИК ШАГОВ> запущена");
        System.out.println("Уточните что Вы хотите сделать? ");
        System.out.println("1 - Ввести количество шагов за определённый день");
        System.out.println("2 - Напечатать статистику за определённый месяц");
        System.out.println("3 - Изменить цель по количеству шагов в день");
        System.out.println("0 - Выйти из приложения");
    }
}


