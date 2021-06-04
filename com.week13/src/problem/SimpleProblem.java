package problem;

public class SimpleProblem implements Solvable {
    @Override
    public double bestfit(double[] x) {
        double max = Double.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < x.length; i++) {
            double y = solve(x[i]);
            if (max < y) {
                max = y;
                maxIndex = i;
            }
        }
        return x[maxIndex];
    }

    @Override
    public double solve(double x) {
        return -x * x + 38 * x + 80;  // f'(x) = -6x + 24
    }
}
