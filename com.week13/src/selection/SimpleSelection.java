package selection;

import problem.Solvable;

import java.util.Random;

public class SimpleSelection implements Selectioner {
    @Override
    public double[] select(double[] x, Solvable p) {
        Random r = new Random();
        double[] y = new double[x.length];
        for (int i = 0; i < x.length; i++)
            y[i] = x[r.nextInt(x.length)];
        return y;
    }
}
