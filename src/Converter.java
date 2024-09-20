/**
 * В этом классе осуществляется преобразование шагов в километры и калории.
 * Для подсчёта дистанции можно считать, что один шаг равен 75 см.
 * Для подсчёта количества сожжённых килокалорий можно считать, что 1 шаг = 50 калорий, 1 килокалория = 1 000 калорий.
 */
public class Converter {
    private static final double METERS_PER_STEP = 0.75; // 1 шаг = 75 см
    private static final double CALORIES_PER_STEP = 50; // 1 шаг = 50 калорий

    /**
     * Метод преобразует количество шагов в километры.
     *
     * @param steps количество шагов
     * @return расстояние в километрах
     */
    public double stepsToKilometers(int steps) {   // переводим метры в километр
        return steps * METERS_PER_STEP / 1000;
    }

    /**
     * Метод преобразует количество шагов в килокалории.
     *
     * @param steps количество шагов
     * @return количество сожжённых килокалорий
     */
    public double stepsToKilocalories(int steps) { // метод перевода шагов в Килокалории (kCal)
        return steps * CALORIES_PER_STEP / 1000; // переводим калории в килокалории
    }

}
