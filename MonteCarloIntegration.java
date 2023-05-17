import java.util.Random;

public class MonteCarloIntegration {
    public static void main(String[] args) {
        int N = 1000; // Кiлькiсть генеруємих точок
        double St = 0.33; // Точне значення iнтегралу
        double a = 0.0; // Лiва межа iнтервалу
        double b = 1.0; // Права межа iнтервалу
        double fmax = fMax(a, b);
        double S_par = fmax * b; // Площа прямокутника
        int K = getPointsFallingUnderTheCurve(N, fmax); // кiлькостi точок, що попаkb пiд криву
        double S = getArea(K, N, S_par); // Обчислення площи пiд кривою
        double[] areas = getAreas(N, fmax, S_par);
        double mathExpec = getMathExpec(areas); // Математичне очiкування
        double dispension = getDispersion(areas, mathExpec); // Дисперсiя
        // System.out.println("К-сть генеруємих точок : " + N);
        // System.out.println("Площа прямокутника: " + Math.round(S_par));
        // System.out.println("Результатом обчислення S: " + S);

        System.out.println("Результат:");
        System.out.println("Кiлькiсть точок: " + N);
        System.out.println("Кiлькiсть значень: " + 10);
        System.out.println("Точне значення iнтегралу: " + St);
        System.out.println("Математичне очiкування: " + mathExpec);
        System.out.println("Cередньоквадратичне вiдхилення: " + Math.sqrt(dispension));
        System.out.println("Абсолютна похiбка: " + (mathExpec - St));
    }

    public static double getDispersion(double[] areas, double mathExpec) {
        double sum = 0;
        for (int i = 0; i < areas.length; i++) {
            sum += Math.pow((areas[i] - mathExpec), 2);
        }
        return sum / 10;
    }

    public static double[] getAreas(int N, double fmax, double Spar) { // генерацыя 10 площ
        double[] areas = new double[10];
        for (int i = 1; i <= 10; i++) {
            areas[i - 1] = getArea(getPointsFallingUnderTheCurve(N, fmax), N, Spar);
            System.out.println(i + ". Результат площi пiд функцiєю: " + areas[i - 1]);
        }
        return areas;
    }

    public static double getMathExpec(double[] areas) { // Математичне очiкування
        double areaSum = 0;
        for (int i = 0; i < areas.length; i++) {
            areaSum += areas[i];
        }
        return areaSum / 10;
    }

    public static int getPointsFallingUnderTheCurve(int N, double fmax) {
        int K = 0; // Початкове значення кiлькостi точок, що попадуть пiд криву
        for (int i = 0; i < N; i++) {
            Random random = new Random();
            double x = random.nextDouble(); // Генерування випадкового значення x в дiапазонi [0, 1]
            double y = random.nextDouble() * fmax; // Генерування випадкового значення y в дiапазонi [0, f_max]

            double fx = x * x; // Обчислення значення функцiї f(x)

            // Перевiрка факту попадання точки (x, y) нижче функцiї f(x)
            if (y < fx) {
                K++;
            }
        }
        return K;
    }

    public static double getArea(int K, int N, double Spar) {
        return Spar * K / N;
    }

    public static double fMax(double a, double b) {
        double maxVal = 0;
        double iter = 0.1;
        for (double i = a; i <= b; i += iter) {
            if (maxVal < i * i)
                maxVal = i * i;
        }
        return maxVal;
    }
}