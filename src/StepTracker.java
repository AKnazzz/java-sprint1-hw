/**
 * Это класс с основной логикой приложения — в нём хранится информация
 * о пройденных шагах,
 * реализована логика по сохранению и изменению количества шагов,
 * а так же рассчитывается статистика.
 */

public class StepTracker {
    int stepTarget = 10000; // целевое значение шагов в день
    MonthData[] monthToData; // объявление массива для связывания месяца и данных по нему.

    public StepTracker() {          // метод для создания 12 месяцев по 30 дней в каждом
        monthToData = new MonthData[12];
        for (int i = 0; i < monthToData.length; i++) {
            monthToData[i] = new MonthData();
        }
    }

    public void setNewTarget(int newTarget) { // метод изменения целевого значения шагов в день
        System.out.println("Старое целевое значение: " + stepTarget);
        stepTarget = newTarget;
        System.out.println("Новое целевое значение шагов в день:" + stepTarget);
    }

    public void saveStepsInBase(int monthN, int dayN, int stepsN) { // метод для сохранения в конкретном месяце, конкретном дне, конкретного числа шагов
        monthToData[monthN].stepsInDay(dayN, stepsN);
        System.out.println("Месяц: " + monthN + ", день: " + dayN + ", шагов пройдено: " + stepsN + ". Данные успешно сохранены!");
    }

    public void statInMonth(int monthN) { // метод вывода статистики за указанный месяц
        System.out.println("Вывод статистики за месяц " + monthN + " :");
        monthToData[monthN].stepsInMonth(); // вывод кол-ва шагов за каждый день указанного месяца
        monthToData[monthN].sumStepsInMonth(); // вывод суммы кол-ва шагов за каждый день указанного месяца
        monthToData[monthN].maximumStepsInMonth(); // вывод лучшего по кол-ву шагов результата за месяц
        monthToData[monthN].middleStepsInMonth(); // вывод среднего числа шагов в день за месяц
        monthToData[monthN].bestSeriesInMonth(stepTarget); // вывод лучшей серии подряд дней выполнения или перевыполнения цели
        System.out.println("Молодцом!");
    }

    class MonthData {

        private int[] month = new int[30]; // массив с днями

        public void stepsInDay(int day, int steps) { // метод присвоения конкретному дню кол-ва шагов
            if (day < 1 || day > 30 || steps < 0) {
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
            for (int steps : month) {
                totalSteps += steps;
            }
            double average = totalSteps / month.length;
            System.out.printf("Среднее значение пройденных шагов в день за указанный месяц составило: %.2f.\n", average);
        }

        // метод нахождения лучшей серии дней с выполнением заданной цели stepTarget (10000 шагов в день)
        public void bestSeriesInMonth(int stepTarget) {
            int dayInARow = 0;
            int bestSeries = 0;
            for (int i = 0; i < month.length; i++) {
                if (month[i] >= stepTarget) {
                    dayInARow = dayInARow + 1; // если цель в день достигнута, то счётчик прибавляет единицу
                } else {
                    if (dayInARow > bestSeries) {
                        bestSeries = dayInARow; // если было несколько дней подряд, то сохраняется как лучшая серия
                    }
                    dayInARow = 0;    // если не достигнута, то обнуляется
                }
            }

            if (dayInARow != 0) {           // дополнительный блок для выбора наибольшей серии дней (если их было несколько)
                if (dayInARow > bestSeries) {
                    bestSeries = dayInARow;
                }
            }
            System.out.println("Лучшая серия дней подряд по выполнению цели за месяц: " + bestSeries);
        }
    }
}
