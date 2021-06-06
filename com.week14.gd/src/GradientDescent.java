import java.util.ArrayList;
import java.util.Random;

public class GradientDescent {
   public ArrayList<Double> hist = new ArrayList<>();

    public double solve(Problem p, int niter, double a, double lower, double upper) {
        Random r = new Random();
        double dx = 0.001;
        double x = r.nextDouble() * (upper - lower) + lower;
        hist.add(p.fit(x));
        System.out.printf("[%f] -> %f\n", x, p.fit(x));
        for (int i = 0; i < niter; i++) {
            x = x - a * (p.fit(x + dx) - p.fit(x)) / dx;
            hist.add(p.fit(x));
            System.out.printf("[%f] -> %f\n", x, p.fit(x));
        }
        return x;
    }
}
