package sample;

public class Autooperator {
    public final double[] a = new double[7];
    public final int[] P = new int[7];
    public final double[] t;
    private final double[][] u;
    private final double[] A = new double[7];
    private double[] F = new double[7];;
    public double func;
    private double[] buffer = new double[7];
    private double[] levels = new double[7];
    private boolean escape = false;
    private double sum = 0;
    private double timing = 0;

    public Autooperator() {
        t = new double[]{0, 7, 15, 3, 4, 2, 5};
        u = new double[][]{
                {0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6},
                {0.1, 0, 0.1, 0.2, 0.3, 0.4, 0.5},
                {0.2, 0.1, 0, 0.1, 0.2, 0.3, 0.4},
                {0.3, 0.2, 0.1, 0, 0.1, 0.2, 0.3},
                {0.4, 0.3, 0.2, 0.1, 0, 0.1, 0.2},
                {0.5, 0.4, 0.3, 0.2, 0.1, 0, 0.1},
                {0.6, 0.5, 0.4, 0.3, 0.2, 0.1, 0}
        };
        func = start();
        System.out.println("\nРасписание R(t): " + a[P[0]] + ", " + a[P[1]] + ", " + a[P[2]] + ", " + a[P[3]] + ", " + a[P[4]] + ", " + a[P[5]] + ", " + a[P[6]]);
        System.out.println("Эффективность (качество) Ф(t) расписания R(t): " + func + "%");
    }

    private double start() {
        A[0] = 0;
        for (int i = 1; i < 6; i++)
            if ((t[i] + 1 + 1 + u[i - 1][i + 1]) > timing) timing = t[i] + 1 + 1 + u[i - 1][i + 1];
        double first_timing = timing;
        for (int i = 0; i < 7; i++) sum = sum + t[i] + 1;
        for (int i = 1; i < 7; i++) A[i] = A[i - 1] + t[i] + 1;

        while (!escape) {
            for (int i = 0; i < 7; i++) {
                levels[i] = 0;
                a[i] = A[i];
                while (a[i] > timing) {
                    levels[i]++;
                    a[i] = A[i] - timing * levels[i];
                }
                buffer[i] = a[i];
            }

            double temp;
            for (int i = 0; i < a.length - 1; i++) {
                for (int j = i + 1; j < a.length; j++) {
                    if (buffer[i] > buffer[j]) {
                        temp = buffer[i];
                        buffer[i] = buffer[j];
                        buffer[j] = temp;
                    }
                }
            }

            for (int i = 0; i < a.length; i++)
                for (int j = 0; j < a.length; j++) if (buffer[i] == a[j]) P[i] = j;

            escape = true;
            for (int i = 0; i < a.length - 1; i++) {
                if (P[i] == 6) F[i] = a[P[i + 1]] - a[P[i]] - 1 - u[P[i + 1]][0];
                else F[i] = a[P[i + 1]] - a[P[i]] - 1 - u[P[i + 1]][P[i] + 1];
                if (F[i] < 0) escape = false;
            }
            timing = timing + 0.1;
        }

        System.out.print("\nОчередь: ");
        for (int i = 0; i < 7; i++) {
            if (P[i] + 1 > 6) System.out.print(P[i] + "->" + 0 + " ");
            else System.out.print(P[i] + "->" + (P[i] + 1) + " ");
        }
        System.out.print(" " + 0 + "\n");

        return Math.round((first_timing / timing) * 100);
    }
}
