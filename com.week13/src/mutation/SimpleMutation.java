package mutation;

import java.util.Random;

public class SimpleMutation implements Mutationer {
    private final double mutationRatio;

    public SimpleMutation(double mutationRatio) {
        this.mutationRatio = mutationRatio;
    }

    @Override
    public double[] mutate(double[] x) {
        double[] y = new double[x.length];
        Random r = new Random();

        for (int i = 0; i < x.length; i++) {
            double a = x[i];
            if (mutationRatio > r.nextDouble())
                a *= 0.5 * r.nextDouble();
            y[i] = a;
        }

        return y;
    }
}
