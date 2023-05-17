import java.util.Random;

public class MonteCarloIntegration {
    public static void main(String[] args) {
        int N = 1000; // Кількість генеруємих точок
        double a = 0.0; // Ліва межа інтервалу
        double b = 1.0; // Права межа інтервалу
        double fmax = fMax(a, b);
        double S_par = fmax * b; // Площа прямокутника
        int K = getPointsFallingUnderTheCurve(N, fmax); // кількості точок, що попаkb під криву
        double S = S_par * K / N; // Обчислення площи під кривою
        System.out.println("К-сть генеруємих точок : " + N);
        System.out.println("Площа прямокутника: " + Math.round(S_par));
        System.out.println("Результатом обчислення S: " + S);

    }

    public static int getPointsFallingUnderTheCurve(int N, double fmax) {
        int K = 0; // Початкове значення кількості точок, що попадуть під криву
        for (int i = 0; i < N; i++) {
            Random random = new Random();
            double x = random.nextDouble(); // Генерування випадкового значення x в діапазоні [0, 1]
            double y = random.nextDouble() * fmax; // Генерування випадкового значення y в діапазоні [0, f_max]

            double fx = x * x; // Обчислення значення функції f(x)

            // Перевірка факту попадання точки (x, y) нижче функції f(x)
            if (y < fx) {
                K++;
            }
        }
        return K;
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