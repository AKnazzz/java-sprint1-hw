/**
 * Это класс с основной логикой приложения — в нём хранится информация
 * о пройденных шагах,
 * реализована логика по сохранению и изменению количества шагов,
 * а так же рассчитывается статистика.
 */

public class StepTracker {

    private static final int DEFAULT_STEP_TARGET = 10000; // целевое значение шагов в день
    private static final int DAYS_IN_MONTH = 30; // количество дней в месяце

    private MonthData[] monthToData; // массив для связывания месяца и данных по нему
    private int stepTarget; // целевое значение шагов


    public StepTracker() {
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
        this.stepTarget = DEFAULT_STEP_TARGET; // устанавливаем целевое значение по умолчанию
    }

    public void setNewTarget(int newTarget) { // изменение целевого значения шагов в день
        if (newTarget <= 0) {
            System.out.println("Ошибка: целевое значение должно быть положительным.");
            return;
        }
        System.out.println("Старое целевое значение: " + stepTarget);
        stepTarget = newTarget;
        System.out.println("Новое целевое значение шагов в день: " + stepTarget);
    }

    public void saveStepsInBase(int monthN, int dayN, int stepsN) { // сохранение шагов
        if (monthN < 0 || monthN >= monthToData.length || dayN < 1 || dayN > DAYS_IN_MONTH || stepsN < 0) {
            System.out.println("Ошибка: некорректные данные для сохранения шагов.");
            return;
        }
        monthToData[monthN].stepsInDay(dayN, stepsN);
        System.out.println("Месяц: " + (monthN + 1) + ", день: " + dayN + ", шагов пройдено: " + stepsN + ". Данные успешно сохранены!");
    }

    public void statInMonth(int monthN) { // вывод статистики за указанный месяц
        if (monthN < 0 || monthN >= monthToData.length) {
            System.out.println("Ошибка: некорректный номер месяца.");
            return;
        }
        System.out.println("Вывод статистики за месяц " + (monthN + 1) + ":");
        monthToData[monthN].stepsInMonth();
        monthToData[monthN].sumStepsInMonth();
        monthToData[monthN].maximumStepsInMonth();
        monthToData[monthN].middleStepsInMonth();
        monthToData[monthN].bestSeriesInMonth(stepTarget);
        System.out.println("Молодцом!");
    }

    static class MonthData {

        private int[] month = new int[DAYS_IN_MONTH]; // массив с днями

        public void stepsInDay(int day, int steps) { // метод присвоения шагов
            if (day < 1 || day > DAYS_IN_MONTH || steps < 0) {
                System.out.println("Ошибка: некорректный день или количество шагов.");
                return;
            }
            month[day - 1] = steps;
        }

        // метод вывода статистики по шагам за месяц по дням (в форме указанной в ТЗ)
        public void stepsInMonth() {
            StringBuilder result = new StringBuilder("Количество пройденных шагов по дням за выбранный месяц:\n");
            for (int i = 0; i < month.length; i++) {
                result.append((i + 1)).append(" день: ").append(month[i]);
                if (i < month.length - 1) {
                    result.append(", ");
                } else {
                    result.append("; ");
                }
            }
            System.out.println(result);
        }

        // метод подсчета кол-ва шагов, ККал, км за месяц
        public void sumStepsInMonth() {
            int sum = 0;
            for (int steps : month) {
                sum += steps;
            }

            Converter converter = new Converter();

            System.out.println("\nЗа указанный месяц:");
            System.out.printf("Шагов пройдено: %d;\n", sum);
            System.out.printf("Общее пройденное расстояние в км: %.2f;\n", converter.stepsToKilometers(sum));
            System.out.printf("Потрачено ККал: %.2f;\n", converter.stepsToKilocalories(sum));
            System.out.println("Молодец! Так держать!");
        }

        // метод вывода максимального числа шагов в день в указанном месяце
        public void maximumStepsInMonth() {
            int maxSteps = 0;
            for (int steps : month) {
                maxSteps = Math.max(maxSteps, steps);
            }
            System.out.println("Лучший результат за этот месяц: " + maxSteps + " шагов в день!");
        }

        // метод нахождения среднего числа шагов в месяц
        public void middleStepsInMonth() {
            double totalSteps = 0;
            int daysWithSteps = 0; // счетчик дней с шагами

            for (int steps : month) {
                totalSteps += steps;
                if (steps > 0) {
                    daysWithSteps++;
                }
            }

            double average = (daysWithSteps > 0) ? totalSteps / daysWithSteps : 0; // избегаем деления на ноль
            System.out.printf("Среднее значение пройденных шагов в день за указанный месяц составило: %.2f.\n", average);
        }


        // метод нахождения лучшей серии дней с выполнением заданной цели stepTarget (10000 шагов в день)
        public void bestSeriesInMonth(int stepTarget) {
            if (stepTarget <= 0) {
                System.out.println("Ошибка: цель должна быть положительным числом.");
                return;
            }

            int dayInARow = 0;
            int bestSeries = 0;

            for (int steps : month) {
                if (steps >= stepTarget) {
                    dayInARow++;
                } else {
                    bestSeries = Math.max(bestSeries, dayInARow);
                    dayInARow = 0;
                }
            }

            bestSeries = Math.max(bestSeries, dayInARow); // проверка на случай, если серия закончилась в конце месяца

            if (bestSeries == 0) {
                System.out.println("К сожалению, не было дней с выполнением цели за месяц.");
            } else {
                System.out.println("Лучшая серия дней подряд по выполнению цели за месяц: " + bestSeries);
            }
        }
    }
}
