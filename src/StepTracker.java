/**
Это класс с основной логикой приложения — в нём хранится информация
 о пройденных шагах,
 реализована логика по сохранению и изменению количества шагов,
 а так же рассчитывается статистика.
*/
import  java.util.Scanner; // имопорт сканера
public class StepTracker {
    int stepTarget = 10000; // целевое значение шагов в день
    Scanner scanner = new Scanner(System.in); // создание объекта класса Scanner
    Converter converter = new Converter(); // создание объекта класса Converter
    MonthData[] monthToData; // массив для связывания месяца и данных по нему.
    public StepTracker() {          // метод для создания 12 месяцев по 30 дней в каждом
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    public void setNewTarget () { // метод изменения целевого значения шагов в день
        System.out.println("Целевое значение шагов в день составляет: " + stepTarget + ". ");
        System.out.println("Укажите новое целевое значение шагов в день: ");
        int newTarget = scanner.nextInt();
        while (true) {
            if (newTarget < 0) {
                System.out.println("Ошбика! Целевое значение должно быть больше нуля!");
                System.out.println("Повтрно укажите новое целевое значение шагов в день:");
                newTarget = scanner.nextInt();
            }
            stepTarget = newTarget;
            System.out.println("Новое целовое значение успешно сохранено! Теперь оно составляет: " + stepTarget + " шагов в день.");
            break;
        }
    }

    public void saveStepsInBase () { // метод для сохранения в конкретном месяце, контретном дне, конкретного числа шагов
        int monthN;
        int dayN;
        int stepsN;

        while (true) {
            System.out.println("Введите номер месяца от 1 до 12.");
            monthN = scanner.nextInt();

            if (monthN-1 < 0) {
                System.out.println("Ошбика! Некорректное значение! Выберите месяц от 1 до 12.");
            } else if (monthN-1 > 12) {
                System.out.println("Ошбика! Значение превышено! Выберите месяц от 1 до 12.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Введите номер дня от 1 до 30.");
            dayN = scanner.nextInt();
            if (dayN-1 < 0) {
                System.out.println("Ошбика! Некорректное значение! Выберите месяц от 1 до 30.");
            } else if (dayN-1 > 30) {
                System.out.println("Ошбика! Значение превышено! Выберите месяц от 1 до 30.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("Введите число шагов, пройденных в этот день.");
            stepsN = scanner.nextInt();
            if (stepsN < 0) {
                System.out.println("Ошбика! Отрицательное значение! Минимальное значение шагов может быть ноль.");
            } else {
                break;
            }
        }

        monthToData[monthN-1].stepsInDay(dayN, stepsN);
        System.out.println("Месяц: " + monthN +", день: " + dayN + ", шагов пройдено: " + stepsN + ". Данные успешно сохранены!");
    }

    public void statInMonth () { // метод вывода статистики за указанный месяц
        int monthN;
        while (true) {
            System.out.println("Введите номер месяца от 1 до 12 за который необходимо показать данные: ");
            monthN = scanner.nextInt();

            if (monthN - 1 < 0) {
                System.out.println("Ошбика! Некорректное значение! Выберите месяц от 1 до 12.");
            } else if (monthN - 1 > 12) {
                System.out.println("Ошбика! Значение превышено! Выберите месяц от 1 до 12.");
            } else {
                break;
            }
        }
        monthToData[monthN-1].stepsInMonth(); // вывод кол-ва шагов за каждый день указанного месяца
        monthToData[monthN-1].summStepsInMonth(); // вывод суммы кол-ва шагов за каждый день указанного месяца
        monthToData[monthN-1].maximumStepsInMonth(); // вывод лушего по кол-ву шагов результата за месяц
        monthToData[monthN-1].middleStepsInMonth(); // вывод среднего числа шагов в день за месяц
        monthToData[monthN-1].bestSeriesInMonth(stepTarget); // вывод лучшей серии подряд дней выполнения или перевыодлнения цели
        System.out.println("Молодцом!");
    }

    class MonthData { // класс для создания объектов месяцев = создаёт отдельный массив на 30 дней
        // Заполните класс самостоятельно
        int[] month = new int[30]; // объявление массива с днями

        public void stepsInDay(int day, int steps) { // метод присвоения конкретному дню кол-ва пройденых шагов
            month[day-1] = steps;
        }

        public void stepsInMonth() { // метод по выводу статистики по шагам за месяц по дням (в форме указанной в ТЗ)
            System.out.println("Количество пройденных шагов по дням за выбранный месяц: ");
            for (int i = 0; i < month.length; i++) {
                System.out.print((i + 1) + " день: " + month[i] + "; ");
            }
            System.out.println("Конец месяца.");
        }

        public void summStepsInMonth() { // метод подсчета кол-ва шагов, ККал , км за месяц
            int summ = 0;
            Converter converter = new Converter(); // создаём объект класса Converter

            for (int i = 0; i < month.length; i++) {
                summ = summ + month[i];
            }
            System.out.println("За указанный месяц: ");
            System.out.println("Шагов пройдено: " + summ + "; ");
            System.out.println("Общее пройденное расстояние в км: " + converter.stepsToKM(summ) + "; ");
            System.out.println("Потрачено ККал: " + converter.stepsToKCal(summ) + "; ");
            System.out.println("Молодец! Так держать!");
        }

        public void maximumStepsInMonth() { // метод вывода максимального числа шагов в день в указанном месяце
            int maxSteps = 0;
            for (int i = 0; i < month.length; i++) {
                if (month[i] > maxSteps) {
                    maxSteps = month[i];
                }
            }
            System.out.println("Лучший результат за этот месяц:" + maxSteps + " шагов в день! ");
        }

        public void middleStepsInMonth() { // метод нахождения среднего числа шагов в месяц
            double middleSteps = 0;
            for (int i = 0; i < month.length; i++) {
                middleSteps = middleSteps + month[i];
            }
            System.out.println("Среднее значение пройденных шагов в день за указанный месяц составило: " + middleSteps / month.length + " ."); // возвращаем общее число шагов разделённое на кол-во дней в месяце
        }

        public void bestSeriesInMonth(int stepTarget) { // метод нахождения лучшей серии дней с выполнением заданной цели stepTarget (10000 шагов в день)
            int dayInARow = 0;
            int bestSeries = 0;
            for (int i = 0; i < month.length; i++) {
                if (month[i] >= stepTarget) {
                    dayInARow = dayInARow + 1; // если цель в день достигнута то счётчик прибавляет единицу
                } else {
                    if (dayInARow > bestSeries) {
                        bestSeries = dayInARow; // если было несколько дней подряд то сохраняется как лучшая серия
                    }
                    dayInARow = 0;    // если не достигнута то обнуляется
                }
            }
            System.out.println("Лучшая серия дней подряд по выполнию цели за месяц: " + bestSeries);
        }
    }
}
