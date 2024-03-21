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

    class MonthData { // класс для создания объектов месяцев = создаёт отдельный массив на 30 дней
        // Заполните класс самостоятельно
        int[] month = new int[30]; // объявление массива с днями

        public void stepsInDay(int day, int steps) { // метод присвоения конкретному дню кол-ва пройденных шагов
            month[day - 1] = steps;
        }

        public void stepsInMonth() { // метод по выводу статистики по шагам за месяц по дням (в форме указанной в ТЗ)
            System.out.println(" ");
            System.out.println("Количество пройденных шагов по дням за выбранный месяц: ");

            for (int i = 0; i < month.length; i++) {
                String space;
                if (i < (month.length - 1)) {
                    space = ", ";
                    System.out.print((i + 1) + " день: " + month[i] + space);
                }
                if (i == (month.length - 1)) {
                    space = "; ";
                    System.out.println((i + 1) + " день: " + month[i] + space);
                }
            }
        }

        public void sumStepsInMonth() { // метод подсчета кол-ва шагов, ККал, км за месяц
            int sum = 0;
            Converter converter = new Converter(); // создаём объект класса Converter

            for (int i = 0; i < month.length; i++) {
                sum = sum + month[i];
            }
            System.out.println("  ");
            System.out.println("За указанный месяц: ");
            System.out.println("Шагов пройдено: " + sum + "; ");
            System.out.println("Общее пройденное расстояние в км: " + converter.stepsToKM(sum) + "; ");
            System.out.println("Потрачено ККал: " + converter.stepsToKCal(sum) + "; ");
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
