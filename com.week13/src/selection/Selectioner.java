package selection;

import problem.Solvable;

public interface Selectioner {
    public double[] select(double[] x, Solvable p);
}
