import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CurveFitting {
    public int n;  // 점들의 수
    public ArrayList<Point2D> points;  // 좌표쌍 (2D)
    public int m;  // 다항식의 차수 (m <= n - 1)
    public ArrayList<Double> error;  // 에러

    public CurveFitting(int n, ArrayList<Point2D> points, int exponent) {
        this.n = n;
        this.points = points;
        this.m = exponent;
    }

    public double MSE(double x, int exponent) {
        double sumX = 0, sumY = 0;
        for (Point2D point : points) {
            sumX += point.getX();
            sumY += point.getY();
        }

        System.out.println("X: " + sumX + ", Y: " + sumY);
        SimulatedAnnealing sa = new SimulatedAnnealing(1, 0.998, 1000, true);
        // p(x) = ax + b
        sa.solve(x1 -> x1 + 20, 0, 52);  // [0,52]
        sa.getBestSolution(true);

        return 0;
    }
}
