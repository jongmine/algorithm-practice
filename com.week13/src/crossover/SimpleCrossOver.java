package crossover;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SimpleCrossOver implements Crossoverer {
    @Override
    public double[] crossover(double[] x) {
        Queue<Double> queue = new PriorityQueue<>();
        List<Double> arr = new ArrayList<>();
        double[] y = new double[x.length];

        for (double a : x)
            queue.add(a);

        while (!queue.isEmpty()) {
            double a = queue.remove();
            double b = queue.remove();
            arr.add((a + b) * 0.5);
            arr.add((a + b) * 2);
        }

        for (int i = 0; i < x.length; i++)
            y[i] = arr.get(i);
        return y;
    }
}
