
import java.awt.*;
import java.util.ArrayList;

public class CurveFitting {
    public int n;  // 점들의 수
    public ArrayList<Point> points;  // 좌표쌍 (2D)
    public int m;  // 다항식의 차수 (m <= n - 1)
    public ArrayList<Double> error;  // 에러

    public CurveFitting(int n, ArrayList<Point> points, int exponent) {
        this.n = n;
        this.points = points;
        this.m = exponent;
    }
/*
    p(x) = b_0 + b_1x + ... + b_mx^m
    q = summation from j=1 to n (y_j - p(x_j)^2)

    aq/ab_0 = 0, ..., aq/ab_m = 0 => m+1개의 정규방정식 획득
     */

    // error를 최소화할 때 simulated annealing 사용
    public double costFunction(double x, int exponent) {
        double a, b, c, d, tempSum = 0, sumOfY = 0;
        double[][] equations = new double[exponent + 1][exponent + 1]; // m+1개의 정규방정식을 획득해야 하므로
        ArrayList<Double> sumOfX = new ArrayList<Double>(exponent + 1);  // sigma x^index
        ArrayList<Double> sumOfPowXandY = new ArrayList<Double>(exponent + 1);  // sigma x^index * y

        for (int i = 1; i < exponent * 2; i++) {
            for (int j = 0; j < points.size(); j++)
                tempSum += Math.pow(points.get(i).getX(), i);
            sumOfX.add(i, tempSum);
        }
        for (int i = 0; i < exponent + 1; i++) {
            for (int j = 0; j < points.size(); j++)
                tempSum += Math.pow(points.get(i).getX(), i) * points.get(i).getY();
            sumOfPowXandY.add(i, tempSum);
        }

        equations[0][0] = sumOfX.get(0);

        equations[0][1] = sumOfX.get(1);
        equations[1][0] = sumOfX.get(1);

        equations[0][2] = sumOfX.get(2);
        equations[1][1] = sumOfX.get(2);
        equations[2][0] = sumOfX.get(2);

        equations[0][3] = sumOfX.get(3);
        equations[1][2] = sumOfX.get(3);
        equations[2][1] = sumOfX.get(3);
        equations[3][0] = sumOfX.get(3);

        equations[1][3] = sumOfX.get(4);
        equations[2][2] = sumOfX.get(4);
        equations[3][1] = sumOfX.get(4);

        equations[2][3] = sumOfX.get(5);
        equations[3][2] = sumOfX.get(5);

        equations[3][3] = sumOfX.get(6);

        SimulatedAnnealing sa = new SimulatedAnnealing(1, 0.998, 1000, true);
        sa.solve(x1 -> -x1 * x1 + 38 * x1 + 80, 0, 31);

        return 0;
    }
}
